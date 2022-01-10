package com.belpost.telegram.bot.mapper;

import com.belpost.telegram.bot.config.MapStructConfig;
import com.belpost.telegram.bot.model.SystemEnum;
import com.belpost.telegram.bot.model.TrackUpdate;
import com.belpost.telegram.bot.model.TrackingInfo;
import com.belpost.telegram.bot.model.belpost.Step;
import com.belpost.telegram.bot.model.belpost.TrackingInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapStructConfig.class, imports = SystemEnum.class)
public interface TrackingInfoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "chatTrackRequest", ignore = true)
    @Mapping(target = "trackNumber", source = "number")
    @Mapping(target = "trackUpdates", source = "steps")
    TrackingInfo convert(TrackingInfoDto trackingInfoDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "system", expression = "java(SystemEnum.getEnumByName(step.getSystem()))")
    @Mapping(target = "createdAt", source = "created_at", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "isBorder", source = "is_border")
    @Mapping(target = "borderLink", source = "border_link")
    @Mapping(target = "placeIndex", source = "place_index")
    @Mapping(target = "trackingInfo", ignore = true)
    TrackUpdate convert(Step step);
}
