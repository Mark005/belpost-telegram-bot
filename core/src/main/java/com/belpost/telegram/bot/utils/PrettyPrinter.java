package com.belpost.telegram.bot.utils;

import com.belpost.telegram.bot.model.belpost.PostTrackingResponse;
import org.springframework.util.CollectionUtils;

public class PrettyPrinter {

    public static String getPretty(PostTrackingResponse postTrackingResponse) {
        if (CollectionUtils.isEmpty(postTrackingResponse.getData()) ||
                CollectionUtils.isEmpty(postTrackingResponse.getData().get(0).getSteps())) {
            return "Order data not found";
        }

        var wholeOrderPattern = """
                ORDER NUMBER %s
                                
                STEPS
                """;

        var stepPattern = """
                ----------------------------------
                DATE: %s
                CURRENT PLACE: %s
                EVENT: %s
                """;
        StringBuilder sb = new StringBuilder();

        postTrackingResponse.getData()
                .forEach(trackingInfo -> {
                    sb.append(wholeOrderPattern.formatted(trackingInfo.getNumber()));

                    trackingInfo.getSteps()
                            .forEach(step ->
                                    sb.append(
                                            stepPattern.formatted(
                                                    step.getCreated_at(),
                                                    step.getPlace(),
                                                    step.getEvent())));
                });
        return sb.toString();
    }
}
