package ph.txtdis.dto;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import ph.txtdis.domain.Channel;

@Getter
@Component
@NoArgsConstructor
public class VolumePerChannelByRoute extends AbstractVolumeByRoute {

    private Channel channel;

    public VolumePerChannelByRoute(Channel channel, BigDecimal pms1Vol,
            BigDecimal pms2Vol, BigDecimal pms3Vol, BigDecimal s41Vol,
            BigDecimal s42Vol, BigDecimal s43Vol, BigDecimal s44Vol,
            BigDecimal s45Vol, BigDecimal s46Vol, BigDecimal s47Vol,
            BigDecimal s48Vol, BigDecimal s49Vol) {
        super(pms1Vol, pms2Vol, pms3Vol, s41Vol, s42Vol, s43Vol, s44Vol, s45Vol,
                s46Vol, s47Vol, s48Vol, s49Vol);
        this.channel = channel;
    }
}