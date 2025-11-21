package com.grammers.shop.settlement.batch;

import com.grammers.shop.seller.domain.SellerRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class SellerSettlementScheduler {

    private final SellerRepository sellerRepository;
    private final JobLauncher jobLauncher;
    private final Job sellerSettlementJob;
    private final ThreadPoolTaskExecutor settlementTaskExecutor;

    @Value("${settlement.async.enabled}")
    private boolean settlementAsyncEnabled;

    @Scheduled(cron = "${spring.task.scheduling.cron.settlement}")
    public void runMidnightSettlements(){
        Pageable pageable = Pageable.ofSize(100);
        Page<UUID> page;
        do {
            page = sellerRepository.findAll(pageable).map(seller -> seller.getId());
            List<UUID> sellerIds = page.getContent();
            if(sellerIds.isEmpty()){
                break;
            }
            log.info("Settlement batch chunk for {} sellers (page {}/{})",
                    sellerIds.size(), page.getNumber()+1, page.getTotalPages());
            sellerIds.forEach(this::runJobForSeller);
            pageable = page.hasNext() ? page.nextPageable() : Pageable.unpaged();
        } while (page.hasNext());
    }

    private void runJobForSeller(UUID sellerId) {
        try {
            Runnable executeJob = () -> {
                try {
                    JobParameters params = new JobParametersBuilder()
                            .addLong("timestamp", System.currentTimeMillis())
                            .addString("sellerId", sellerId.toString())
                            .toJobParameters();
                    jobLauncher.run(sellerSettlementJob, params);
                    log.info("Settlement job triggered for seller {}", sellerId);
                } catch (Exception ex) {
                    log.error("Failed to run settlement job for seller {}", sellerId, ex);
                }
            };

            if (settlementAsyncEnabled) {
                settlementTaskExecutor.execute(executeJob);
            } else {
                executeJob.run();
            }
        } catch (Exception ex) {
            log.error("Failed to run settlement job for seller {}", sellerId, ex);
        }
    }
}
