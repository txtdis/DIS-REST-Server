package ph.txtdis.repository;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ph.txtdis.domain.Item;

@Repository("itemRepository")
public interface ItemRepository extends SpunRepository<Item, Long> {

	// @Query("select i.family from Item i where i.id = :id")
	// ItemFamily getFamily(@Param("id") Long id);
	//
	// @Query("select i.priceHistory from Item i where i.id = :id")
	// List<Pricing> getPriceHistory(@Param("id") Long id);
	//
	// @Query("select i.qtyPerUom from Item i where i.id = :id")
	// List<QtyPerUom> getQtyPerUom(@Param("id") Long id);
	//
	// @Query("select q.qty from Item i join i.qtyPerUom q where i.id = :id and
	// q.uom = :uom")
	// BigDecimal getQtyPerUom(@Param("id") Long id, @Param("uom") UomType uom);
	//
	// @Query("select i.volumeDiscounts from Item i where i.id = :id")
	// List<VolumeDiscount> getVolumeDiscounts(@Param("id") Long id);
	//
	// @Query("select i.boms from Item i where i.id = :id")
	// List<Bom> getBoms(@Param("id") Long id);
	//
	// @Query("select q.uom from QtyPerUom q where q.item = :item and
	// q.isPurchased = true")
	// List<UomType> getPurchasingUoms(@Param("item") Item item);
	//
	// @Query("select q.uom from QtyPerUom q where q.item = :item and q.isSold =
	// true")
	// List<UomType> getSellingUoms(@Param("item") Item item);
	//
	// @Query("select q.uom from QtyPerUom q where q.item = :item and
	// q.isReported = true")
	// List<UomType> getReportingUoms(@Param("item") Item item);
	//
	// @Query(" select new ph.txtdis.domain.ItemPrice(i.id, i.name,
	// i.description, "
	// + " (select p.price from Pricing p where p.item = i and p.type =
	// ph.txtdis.type.PricingType.LIST "
	// + " and p.channelLimit = :none and p.startDate = "
	// + " (select max(p.startDate) from Pricing p where p.type =
	// ph.txtdis.type.PricingType.LIST "
	// + " and p.item = i and p.channelLimit = :none)), "
	// + " (select p.price from Pricing p where p.item = i and p.type =
	// ph.txtdis.type.PricingType.LIST "
	// + " and p.channelLimit = :channel and p.startDate = "
	// + " (select max(p.startDate) from Pricing p where p.type =
	// ph.txtdis.type.PricingType.LIST "
	// + " and p.item = i and p.channelLimit = :channel))) "
	// + " from Item i where i.deactivatedBy is null and i.type <>
	// ph.txtdis.type.ItemType.MONETARY ")
	// List<ItemPrice> getItemsWithTheirLatestPrices(@Param("none") Channel
	// none, @Param("channel") Channel channel);

	List<Item> findByDescriptionContaining(@Param("description") String description);
}
