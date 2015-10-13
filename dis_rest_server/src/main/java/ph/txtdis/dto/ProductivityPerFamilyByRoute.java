package ph.txtdis.dto;

import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ph.txtdis.domain.ItemFamily;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Component
public class ProductivityPerFamilyByRoute {

    private ItemFamily family;

    private int pms1Count, pms2Count, pms3Count;

    private int s41Count, s42Count, s43Count, s44Count, s45Count, s46Count, s47Count, s48Count, s49Count;

    public ProductivityPerFamilyByRoute(ItemFamily family, Long pms1Count, Long pms2Count, Long pms3Count,
            Long s41Count, Long s42Count, Long s43Count, Long s44Count, Long s45Count, Long s46Count, Long s47Count,
            Long s48Count, Long s49Count) {
        this.family = family;
        this.pms1Count = pms1Count == null ? 0 : pms1Count.intValue();
        this.pms2Count = pms2Count == null ? 0 : pms2Count.intValue();
        this.pms3Count = pms3Count == null ? 0 : pms3Count.intValue();
        this.s41Count = s41Count == null ? 0 : s41Count.intValue();
        this.s42Count = s42Count == null ? 0 : s42Count.intValue();
        this.s43Count = s43Count == null ? 0 : s43Count.intValue();
        this.s44Count = s44Count == null ? 0 : s44Count.intValue();
        this.s45Count = s45Count == null ? 0 : s45Count.intValue();
        this.s46Count = s46Count == null ? 0 : s46Count.intValue();
        this.s47Count = s47Count == null ? 0 : s47Count.intValue();
        this.s48Count = s48Count == null ? 0 : s48Count.intValue();
        this.s49Count = s49Count == null ? 0 : s49Count.intValue();
    }
}