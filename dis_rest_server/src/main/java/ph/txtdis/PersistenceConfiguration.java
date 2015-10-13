package ph.txtdis;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import ph.txtdis.domain.Account;
import ph.txtdis.domain.Authority;
import ph.txtdis.domain.Booking;
import ph.txtdis.domain.BookingDetail;
import ph.txtdis.domain.BookingDiscount;
import ph.txtdis.domain.Channel;
import ph.txtdis.domain.Customer;
import ph.txtdis.domain.Invoice;
import ph.txtdis.domain.InvoiceBooklet;
import ph.txtdis.domain.InvoiceDetail;
import ph.txtdis.domain.InvoiceDiscount;
import ph.txtdis.domain.Item;
import ph.txtdis.domain.ItemFamily;
import ph.txtdis.domain.Location;
import ph.txtdis.domain.LocationTree;
import ph.txtdis.domain.Picking;
import ph.txtdis.domain.Price;
import ph.txtdis.domain.PricingType;
import ph.txtdis.domain.Purchase;
import ph.txtdis.domain.PurchaseDetail;
import ph.txtdis.domain.QtyPerUom;
import ph.txtdis.domain.Receipt;
import ph.txtdis.domain.ReceiptDetail;
import ph.txtdis.domain.Remittance;
import ph.txtdis.domain.RemittanceDetail;
import ph.txtdis.domain.Route;
import ph.txtdis.domain.Routing;
import ph.txtdis.domain.StockTake;
import ph.txtdis.domain.StockTakeDetail;
import ph.txtdis.domain.StockTakeReconciliation;
import ph.txtdis.domain.Truck;
import ph.txtdis.domain.User;
import ph.txtdis.domain.VolumeDiscount;
import ph.txtdis.domain.Warehouse;
import ph.txtdis.repository.BookingRepository;
import ph.txtdis.repository.ChannelRepository;
import ph.txtdis.repository.CustomerRepository;
import ph.txtdis.repository.InvoiceBookletRepository;
import ph.txtdis.repository.InvoiceRepository;
import ph.txtdis.repository.ItemFamilyRepository;
import ph.txtdis.repository.ItemRepository;
import ph.txtdis.repository.LocationRepository;
import ph.txtdis.repository.LocationTreeRepository;
import ph.txtdis.repository.PickingRepository;
import ph.txtdis.repository.PricingTypeRepository;
import ph.txtdis.repository.PurchaseRepository;
import ph.txtdis.repository.ReceivingRepository;
import ph.txtdis.repository.RemittanceRepository;
import ph.txtdis.repository.RouteRepository;
import ph.txtdis.repository.StockTakeReconciliationRepository;
import ph.txtdis.repository.StockTakeRepository;
import ph.txtdis.repository.TruckRepository;
import ph.txtdis.repository.UserRepository;
import ph.txtdis.repository.WarehouseRepository;
import ph.txtdis.type.CustomerType;
import ph.txtdis.type.ItemTier;
import ph.txtdis.type.ItemType;
import ph.txtdis.type.LocationType;
import ph.txtdis.type.QualityType;
import ph.txtdis.type.ReceivingReferenceType;
import ph.txtdis.type.UomType;
import ph.txtdis.type.UserType;
import ph.txtdis.type.VisitFrequency;
import ph.txtdis.type.VolumeDiscountType;
import ph.txtdis.util.Spring;

@Configuration("persistenceConfiguration")
public class PersistenceConfiguration {

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private ChannelRepository channelRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private InvoiceBookletRepository bookletRepository;

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private ItemFamilyRepository familyRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private LocationRepository locationRepository;

	@Autowired
	private LocationTreeRepository locationTreeRepository;

	@Autowired
	private PickingRepository pickRepository;

	@Autowired
	private PricingTypeRepository pricingTypeRepository;

	@Autowired
	private PurchaseRepository purchaseRepository;

	@Autowired
	private ReceivingRepository receiptRepository;

	@Autowired
	private RemittanceRepository remittanceRepository;

	@Autowired
	private RouteRepository routeRepository;

	@Autowired
	private StockTakeRepository stockTakeRepository;

	@Autowired
	private StockTakeReconciliationRepository stockTakeReconciliationRepository;

	@Autowired
	private TruckRepository truckRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private WarehouseRepository warehouseRepository;

	private PricingType purchase, list;

	private QtyPerUom cs() {
		return new QtyPerUom(UomType.CS, new BigDecimal(24), true, true, true);
	}

	private List<InvoiceDiscount> getInvoiceDiscounts(Booking booking) {
		List<BookingDiscount> list = booking.getDiscounts();
		return list == null ? null
				: booking.getDiscounts().stream().map(b -> new InvoiceDiscount(b.getLevel(), b.getPercent()))
						.collect(Collectors.toList());
	}

	private LocalDate newDate() {
		return LocalDate.now();
	}

	private LocalDate oldDate() {
		return LocalDate.parse("2014-08-31");
	}

	private QtyPerUom pc() {
		return new QtyPerUom(UomType.PC, BigDecimal.ONE, false, true, false);
	}

	private Price pineSlice15ListPricingPerPC() {
		Price pineSlice15ListPricingPerPC = new Price(list, new BigDecimal(42.48), startDate());
		return pineSlice15ListPricingPerPC;
	}

	private List<Price> pineSlice15Prices() {
		List<Price> prices15 = Arrays.asList(pineSlice15PurchasePricingPerPC(), pineSlice15ListPricingPerPC());
		return prices15;
	}

	private Price pineSlice15PurchasePricingPerPC() {
		Price pineSlice15PurchasePricingPerPC = new Price(purchase, new BigDecimal(37.66), startDate());
		return pineSlice15PurchasePricingPerPC;
	}

	private Price pineSliceFlatListPricePerPC() {
		return new Price(list, new BigDecimal(23.48), startDate());
	}

	private Price pineSliceFlatNewPurchasePricePerPC() {
		return new Price(purchase, new BigDecimal(21.70), newDate());
	}

	private Price pineSliceFlatOldPurchasePricePerPC() {
		return new Price(purchase, new BigDecimal(20.70), startDate());
	}

	private List<Price> pineSliceFlatPrices() {
		return Arrays.asList(pineSliceFlatOldPurchasePricePerPC(), pineSliceFlatNewPurchasePricePerPC(),
				pineSliceFlatListPricePerPC());
	}

	private List<QtyPerUom> pineSliceQtyPerUom() {
		return Arrays.asList(pc(), cs());
	}

	private VolumeDiscount pineSliceVolumeDiscount() {
		return new VolumeDiscount(VolumeDiscountType.SET, UomType.PC, 24, new BigDecimal(0.15), startDate());
	}

	private List<VolumeDiscount> pineSliceVolumeDiscounts() {
		return Arrays.asList(pineSliceVolumeDiscount());
	}

	@PostConstruct
	private void start() {
		if (userRepository.count() >= 1)
			return;

		List<Authority> roles = Arrays.asList(new Authority(UserType.MANAGER));

		User sysgen = new User("SYSGEN", Spring.encode("I'mSysGen4txtDIS@PostgreSQL"), true);
		sysgen.setRoles(roles);
		userRepository.save(sysgen);

		User jackie = new User("JACKIE", Spring.encode("robbie"), true);
		jackie.setEmail("manila12@gmail.com");
		jackie.setRoles(roles);
		userRepository.save(jackie);

		User ronald = new User("RONALD", Spring.encode("alphacowboy"), true);
		ronald.setEmail("ronaldallanso@yahoo.com");
		ronald.setRoles(roles);
		userRepository.save(ronald);

		User butch = new User("BUTCH", "attila", true);
		butch.setEmail("butchlim888@yahoo.com");
		butch.setRoles(roles);
		userRepository.save(butch);

		User txtdis = new User("TXTDIS", "txtDIS@1", true);
		txtdis.setEmail("txtdis.mgdc.edsa.dmpi@gmail.com");
		ronald.setRoles(roles);
		userRepository.save(txtdis);

		userRepository.save(new User("MICHELLE", "TWEETY", true));
		userRepository.save(new User("ANGIE", "Angelica Loteyro", true));
		userRepository.save(new User("MAY", "May Tuscano", true));
		userRepository.save(new User("MENNEN", "Mennen Timbal", true));
		userRepository.save(new User("MHON", "NOMAR", true));
		userRepository.save(new User("IRENE", "magnum08", true));

		User dsp1 = userRepository.save(new User("OGIE", "dsp", true));
		User dsp2 = userRepository.save(new User("PHILLIP", "dsp", true));
		User dsp3 = userRepository.save(new User("BONG", "dsp", true));
		User dsp4 = userRepository.save(new User("RANDY", "dsp", true));
		User dsp5 = userRepository.save(new User("ROBERT", "dsp", true));
		User dsp6 = userRepository.save(new User("HENRY", "dsp", true));
		User dsp7 = userRepository.save(new User("ROLAND", "dsp", true));

		Truck rdm801 = truckRepository.save(new Truck("RDM801"));
		Truck kdl170 = truckRepository.save(new Truck("KDL170"));
		Truck wsn519 = truckRepository.save(new Truck("WSN519"));

		Route s41 = new Route("S41");
		s41.setSellerHistory(Arrays.asList(new Account(dsp1.getUsername(), startDate())));
		s41 = routeRepository.save(s41);

		Route s42 = new Route("S42");
		s42.setSellerHistory(Arrays.asList(new Account(dsp2.getUsername(), startDate())));
		s42 = routeRepository.save(s42);

		Route s43 = new Route("S43");
		s43.setSellerHistory(Arrays.asList(new Account(dsp3.getUsername(), startDate())));
		s43 = routeRepository.save(s43);

		Route s44 = new Route("S44");
		s44.setSellerHistory(Arrays.asList(new Account(dsp4.getUsername(), startDate())));
		s44 = routeRepository.save(s44);

		routeRepository.save(new Route("S45"));

		Route pms1 = new Route("PMS1");
		pms1.setSellerHistory(Arrays.asList(new Account(dsp5.getUsername(), startDate())));
		pms1 = routeRepository.save(pms1);

		Route pms2 = new Route("PMS2");
		pms2.setSellerHistory(Arrays.asList(new Account(dsp6.getUsername(), startDate())));
		pms2 = routeRepository.save(pms2);

		Route pms3 = new Route("PMS3");
		pms3.setSellerHistory(Arrays.asList(new Account(dsp7.getUsername(), startDate())));
		pms3 = routeRepository.save(pms3);

		userRepository.save(new User("LARRY", "dsp", true));
		userRepository.save(new User("VICENTE", "dsp", true));
		userRepository.save(new User("NOLI", "dsp", true));
		userRepository.save(new User("MARK", "dsp", true));
		userRepository.save(new User("MICHAEL", "dsp", true));
		userRepository.save(new User("TATA", "dsp", true));
		userRepository.save(new User("KEVIN", "dsp", true));
		userRepository.save(new User("JEFF", "dsp", true));
		userRepository.save(new User("RENE", "dsp", true));

		channelRepository.save(new Channel("GROCERY"));
		channelRepository.save(new Channel("SARI-SARI"));
		channelRepository.save(new Channel("WET MARKET"));
		channelRepository.save(new Channel("DRY MARKET"));
		channelRepository.save(new Channel("DRUG STORE"));
		channelRepository.save(new Channel("TFO"));

		familyRepository.save(new ItemFamily("CHEESE MAGIC", ItemTier.CATEGORY));
		familyRepository.save(new ItemFamily("FNR", ItemTier.CATEGORY));
		familyRepository.save(new ItemFamily("FRUITS", ItemTier.CATEGORY));
		familyRepository.save(new ItemFamily("KETCHUP", ItemTier.CATEGORY));
		familyRepository.save(new ItemFamily("MIX JUICES", ItemTier.CATEGORY));
		familyRepository.save(new ItemFamily("ORANGE", ItemTier.CATEGORY));
		familyRepository.save(new ItemFamily("OTHERS", ItemTier.CATEGORY));
		familyRepository.save(new ItemFamily("PASTA", ItemTier.CATEGORY));
		familyRepository.save(new ItemFamily("PASTA 175G", ItemTier.CATEGORY));
		familyRepository.save(new ItemFamily("PASTE", ItemTier.CATEGORY));
		familyRepository.save(new ItemFamily("PINE JUICE", ItemTier.CATEGORY));
		familyRepository.save(new ItemFamily("Q&E MEAL MIXES", ItemTier.CATEGORY));
		ItemFamily pineSolid = familyRepository.save(new ItemFamily("SOLIDS", ItemTier.CATEGORY));
		familyRepository.save(new ItemFamily("SPAGHETTI SAUCE", ItemTier.CATEGORY));
		familyRepository.save(new ItemFamily("TIDBITS 115G", ItemTier.CATEGORY));
		familyRepository.save(new ItemFamily("TOMATO SAUCE", ItemTier.CATEGORY));
		familyRepository.save(new ItemFamily("TS 90", ItemTier.CATEGORY));
		familyRepository.save(new ItemFamily("VINEGAR", ItemTier.CATEGORY));

		Warehouse edsa = warehouseRepository.save(new Warehouse("EDSA"));

		Location bulacan = locationRepository.save(new Location("BULACAN", LocationType.PROVINCE));
		Location san_jose_del_monte = locationRepository.save(new Location("SAN JOSE DEL MONTE", LocationType.CITY));
		Location assumption = locationRepository.save(new Location("ASSUMPTION", LocationType.BARANGAY));
		Location bagong_buhay = locationRepository.save(new Location("BAGONG BUHAY", LocationType.BARANGAY));
		Location citrus = locationRepository.save(new Location("CITRUS", LocationType.BARANGAY));
		Location ciudad_real = locationRepository.save(new Location("CIUDAD REAL", LocationType.BARANGAY));
		Location dulong_bayan = locationRepository.save(new Location("DULONG BAYAN", LocationType.BARANGAY));
		Location fatima = locationRepository.save(new Location("FATIMA", LocationType.BARANGAY));
		Location francisco_homes = locationRepository.save(new Location("FRANCISCO HOMES", LocationType.BARANGAY));
		Location gaya_gaya = locationRepository.save(new Location("GAYA-GAYA", LocationType.BARANGAY));
		Location graceville = locationRepository.save(new Location("GRACEVILLE", LocationType.BARANGAY));
		Location gumaoc = locationRepository.save(new Location("GUMAOC", LocationType.BARANGAY));
		Location kaybanban = locationRepository.save(new Location("KAYBANBAN", LocationType.BARANGAY));
		Location kaypian = locationRepository.save(new Location("KAYPIAN", LocationType.BARANGAY));
		Location lawang_pare = locationRepository.save(new Location("LAWANG PARE", LocationType.BARANGAY));
		Location maharlika = locationRepository.save(new Location("MAHARLIKA", LocationType.BARANGAY));
		Location minuyan = locationRepository.save(new Location("MINUYAN", LocationType.BARANGAY));
		Location muzon = locationRepository.save(new Location("MUZON", LocationType.BARANGAY));
		Location paradise = locationRepository.save(new Location("PARADISE", LocationType.BARANGAY));
		Location poblacion = locationRepository.save(new Location("POBLACION", LocationType.BARANGAY));
		Location san_isidro = locationRepository.save(new Location("SAN ISIDRO", LocationType.BARANGAY));
		Location san_manuel = locationRepository.save(new Location("SAN MANUEL", LocationType.BARANGAY));
		Location san_martin = locationRepository.save(new Location("SAN MARTIN", LocationType.BARANGAY));
		Location san_pedro = locationRepository.save(new Location("SAN PEDRO", LocationType.BARANGAY));
		Location san_rafael = locationRepository.save(new Location("SAN RAFAEL", LocationType.BARANGAY));
		Location san_roque = locationRepository.save(new Location("SAN ROQUE", LocationType.BARANGAY));
		Location sapang_palay = locationRepository.save(new Location("SAPANG PALAY", LocationType.BARANGAY));
		Location saint_martin_de_porres = locationRepository
				.save(new Location("SAINT MARTIN DE PORRES", LocationType.BARANGAY));
		Location santa_cruz = locationRepository.save(new Location("SANTA CRUZ", LocationType.BARANGAY));
		Location santo_cristo = locationRepository.save(new Location("SANTO CRISTO", LocationType.BARANGAY));
		Location santo_niño = locationRepository.save(new Location("SANTO NIÃ‘O", LocationType.BARANGAY));
		Location tungkong_mangga = locationRepository.save(new Location("TUNGKONG MANGGA", LocationType.BARANGAY));
		Location santa_maria = locationRepository.save(new Location("SANTA MARIA", LocationType.CITY));
		Location bagbaguin = locationRepository.save(new Location("BAGBAGUIN", LocationType.BARANGAY));
		Location balasing = locationRepository.save(new Location("BALASING", LocationType.BARANGAY));
		Location buenavista = locationRepository.save(new Location("BUENAVISTA", LocationType.BARANGAY));
		Location bulac = locationRepository.save(new Location("BULAC", LocationType.BARANGAY));
		Location camangyanan = locationRepository.save(new Location("CAMANGYANAN", LocationType.BARANGAY));
		Location catmon = locationRepository.save(new Location("CATMON", LocationType.BARANGAY));
		Location caypombo = locationRepository.save(new Location("CAYPOMBO", LocationType.BARANGAY));
		Location caysio = locationRepository.save(new Location("CAYSIO", LocationType.BARANGAY));
		Location guyong = locationRepository.save(new Location("GUYONG", LocationType.BARANGAY));
		Location lalakhan = locationRepository.save(new Location("LALAKHAN", LocationType.BARANGAY));
		Location mag_asawang_sapa = locationRepository.save(new Location("MAG-ASAWANG SAPA", LocationType.BARANGAY));
		Location mahabang_parang = locationRepository.save(new Location("MAHABANG PARANG", LocationType.BARANGAY));
		Location manggahan = locationRepository.save(new Location("MANGGAHAN", LocationType.BARANGAY));
		Location parada = locationRepository.save(new Location("PARADA", LocationType.BARANGAY));
		Location pulong_buhangin = locationRepository.save(new Location("PULONG BUHANGIN", LocationType.BARANGAY));
		Location san_gabriel = locationRepository.save(new Location("SAN GABRIEL", LocationType.BARANGAY));
		Location san_jose_patag = locationRepository.save(new Location("SAN JOSE PATAG", LocationType.BARANGAY));
		Location san_vicente = locationRepository.save(new Location("SAN VICENTE", LocationType.BARANGAY));
		Location santa_clara = locationRepository.save(new Location("SANTA CLARA", LocationType.BARANGAY));
		Location santo_tomas = locationRepository.save(new Location("SANTO TOMAS", LocationType.BARANGAY));
		Location silangan = locationRepository.save(new Location("SILANGAN", LocationType.BARANGAY));
		Location tumana = locationRepository.save(new Location("TUMANA", LocationType.BARANGAY));

		Location a_mabini = locationRepository.save(new Location("A. MABINI", LocationType.BARANGAY));
		Location baesa = locationRepository.save(new Location("BAESA", LocationType.BARANGAY));
		Location bagong_barrio = locationRepository.save(new Location("BAGONG BARRIO", LocationType.BARANGAY));
		Location barrio_san_jose = locationRepository.save(new Location("BARRIO SAN JOSE", LocationType.BARANGAY));
		Location biglang_awa = locationRepository.save(new Location("BIGLANG-AWA", LocationType.BARANGAY));
		Location dagat_dagatan = locationRepository.save(new Location("DAGAT-DAGATAN", LocationType.BARANGAY));
		Location grace_park_east = locationRepository.save(new Location("GRACE PARK EAST", LocationType.BARANGAY));
		Location grace_park_west = locationRepository.save(new Location("GRACE PARK WEST", LocationType.BARANGAY));
		Location heroes_del_96 = locationRepository.save(new Location("HEROES DEL 96", LocationType.BARANGAY));
		Location kaunlaran_village = locationRepository.save(new Location("KAUNLARAN VILLAGE", LocationType.BARANGAY));
		Location libis_reparo = locationRepository.save(new Location("LIBIS REPARO", LocationType.BARANGAY));
		Location maypajo = locationRepository.save(new Location("MAYPAJO", LocationType.BARANGAY));
		Location monumento = locationRepository.save(new Location("MONUMENTO", LocationType.BARANGAY));
		Location morning_breeze = locationRepository.save(new Location("MORNING BREEZE", LocationType.BARANGAY));
		Location sangandaan = locationRepository.save(new Location("SANGANDAAN", LocationType.BARANGAY));
		Location santa_quiteria = locationRepository.save(new Location("SANTA QUITERIA", LocationType.BARANGAY));
		Location talipapa = locationRepository.save(new Location("TALIPAPA", LocationType.BARANGAY));
		Location university_hills = locationRepository.save(new Location("UNIVERSITY HILLS", LocationType.BARANGAY));
		Location almanza_dos = locationRepository.save(new Location("ALMANZA DOS", LocationType.BARANGAY));
		Location almanza_uno = locationRepository.save(new Location("ALMANZA UNO", LocationType.BARANGAY));
		Location caa_bf_international = locationRepository
				.save(new Location("CAA-BF INTERNATIONAL", LocationType.BARANGAY));
		Location daniel_fajardo = locationRepository.save(new Location("DANIEL FAJARDO", LocationType.BARANGAY));
		Location elias_aldana = locationRepository.save(new Location("ELIAS ALDANA", LocationType.BARANGAY));
		Location ilaya = locationRepository.save(new Location("ILAYA", LocationType.BARANGAY));
		Location manuyo_dos = locationRepository.save(new Location("MANUYO DOS", LocationType.BARANGAY));
		Location manuyo_uno = locationRepository.save(new Location("MANUYO UNO", LocationType.BARANGAY));
		Location pamplona_dos = locationRepository.save(new Location("PAMPLONA DOS", LocationType.BARANGAY));
		Location pamplona_tres = locationRepository.save(new Location("PAMPLONA TRES", LocationType.BARANGAY));
		Location pamplona_uno = locationRepository.save(new Location("PAMPLONA UNO", LocationType.BARANGAY));
		Location pilar_village = locationRepository.save(new Location("PILAR VILLAGE", LocationType.BARANGAY));
		Location pulang_lupa_dos = locationRepository.save(new Location("PULANG LUPA DOS", LocationType.BARANGAY));
		Location pulang_lupa_uno = locationRepository.save(new Location("PULANG LUPA UNO", LocationType.BARANGAY));
		Location talon_cinco = locationRepository.save(new Location("TALON CINCO", LocationType.BARANGAY));
		Location talon_cuatro = locationRepository.save(new Location("TALON CUATRO", LocationType.BARANGAY));
		Location talon_dos = locationRepository.save(new Location("TALON DOS", LocationType.BARANGAY));
		Location talon_tres = locationRepository.save(new Location("TALON TRES", LocationType.BARANGAY));
		Location talon_uno = locationRepository.save(new Location("TALON UNO", LocationType.BARANGAY));
		Location zapote = locationRepository.save(new Location("ZAPOTE", LocationType.BARANGAY));
		Location bangkal = locationRepository.save(new Location("BANGKAL", LocationType.BARANGAY));
		Location bel_air = locationRepository.save(new Location("BEL-AIR", LocationType.BARANGAY));
		Location carmona = locationRepository.save(new Location("CARMONA", LocationType.BARANGAY));
		Location cembo = locationRepository.save(new Location("CEMBO", LocationType.BARANGAY));
		Location comembo = locationRepository.save(new Location("COMEMBO", LocationType.BARANGAY));
		Location dasmariñas = locationRepository.save(new Location("DASMARIÃ‘AS", LocationType.BARANGAY));
		Location east_rembo = locationRepository.save(new Location("EAST REMBO", LocationType.BARANGAY));
		Location forbes_park = locationRepository.save(new Location("FORBES PARK", LocationType.BARANGAY));
		Location guadalupe_nuevo = locationRepository.save(new Location("GUADALUPE NUEVO", LocationType.BARANGAY));
		Location guadalupe_viejo = locationRepository.save(new Location("GUADALUPE VIEJO", LocationType.BARANGAY));
		Location kasilawan = locationRepository.save(new Location("KASILAWAN", LocationType.BARANGAY));
		Location la_paz = locationRepository.save(new Location("LA PAZ", LocationType.BARANGAY));
		Location magallanes = locationRepository.save(new Location("MAGALLANES", LocationType.BARANGAY));
		Location olympia = locationRepository.save(new Location("OLYMPIA", LocationType.BARANGAY));
		Location palanan = locationRepository.save(new Location("PALANAN", LocationType.BARANGAY));
		Location pembo = locationRepository.save(new Location("PEMBO", LocationType.BARANGAY));
		Location pinagkaisahan = locationRepository.save(new Location("PINAGKAISAHAN", LocationType.BARANGAY));
		Location pio_del_pilar = locationRepository.save(new Location("PIO DEL PILAR", LocationType.BARANGAY));
		Location pitogo = locationRepository.save(new Location("PITOGO", LocationType.BARANGAY));
		Location post_proper_northside = locationRepository
				.save(new Location("POST PROPER NORTHSIDE", LocationType.BARANGAY));
		Location post_proper_southside = locationRepository
				.save(new Location("POST PROPER SOUTHSIDE", LocationType.BARANGAY));
		Location rembo = locationRepository.save(new Location("REMBO", LocationType.BARANGAY));
		Location rizal = locationRepository.save(new Location("RIZAL", LocationType.BARANGAY));
		Location san_antonio = locationRepository.save(new Location("SAN ANTONIO", LocationType.BARANGAY));
		Location san_lorenzo = locationRepository.save(new Location("SAN LORENZO", LocationType.BARANGAY));
		Location singkamas = locationRepository.save(new Location("SINGKAMAS", LocationType.BARANGAY));
		Location south_cembo = locationRepository.save(new Location("SOUTH CEMBO", LocationType.BARANGAY));
		Location tejeros = locationRepository.save(new Location("TEJEROS", LocationType.BARANGAY));
		Location urdaneta = locationRepository.save(new Location("URDANETA", LocationType.BARANGAY));
		Location valenzuela = locationRepository.save(new Location("VALENZUELA", LocationType.BARANGAY));
		Location west_rembo = locationRepository.save(new Location("WEST REMBO", LocationType.BARANGAY));
		Location acacia = locationRepository.save(new Location("ACACIA", LocationType.BARANGAY));
		Location baritan = locationRepository.save(new Location("BARITAN", LocationType.BARANGAY));
		Location bayan_bayanan = locationRepository.save(new Location("BAYAN-BAYANAN", LocationType.BARANGAY));
		Location concepcion = locationRepository.save(new Location("CONCEPCION", LocationType.BARANGAY));
		Location dampalit = locationRepository.save(new Location("DAMPALIT", LocationType.BARANGAY));
		Location flores = locationRepository.save(new Location("FLORES", LocationType.BARANGAY));
		Location hulong_duhat = locationRepository.save(new Location("HULONG DUHAT", LocationType.BARANGAY));
		Location ibaba = locationRepository.save(new Location("IBABA", LocationType.BARANGAY));
		Location longos = locationRepository.save(new Location("LONGOS", LocationType.BARANGAY));
		Location maysilo = locationRepository.save(new Location("MAYSILO", LocationType.BARANGAY));
		Location niugan = locationRepository.save(new Location("NIUGAN", LocationType.BARANGAY));
		Location panghulo = locationRepository.save(new Location("PANGHULO", LocationType.BARANGAY));
		Location potrero = locationRepository.save(new Location("POTRERO", LocationType.BARANGAY));
		Location san_agustin = locationRepository.save(new Location("SAN AGUSTIN", LocationType.BARANGAY));
		Location santolan = locationRepository.save(new Location("SANTOLAN", LocationType.BARANGAY));
		Location tañong = locationRepository.save(new Location("TAÃ‘ONG", LocationType.BARANGAY));
		Location tinajeros = locationRepository.save(new Location("TINAJEROS", LocationType.BARANGAY));
		Location tonsuya = locationRepository.save(new Location("TONSUYA", LocationType.BARANGAY));
		Location tugatog = locationRepository.save(new Location("TUGATOG", LocationType.BARANGAY));
		Location addition_hills = locationRepository.save(new Location("ADDITION HILLS", LocationType.BARANGAY));
		Location bagong_silang = locationRepository.save(new Location("BAGONG SILANG", LocationType.BARANGAY));
		Location barangka_drive = locationRepository.save(new Location("BARANGKA DRIVE", LocationType.BARANGAY));
		Location barangka_ibaba = locationRepository.save(new Location("BARANGKA IBABA", LocationType.BARANGAY));
		Location barangka_ilaya = locationRepository.save(new Location("BARANGKA ILAYA", LocationType.BARANGAY));
		Location barangka_itaas = locationRepository.save(new Location("BARANGKA ITAAS", LocationType.BARANGAY));
		Location buayang_bato = locationRepository.save(new Location("BUAYANG BATO", LocationType.BARANGAY));
		Location burol = locationRepository.save(new Location("BUROL", LocationType.BARANGAY));
		Location daang_bakal = locationRepository.save(new Location("DAANG BAKAL", LocationType.BARANGAY));
		Location hagdan_bato_itaas = locationRepository.save(new Location("HAGDAN BATO ITAAS", LocationType.BARANGAY));
		Location hagdan_bato_libis = locationRepository.save(new Location("HAGDAN BATO LIBIS", LocationType.BARANGAY));
		Location harapin_ang_bukas = locationRepository.save(new Location("HARAPIN ANG BUKAS", LocationType.BARANGAY));
		Location highway_hills = locationRepository.save(new Location("HIGHWAY HILLS", LocationType.BARANGAY));
		Location hulo = locationRepository.save(new Location("HULO", LocationType.BARANGAY));
		Location mabini_j_rizal = locationRepository.save(new Location("MABINI-J. RIZAL", LocationType.BARANGAY));
		Location malamig = locationRepository.save(new Location("MALAMIG", LocationType.BARANGAY));
		Location namayan = locationRepository.save(new Location("NAMAYAN", LocationType.BARANGAY));
		Location new_zañiga = locationRepository.save(new Location("NEW ZAÃ‘IGA", LocationType.BARANGAY));
		Location old_zañiga = locationRepository.save(new Location("OLD ZAÃ‘IGA", LocationType.BARANGAY));
		Location pag_asa = locationRepository.save(new Location("PAG-ASA", LocationType.BARANGAY));
		Location plainview = locationRepository.save(new Location("PLAINVIEW", LocationType.BARANGAY));
		Location pleasant_hills = locationRepository.save(new Location("PLEASANT HILLS", LocationType.BARANGAY));
		Location san_jose = locationRepository.save(new Location("SAN JOSE", LocationType.BARANGAY));
		Location vergara = locationRepository.save(new Location("VERGARA", LocationType.BARANGAY));
		Location wack_wack_greenhills = locationRepository
				.save(new Location("WACK-WACK GREENHILLS", LocationType.BARANGAY));
		Location binondo = locationRepository.save(new Location("BINONDO", LocationType.BARANGAY));
		Location ermita = locationRepository.save(new Location("ERMITA", LocationType.BARANGAY));
		Location intramuros = locationRepository.save(new Location("INTRAMUROS", LocationType.BARANGAY));
		Location malate = locationRepository.save(new Location("MALATE", LocationType.BARANGAY));
		Location paco = locationRepository.save(new Location("PACO", LocationType.BARANGAY));
		Location pandacan = locationRepository.save(new Location("PANDACAN", LocationType.BARANGAY));
		Location port_area = locationRepository.save(new Location("PORT AREA", LocationType.BARANGAY));
		Location quiapo = locationRepository.save(new Location("QUIAPO", LocationType.BARANGAY));
		Location sampaloc = locationRepository.save(new Location("SAMPALOC", LocationType.BARANGAY));
		Location san_andres = locationRepository.save(new Location("SAN ANDRES", LocationType.BARANGAY));
		Location san_miguel = locationRepository.save(new Location("SAN MIGUEL", LocationType.BARANGAY));
		Location san_nicolas = locationRepository.save(new Location("SAN NICOLAS", LocationType.BARANGAY));
		Location santa_ana = locationRepository.save(new Location("SANTA ANA", LocationType.BARANGAY));
		Location santa_mesa = locationRepository.save(new Location("SANTA MESA", LocationType.BARANGAY));
		Location tondo = locationRepository.save(new Location("TONDO", LocationType.BARANGAY));
		Location barangka = locationRepository.save(new Location("BARANGKA", LocationType.BARANGAY));
		Location calumpang = locationRepository.save(new Location("CALUMPANG", LocationType.BARANGAY));
		Location concepcion_i_ii = locationRepository.save(new Location("CONCEPCION (I/II)", LocationType.BARANGAY));
		Location fortune = locationRepository.save(new Location("FORTUNE", LocationType.BARANGAY));
		Location industrial_valley = locationRepository.save(new Location("INDUSTRIAL VALLEY", LocationType.BARANGAY));
		Location malanday = locationRepository.save(new Location("MALANDAY", LocationType.BARANGAY));
		Location marikina_heights = locationRepository.save(new Location("MARIKINA HEIGHTS", LocationType.BARANGAY));
		Location nangka = locationRepository.save(new Location("NANGKA", LocationType.BARANGAY));
		Location parang = locationRepository.save(new Location("PARANG", LocationType.BARANGAY));
		Location santa_elena = locationRepository.save(new Location("SANTA ELENA", LocationType.BARANGAY));
		Location alabang = locationRepository.save(new Location("ALABANG", LocationType.BARANGAY));
		Location ayala_alabang = locationRepository.save(new Location("AYALA ALABANG", LocationType.BARANGAY));
		Location bayanan = locationRepository.save(new Location("BAYANAN", LocationType.BARANGAY));
		Location buli = locationRepository.save(new Location("BULI", LocationType.BARANGAY));
		Location cupang = locationRepository.save(new Location("CUPANG", LocationType.BARANGAY));
		Location putatan = locationRepository.save(new Location("PUTATAN", LocationType.BARANGAY));
		Location sucat = locationRepository.save(new Location("SUCAT", LocationType.BARANGAY));
		Location tunasan = locationRepository.save(new Location("TUNASAN", LocationType.BARANGAY));
		Location bagumbayan_north = locationRepository.save(new Location("BAGUMBAYAN NORTH", LocationType.BARANGAY));
		Location bagumbayan_south = locationRepository.save(new Location("BAGUMBAYAN SOUTH", LocationType.BARANGAY));
		Location bangkulasi = locationRepository.save(new Location("BANGKULASI", LocationType.BARANGAY));
		Location daanghari = locationRepository.save(new Location("DAANGHARI", LocationType.BARANGAY));
		Location navotas_east = locationRepository.save(new Location("NAVOTAS EAST", LocationType.BARANGAY));
		Location navotas_west = locationRepository.save(new Location("NAVOTAS WEST", LocationType.BARANGAY));
		Location northbay_boulevard_north = locationRepository
				.save(new Location("NORTHBAY BOULEVARD NORTH", LocationType.BARANGAY));
		Location northbay_boulevard_south = locationRepository
				.save(new Location("NORTHBAY BOULEVARD SOUTH", LocationType.BARANGAY));
		Location northbay_boulevard_south_1 = locationRepository
				.save(new Location("NORTHBAY BOULEVARD SOUTH 1", LocationType.BARANGAY));
		Location northbay_boulevard_south_2 = locationRepository
				.save(new Location("NORTHBAY BOULEVARD SOUTH 2", LocationType.BARANGAY));
		Location northbay_boulevard_south_3 = locationRepository
				.save(new Location("NORTHBAY BOULEVARD SOUTH 3", LocationType.BARANGAY));
		Location san_rafael_village = locationRepository
				.save(new Location("SAN RAFAEL VILLAGE", LocationType.BARANGAY));
		Location sipac_almacen = locationRepository.save(new Location("SIPAC-ALMACEN", LocationType.BARANGAY));
		Location tangos_north = locationRepository.save(new Location("TANGOS NORTH", LocationType.BARANGAY));
		Location tangos_south = locationRepository.save(new Location("TANGOS SOUTH", LocationType.BARANGAY));
		Location tanza = locationRepository.save(new Location("TANZA", LocationType.BARANGAY));
		Location baclaran = locationRepository.save(new Location("BACLARAN", LocationType.BARANGAY));
		Location bf_homes = locationRepository.save(new Location("BF HOMES", LocationType.BARANGAY));
		Location don_bosco = locationRepository.save(new Location("DON BOSCO", LocationType.BARANGAY));
		Location don_galo = locationRepository.save(new Location("DON GALO", LocationType.BARANGAY));
		Location la_huerta = locationRepository.save(new Location("LA HUERTA", LocationType.BARANGAY));
		Location marcelo_green = locationRepository.save(new Location("MARCELO GREEN", LocationType.BARANGAY));
		Location merville = locationRepository.save(new Location("MERVILLE", LocationType.BARANGAY));
		Location moonwalk = locationRepository.save(new Location("MOONWALK", LocationType.BARANGAY));
		Location san_dionisio = locationRepository.save(new Location("SAN DIONISIO", LocationType.BARANGAY));
		Location san_martin_de_porres = locationRepository
				.save(new Location("SAN MARTIN DE PORRES", LocationType.BARANGAY));
		Location sun_valley = locationRepository.save(new Location("SUN VALLEY", LocationType.BARANGAY));
		Location tambo = locationRepository.save(new Location("TAMBO", LocationType.BARANGAY));
		Location vitalez = locationRepository.save(new Location("VITALEZ", LocationType.BARANGAY));
		Location malibay = locationRepository.save(new Location("MALIBAY", LocationType.BARANGAY));
		Location manila_bay_reclamation = locationRepository
				.save(new Location("MANILA BAY RECLAMATION", LocationType.BARANGAY));
		Location maricaban = locationRepository.save(new Location("MARICABAN", LocationType.BARANGAY));
		Location picc = locationRepository.save(new Location("PICC", LocationType.BARANGAY));
		Location villamor_airbase = locationRepository.save(new Location("VILLAMOR AIRBASE", LocationType.BARANGAY));
		Location bagong_ilog = locationRepository.save(new Location("BAGONG ILOG", LocationType.BARANGAY));
		Location bagong_katipunan = locationRepository.save(new Location("BAGONG KATIPUNAN", LocationType.BARANGAY));
		Location bambang = locationRepository.save(new Location("BAMBANG", LocationType.BARANGAY));
		Location buting = locationRepository.save(new Location("BUTING", LocationType.BARANGAY));
		Location caniogan = locationRepository.save(new Location("CANIOGAN", LocationType.BARANGAY));
		Location dela_paz = locationRepository.save(new Location("DELA PAZ", LocationType.BARANGAY));
		Location kalawaan = locationRepository.save(new Location("KALAWAAN", LocationType.BARANGAY));
		Location kapasigan = locationRepository.save(new Location("KAPASIGAN", LocationType.BARANGAY));
		Location kapitolyo = locationRepository.save(new Location("KAPITOLYO", LocationType.BARANGAY));
		Location malinao = locationRepository.save(new Location("MALINAO", LocationType.BARANGAY));
		Location mauway = locationRepository.save(new Location("MAUWAY", LocationType.BARANGAY));
		Location maybunga = locationRepository.save(new Location("MAYBUNGA", LocationType.BARANGAY));
		Location oranbo = locationRepository.save(new Location("ORANBO", LocationType.BARANGAY));
		Location palatiw = locationRepository.save(new Location("PALATIW", LocationType.BARANGAY));
		Location pinagbuhatan = locationRepository.save(new Location("PINAGBUHATAN", LocationType.BARANGAY));
		Location pineda = locationRepository.save(new Location("PINEDA", LocationType.BARANGAY));
		Location rosario = locationRepository.save(new Location("ROSARIO", LocationType.BARANGAY));
		Location sagad = locationRepository.save(new Location("SAGAD", LocationType.BARANGAY));
		Location san_joaquin = locationRepository.save(new Location("SAN JOAQUIN", LocationType.BARANGAY));
		Location santa_lucia = locationRepository.save(new Location("SANTA LUCIA", LocationType.BARANGAY));
		Location santa_rosa = locationRepository.save(new Location("SANTA ROSA", LocationType.BARANGAY));
		Location sumilang = locationRepository.save(new Location("SUMILANG", LocationType.BARANGAY));
		Location ugong = locationRepository.save(new Location("UGONG", LocationType.BARANGAY));
		Location aguho = locationRepository.save(new Location("AGUHO", LocationType.BARANGAY));
		Location magtanggol = locationRepository.save(new Location("MAGTANGGOL", LocationType.BARANGAY));
		Location martires = locationRepository.save(new Location("MARTIRES", LocationType.BARANGAY));
		Location santo_rosario_kanluran = locationRepository
				.save(new Location("SANTO ROSARIO KANLURAN", LocationType.BARANGAY));
		Location santo_rosario_silangan = locationRepository
				.save(new Location("SANTO ROSARIO SILANGAN", LocationType.BARANGAY));
		Location tabacalera = locationRepository.save(new Location("TABACALERA", LocationType.BARANGAY));
		Location alicia = locationRepository.save(new Location("ALICIA", LocationType.BARANGAY));
		Location amihan = locationRepository.save(new Location("AMIHAN", LocationType.BARANGAY));
		Location apolonio_samson = locationRepository.save(new Location("APOLONIO SAMSON", LocationType.BARANGAY));
		Location bagbag = locationRepository.save(new Location("BAGBAG", LocationType.BARANGAY));
		Location bagong_lipunan_ng_crame = locationRepository
				.save(new Location("BAGONG LIPUNAN NG CRAME", LocationType.BARANGAY));
		Location bagong_pag_asa = locationRepository.save(new Location("BAGONG PAG-ASA", LocationType.BARANGAY));
		Location bagong_silangan = locationRepository.save(new Location("BAGONG SILANGAN", LocationType.BARANGAY));
		Location bagumbayan = locationRepository.save(new Location("BAGUMBAYAN", LocationType.BARANGAY));
		Location bagumbuhay = locationRepository.save(new Location("BAGUMBUHAY", LocationType.BARANGAY));
		Location bahay_toro = locationRepository.save(new Location("BAHAY TORO", LocationType.BARANGAY));
		Location balingasa = locationRepository.save(new Location("BALINGASA", LocationType.BARANGAY));
		Location balonbato = locationRepository.save(new Location("BALONBATO", LocationType.BARANGAY));
		Location batasan_hills = locationRepository.save(new Location("BATASAN HILLS", LocationType.BARANGAY));
		Location bayanihan = locationRepository.save(new Location("BAYANIHAN", LocationType.BARANGAY));
		Location blue_ridge_a_b = locationRepository.save(new Location("BLUE RIDGE (A/B)", LocationType.BARANGAY));
		Location botocan = locationRepository.save(new Location("BOTOCAN", LocationType.BARANGAY));
		Location bungad = locationRepository.save(new Location("BUNGAD", LocationType.BARANGAY));
		Location camp_aguinaldo = locationRepository.save(new Location("CAMP AGUINALDO", LocationType.BARANGAY));
		Location capri = locationRepository.save(new Location("CAPRI", LocationType.BARANGAY));
		Location central = locationRepository.save(new Location("CENTRAL", LocationType.BARANGAY));
		Location commonwealth = locationRepository.save(new Location("COMMONWEALTH", LocationType.BARANGAY));
		Location culiat = locationRepository.save(new Location("CULIAT", LocationType.BARANGAY));
		Location damar = locationRepository.save(new Location("DAMAR", LocationType.BARANGAY));
		Location damayang_lagi = locationRepository.save(new Location("DAMAYANG LAGI", LocationType.BARANGAY));
		Location del_monte = locationRepository.save(new Location("DEL MONTE", LocationType.BARANGAY));
		Location dioquino_zobel = locationRepository.save(new Location("DIOQUINO ZOBEL", LocationType.BARANGAY));
		Location don_manuel = locationRepository.save(new Location("DON MANUEL", LocationType.BARANGAY));
		Location doña_aurora = locationRepository.save(new Location("DOÃ‘A AURORA", LocationType.BARANGAY));
		Location doña_imelda = locationRepository.save(new Location("DOÃ‘A IMELDA", LocationType.BARANGAY));
		Location doña_josefa = locationRepository.save(new Location("DOÃ‘A JOSEFA", LocationType.BARANGAY));
		Location duyan_duyan = locationRepository.save(new Location("DUYAN DUYAN", LocationType.BARANGAY));
		Location e_rodriguez = locationRepository.save(new Location("E. RODRIGUEZ", LocationType.BARANGAY));
		Location east_kamias = locationRepository.save(new Location("EAST KAMIAS", LocationType.BARANGAY));
		Location escopa_i_iv = locationRepository.save(new Location("ESCOPA (I-IV)", LocationType.BARANGAY));
		Location fairview = locationRepository.save(new Location("FAIRVIEW", LocationType.BARANGAY));
		Location greater_lagro = locationRepository.save(new Location("GREATER LAGRO", LocationType.BARANGAY));
		Location gulod = locationRepository.save(new Location("GULOD", LocationType.BARANGAY));
		Location holy_spirit = locationRepository.save(new Location("HOLY SPIRIT", LocationType.BARANGAY));
		Location horseshoe = locationRepository.save(new Location("HORSESHOE", LocationType.BARANGAY));
		Location immaculate_concepcion = locationRepository
				.save(new Location("IMMACULATE CONCEPCION", LocationType.BARANGAY));
		Location kaligayahan = locationRepository.save(new Location("KALIGAYAHAN", LocationType.BARANGAY));
		Location kalusugan = locationRepository.save(new Location("KALUSUGAN", LocationType.BARANGAY));
		Location kamuning = locationRepository.save(new Location("KAMUNING", LocationType.BARANGAY));
		Location katipunan = locationRepository.save(new Location("KATIPUNAN", LocationType.BARANGAY));
		Location kaunlaran = locationRepository.save(new Location("KAUNLARAN", LocationType.BARANGAY));
		Location kristong_hari = locationRepository.save(new Location("KRISTONG HARI", LocationType.BARANGAY));
		Location krus_na_ligas = locationRepository.save(new Location("KRUS NA LIGAS", LocationType.BARANGAY));
		Location laging_handa = locationRepository.save(new Location("LAGING HANDA", LocationType.BARANGAY));
		Location libis = locationRepository.save(new Location("LIBIS", LocationType.BARANGAY));
		Location lourdes = locationRepository.save(new Location("LOURDES", LocationType.BARANGAY));
		Location loyola_heights = locationRepository.save(new Location("LOYOLA HEIGHTS", LocationType.BARANGAY));
		Location malaya = locationRepository.save(new Location("MALAYA", LocationType.BARANGAY));
		Location mangga = locationRepository.save(new Location("MANGGA", LocationType.BARANGAY));
		Location manresa = locationRepository.save(new Location("MANRESA", LocationType.BARANGAY));
		Location mariana = locationRepository.save(new Location("MARIANA", LocationType.BARANGAY));
		Location mariblo = locationRepository.save(new Location("MARIBLO", LocationType.BARANGAY));
		Location marilag = locationRepository.save(new Location("MARILAG", LocationType.BARANGAY));
		Location masagana = locationRepository.save(new Location("MASAGANA", LocationType.BARANGAY));
		Location masambong = locationRepository.save(new Location("MASAMBONG", LocationType.BARANGAY));
		Location matandang_balara = locationRepository.save(new Location("MATANDANG BALARA", LocationType.BARANGAY));
		Location milagrosa = locationRepository.save(new Location("MILAGROSA", LocationType.BARANGAY));
		Location ns_amoranto = locationRepository.save(new Location("N.S. AMORANTO", LocationType.BARANGAY));
		Location nagkaisang_nayon = locationRepository.save(new Location("NAGKAISANG NAYON", LocationType.BARANGAY));
		Location nayong_kanluran = locationRepository.save(new Location("NAYONG KANLURAN", LocationType.BARANGAY));
		Location new_era = locationRepository.save(new Location("NEW ERA", LocationType.BARANGAY));
		Location north_fairview = locationRepository.save(new Location("NORTH FAIRVIEW", LocationType.BARANGAY));
		Location novaliches = locationRepository.save(new Location("NOVALICHES", LocationType.BARANGAY));
		Location obrero = locationRepository.save(new Location("OBRERO", LocationType.BARANGAY));
		Location old_capitol_site = locationRepository.save(new Location("OLD CAPITOL SITE", LocationType.BARANGAY));
		Location paang_bundok = locationRepository.save(new Location("PAANG BUNDOK", LocationType.BARANGAY));
		Location pag_ibig_sa_nayon = locationRepository.save(new Location("PAG-IBIG SA NAYON", LocationType.BARANGAY));
		Location paligsahan = locationRepository.save(new Location("PALIGSAHAN", LocationType.BARANGAY));
		Location paltok = locationRepository.save(new Location("PALTOK", LocationType.BARANGAY));
		Location pansol = locationRepository.save(new Location("PANSOL", LocationType.BARANGAY));
		Location paraiso = locationRepository.save(new Location("PARAISO", LocationType.BARANGAY));
		Location pasong_putik = locationRepository.save(new Location("PASONG PUTIK", LocationType.BARANGAY));
		Location pasong_tamo = locationRepository.save(new Location("PASONG TAMO", LocationType.BARANGAY));
		Location payatas = locationRepository.save(new Location("PAYATAS", LocationType.BARANGAY));
		Location phil_am = locationRepository.save(new Location("PHIL-AM", LocationType.BARANGAY));
		Location pinyahan = locationRepository.save(new Location("PINYAHAN", LocationType.BARANGAY));
		Location project_6 = locationRepository.save(new Location("PROJECT 6", LocationType.BARANGAY));
		Location quirino_2_a_c = locationRepository.save(new Location("QUIRINO 2 (A-C)", LocationType.BARANGAY));
		Location quirino_3_a_b = locationRepository.save(new Location("QUIRINO 3 (A-B)", LocationType.BARANGAY));
		Location roxas = locationRepository.save(new Location("ROXAS", LocationType.BARANGAY));
		Location sacred_heart = locationRepository.save(new Location("SACRED HEART", LocationType.BARANGAY));
		Location salvacion = locationRepository.save(new Location("SALVACION", LocationType.BARANGAY));
		Location san_bartolome = locationRepository.save(new Location("SAN BARTOLOME", LocationType.BARANGAY));
		Location san_isidro_galas = locationRepository.save(new Location("SAN ISIDRO GALAS", LocationType.BARANGAY));
		Location san_isidro_labrador = locationRepository
				.save(new Location("SAN ISIDRO LABRADOR", LocationType.BARANGAY));
		Location santa_monica = locationRepository.save(new Location("SANTA MONICA", LocationType.BARANGAY));
		Location santa_teresita = locationRepository.save(new Location("SANTA TERESITA", LocationType.BARANGAY));
		Location santo_domingo = locationRepository.save(new Location("SANTO DOMINGO", LocationType.BARANGAY));
		Location santol = locationRepository.save(new Location("SANTOL", LocationType.BARANGAY));
		Location sauyo = locationRepository.save(new Location("SAUYO", LocationType.BARANGAY));
		Location siena = locationRepository.save(new Location("SIENA", LocationType.BARANGAY));
		Location sikatuna_village = locationRepository.save(new Location("SIKATUNA VILLAGE", LocationType.BARANGAY));
		Location socorro_cubao = locationRepository.save(new Location("SOCORRO (CUBAO)", LocationType.BARANGAY));
		Location st_ignatius = locationRepository.save(new Location("ST. IGNATIUS", LocationType.BARANGAY));
		Location st_peter = locationRepository.save(new Location("ST. PETER", LocationType.BARANGAY));
		Location tagumpay = locationRepository.save(new Location("TAGUMPAY", LocationType.BARANGAY));
		Location talayan = locationRepository.save(new Location("TALAYAN", LocationType.BARANGAY));
		Location tandang_sora = locationRepository.save(new Location("TANDANG SORA", LocationType.BARANGAY));
		Location tatalon = locationRepository.save(new Location("TATALON", LocationType.BARANGAY));
		Location teachers_village = locationRepository.save(new Location("TEACHER'S VILLAGE", LocationType.BARANGAY));
		Location ugong_norte = locationRepository.save(new Location("UGONG NORTE", LocationType.BARANGAY));
		Location unang_sigaw = locationRepository.save(new Location("UNANG SIGAW", LocationType.BARANGAY));
		Location up_campus = locationRepository.save(new Location("UP CAMPUS", LocationType.BARANGAY));
		Location up_village = locationRepository.save(new Location("UP VILLAGE", LocationType.BARANGAY));
		Location valencia = locationRepository.save(new Location("VALENCIA", LocationType.BARANGAY));
		Location vasra = locationRepository.save(new Location("VASRA", LocationType.BARANGAY));
		Location veterans_village = locationRepository.save(new Location("VETERANS VILLAGE", LocationType.BARANGAY));
		Location villa_maria_clara = locationRepository.save(new Location("VILLA MARIA CLARA", LocationType.BARANGAY));
		Location west_kamias = locationRepository.save(new Location("WEST KAMIAS", LocationType.BARANGAY));
		Location west_triangle = locationRepository.save(new Location("WEST TRIANGLE", LocationType.BARANGAY));
		Location white_plains = locationRepository.save(new Location("WHITE PLAINS", LocationType.BARANGAY));
		Location balong_bato = locationRepository.save(new Location("BALONG-BATO", LocationType.BARANGAY));
		Location batis = locationRepository.save(new Location("BATIS", LocationType.BARANGAY));
		Location corazon_de_jesus = locationRepository.save(new Location("CORAZON DE JESUS", LocationType.BARANGAY));
		Location ermitaño = locationRepository.save(new Location("ERMITAÃ‘O", LocationType.BARANGAY));
		Location greenhills = locationRepository.save(new Location("GREENHILLS", LocationType.BARANGAY));
		Location isabelita = locationRepository.save(new Location("ISABELITA", LocationType.BARANGAY));
		Location kabayanan = locationRepository.save(new Location("KABAYANAN", LocationType.BARANGAY));
		Location little_baguio = locationRepository.save(new Location("LITTLE BAGUIO", LocationType.BARANGAY));
		Location maytunas = locationRepository.save(new Location("MAYTUNAS", LocationType.BARANGAY));
		Location onse = locationRepository.save(new Location("ONSE", LocationType.BARANGAY));
		Location pasadena = locationRepository.save(new Location("PASADENA", LocationType.BARANGAY));
		Location pedro_cruz = locationRepository.save(new Location("PEDRO CRUZ", LocationType.BARANGAY));
		Location progreso = locationRepository.save(new Location("PROGRESO", LocationType.BARANGAY));
		Location rivera = locationRepository.save(new Location("RIVERA", LocationType.BARANGAY));
		Location salapan = locationRepository.save(new Location("SALAPAN", LocationType.BARANGAY));
		Location san_perfecto = locationRepository.save(new Location("SAN PERFECTO", LocationType.BARANGAY));
		Location st_joseph = locationRepository.save(new Location("ST. JOSEPH", LocationType.BARANGAY));
		Location tibagan = locationRepository.save(new Location("TIBAGAN", LocationType.BARANGAY));
		Location west_crame = locationRepository.save(new Location("WEST CRAME", LocationType.BARANGAY));
		Location calzada = locationRepository.save(new Location("CALZADA", LocationType.BARANGAY));
		Location central_bicutan = locationRepository.save(new Location("CENTRAL BICUTAN", LocationType.BARANGAY));
		Location central_signal_village = locationRepository
				.save(new Location("CENTRAL SIGNAL VILLAGE", LocationType.BARANGAY));
		Location fort_bonifacio = locationRepository.save(new Location("FORT BONIFACIO", LocationType.BARANGAY));
		Location hagonoy = locationRepository.save(new Location("HAGONOY", LocationType.BARANGAY));
		Location ibayo_tipas = locationRepository.save(new Location("IBAYO TIPAS", LocationType.BARANGAY));
		Location katuparan = locationRepository.save(new Location("KATUPARAN", LocationType.BARANGAY));
		Location ligid_tipas = locationRepository.save(new Location("LIGID TIPAS", LocationType.BARANGAY));
		Location lower_bicutan = locationRepository.save(new Location("LOWER BICUTAN", LocationType.BARANGAY));
		Location maharlika_village = locationRepository.save(new Location("MAHARLIKA VILLAGE", LocationType.BARANGAY));
		Location napindan = locationRepository.save(new Location("NAPINDAN", LocationType.BARANGAY));
		Location new_lower_bicutan = locationRepository.save(new Location("NEW LOWER BICUTAN", LocationType.BARANGAY));
		Location north_daang_hari = locationRepository.save(new Location("NORTH DAANG HARI", LocationType.BARANGAY));
		Location north_signal_village = locationRepository
				.save(new Location("NORTH SIGNAL VILLAGE", LocationType.BARANGAY));
		Location palingon = locationRepository.save(new Location("PALINGON", LocationType.BARANGAY));
		Location pinagsama = locationRepository.save(new Location("PINAGSAMA", LocationType.BARANGAY));
		Location south_daang_hari = locationRepository.save(new Location("SOUTH DAANG HARI", LocationType.BARANGAY));
		Location south_signal_village = locationRepository
				.save(new Location("SOUTH SIGNAL VILLAGE", LocationType.BARANGAY));
		Location tanyag = locationRepository.save(new Location("TANYAG", LocationType.BARANGAY));
		Location tuktukan = locationRepository.save(new Location("TUKTUKAN", LocationType.BARANGAY));
		Location upper_bicutan = locationRepository.save(new Location("UPPER BICUTAN", LocationType.BARANGAY));
		Location ususan = locationRepository.save(new Location("USUSAN", LocationType.BARANGAY));
		Location wawa = locationRepository.save(new Location("WAWA", LocationType.BARANGAY));
		Location western_bicutan = locationRepository.save(new Location("WESTERN BICUTAN", LocationType.BARANGAY));
		Location arkong_bato = locationRepository.save(new Location("ARKONG BATO", LocationType.BARANGAY));
		Location balangkas = locationRepository.save(new Location("BALANGKAS", LocationType.BARANGAY));
		Location bignay = locationRepository.save(new Location("BIGNAY", LocationType.BARANGAY));
		Location bisig = locationRepository.save(new Location("BISIG", LocationType.BARANGAY));
		Location canumay = locationRepository.save(new Location("CANUMAY", LocationType.BARANGAY));
		Location coloong = locationRepository.save(new Location("COLOONG", LocationType.BARANGAY));
		Location dalandanan = locationRepository.save(new Location("DALANDANAN", LocationType.BARANGAY));
		Location general_t_de_leon = locationRepository.save(new Location("GENERAL T. DE LEON", LocationType.BARANGAY));
		Location isla = locationRepository.save(new Location("ISLA", LocationType.BARANGAY));
		Location karuhatan = locationRepository.save(new Location("KARUHATAN", LocationType.BARANGAY));
		Location lawang_bato = locationRepository.save(new Location("LAWANG BATO", LocationType.BARANGAY));
		Location lingunan = locationRepository.save(new Location("LINGUNAN", LocationType.BARANGAY));
		Location mabolo = locationRepository.save(new Location("MABOLO", LocationType.BARANGAY));
		Location malinta = locationRepository.save(new Location("MALINTA", LocationType.BARANGAY));
		Location mapulang_lupa = locationRepository.save(new Location("MAPULANG LUPA", LocationType.BARANGAY));
		Location marulas = locationRepository.save(new Location("MARULAS", LocationType.BARANGAY));
		Location maysan = locationRepository.save(new Location("MAYSAN", LocationType.BARANGAY));
		Location palasan = locationRepository.save(new Location("PALASAN", LocationType.BARANGAY));
		Location pariancillo_villa = locationRepository.save(new Location("PARIANCILLO VILLA", LocationType.BARANGAY));
		Location paso_de_blas = locationRepository.save(new Location("PASO DE BLAS", LocationType.BARANGAY));
		Location pasolo = locationRepository.save(new Location("PASOLO", LocationType.BARANGAY));
		Location polo = locationRepository.save(new Location("POLO", LocationType.BARANGAY));
		Location punturin = locationRepository.save(new Location("PUNTURIN", LocationType.BARANGAY));
		Location rincon = locationRepository.save(new Location("RINCON", LocationType.BARANGAY));
		Location tagalag = locationRepository.save(new Location("TAGALAG", LocationType.BARANGAY));
		Location veinte_reales = locationRepository.save(new Location("VEINTE REALES", LocationType.BARANGAY));
		Location camarin = locationRepository.save(new Location("CAMARIN", LocationType.BARANGAY));

		Location caloocan = locationRepository.save(new Location("CALOOCAN", LocationType.CITY));
		Location las_piñas = locationRepository.save(new Location("LAS PIÃ‘AS", LocationType.CITY));
		Location makati = locationRepository.save(new Location("MAKATI", LocationType.CITY));
		Location malabon = locationRepository.save(new Location("MALABON", LocationType.CITY));
		Location mandaluyong = locationRepository.save(new Location("MANDALUYONG", LocationType.CITY));
		Location manila = locationRepository.save(new Location("MANILA", LocationType.CITY));
		Location marikina = locationRepository.save(new Location("MARIKINA", LocationType.CITY));
		Location muntinlupa = locationRepository.save(new Location("MUNTINLUPA", LocationType.CITY));
		Location navotas = locationRepository.save(new Location("NAVOTAS", LocationType.CITY));
		Location parañaque = locationRepository.save(new Location("PARAÃ‘AQUE", LocationType.CITY));
		Location pasay = locationRepository.save(new Location("PASAY", LocationType.CITY));
		Location pasig = locationRepository.save(new Location("PASIG", LocationType.CITY));
		Location pateros = locationRepository.save(new Location("PATEROS", LocationType.CITY));
		Location quezon_city = locationRepository.save(new Location("QUEZON CITY", LocationType.CITY));
		Location san_juan = locationRepository.save(new Location("SAN JUAN", LocationType.CITY));
		Location taguig = locationRepository.save(new Location("TAGUIG", LocationType.CITY));
		Location valenzuela_city = locationRepository.save(new Location("VALENZUELA CITY", LocationType.CITY));

		Location metro_manila = locationRepository.save(new Location("METRO MANILA", LocationType.PROVINCE));
		locationTreeRepository.save(new LocationTree(caloocan, metro_manila));
		locationTreeRepository.save(new LocationTree(las_piñas, metro_manila));
		locationTreeRepository.save(new LocationTree(makati, metro_manila));
		locationTreeRepository.save(new LocationTree(malabon, metro_manila));
		locationTreeRepository.save(new LocationTree(mandaluyong, metro_manila));
		locationTreeRepository.save(new LocationTree(manila, metro_manila));
		locationTreeRepository.save(new LocationTree(marikina, metro_manila));
		locationTreeRepository.save(new LocationTree(muntinlupa, metro_manila));
		locationTreeRepository.save(new LocationTree(navotas, metro_manila));
		locationTreeRepository.save(new LocationTree(parañaque, metro_manila));
		locationTreeRepository.save(new LocationTree(pasay, metro_manila));
		locationTreeRepository.save(new LocationTree(pasig, metro_manila));
		locationTreeRepository.save(new LocationTree(pateros, metro_manila));
		locationTreeRepository.save(new LocationTree(quezon_city, metro_manila));
		locationTreeRepository.save(new LocationTree(san_juan, metro_manila));
		locationTreeRepository.save(new LocationTree(taguig, metro_manila));
		locationTreeRepository.save(new LocationTree(valenzuela_city, metro_manila));

		locationTreeRepository.save(new LocationTree(san_jose_del_monte, bulacan));
		locationTreeRepository.save(new LocationTree(santa_maria, bulacan));
		locationTreeRepository.save(new LocationTree(citrus, san_jose_del_monte));
		locationTreeRepository.save(new LocationTree(ciudad_real, san_jose_del_monte));
		locationTreeRepository.save(new LocationTree(dulong_bayan, san_jose_del_monte));
		locationTreeRepository.save(new LocationTree(fatima, san_jose_del_monte));
		locationTreeRepository.save(new LocationTree(francisco_homes, san_jose_del_monte));
		locationTreeRepository.save(new LocationTree(gaya_gaya, san_jose_del_monte));
		locationTreeRepository.save(new LocationTree(graceville, san_jose_del_monte));
		locationTreeRepository.save(new LocationTree(gumaoc, san_jose_del_monte));
		locationTreeRepository.save(new LocationTree(kaybanban, san_jose_del_monte));
		locationTreeRepository.save(new LocationTree(kaypian, san_jose_del_monte));
		locationTreeRepository.save(new LocationTree(lawang_pare, san_jose_del_monte));
		locationTreeRepository.save(new LocationTree(minuyan, san_jose_del_monte));
		locationTreeRepository.save(new LocationTree(muzon, san_jose_del_monte));
		locationTreeRepository.save(new LocationTree(paradise, san_jose_del_monte));
		locationTreeRepository.save(new LocationTree(poblacion, san_jose_del_monte));
		locationTreeRepository.save(new LocationTree(san_isidro, san_jose_del_monte));
		locationTreeRepository.save(new LocationTree(san_manuel, san_jose_del_monte));
		locationTreeRepository.save(new LocationTree(san_martin, san_jose_del_monte));
		locationTreeRepository.save(new LocationTree(san_pedro, san_jose_del_monte));
		locationTreeRepository.save(new LocationTree(san_rafael, san_jose_del_monte));
		locationTreeRepository.save(new LocationTree(san_roque, san_jose_del_monte));
		locationTreeRepository.save(new LocationTree(sapang_palay, san_jose_del_monte));
		locationTreeRepository.save(new LocationTree(saint_martin_de_porres, san_jose_del_monte));
		locationTreeRepository.save(new LocationTree(santa_cruz, san_jose_del_monte));
		locationTreeRepository.save(new LocationTree(santo_cristo, san_jose_del_monte));
		locationTreeRepository.save(new LocationTree(santo_niño, san_jose_del_monte));
		locationTreeRepository.save(new LocationTree(tungkong_mangga, san_jose_del_monte));
		locationTreeRepository.save(new LocationTree(maharlika, san_jose_del_monte));
		locationTreeRepository.save(new LocationTree(assumption, san_jose_del_monte));
		locationTreeRepository.save(new LocationTree(bagong_buhay, san_jose_del_monte));
		locationTreeRepository.save(new LocationTree(balasing, santa_maria));
		locationTreeRepository.save(new LocationTree(buenavista, santa_maria));
		locationTreeRepository.save(new LocationTree(bulac, santa_maria));
		locationTreeRepository.save(new LocationTree(camangyanan, santa_maria));
		locationTreeRepository.save(new LocationTree(catmon, santa_maria));
		locationTreeRepository.save(new LocationTree(caypombo, santa_maria));
		locationTreeRepository.save(new LocationTree(caysio, santa_maria));
		locationTreeRepository.save(new LocationTree(guyong, santa_maria));
		locationTreeRepository.save(new LocationTree(lalakhan, santa_maria));
		locationTreeRepository.save(new LocationTree(mag_asawang_sapa, santa_maria));
		locationTreeRepository.save(new LocationTree(mahabang_parang, santa_maria));
		locationTreeRepository.save(new LocationTree(manggahan, santa_maria));
		locationTreeRepository.save(new LocationTree(parada, santa_maria));
		locationTreeRepository.save(new LocationTree(poblacion, santa_maria));
		locationTreeRepository.save(new LocationTree(pulong_buhangin, santa_maria));
		locationTreeRepository.save(new LocationTree(san_gabriel, santa_maria));
		locationTreeRepository.save(new LocationTree(san_jose_patag, santa_maria));
		locationTreeRepository.save(new LocationTree(san_vicente, santa_maria));
		locationTreeRepository.save(new LocationTree(santa_clara, santa_maria));
		locationTreeRepository.save(new LocationTree(santa_cruz, santa_maria));
		locationTreeRepository.save(new LocationTree(santo_tomas, santa_maria));
		locationTreeRepository.save(new LocationTree(silangan, santa_maria));
		locationTreeRepository.save(new LocationTree(tumana, santa_maria));
		locationTreeRepository.save(new LocationTree(bagbaguin, santa_maria));

		locationTreeRepository.save(new LocationTree(a_mabini, caloocan));
		locationTreeRepository.save(new LocationTree(baesa, caloocan));
		locationTreeRepository.save(new LocationTree(bagong_barrio, caloocan));
		locationTreeRepository.save(new LocationTree(barrio_san_jose, caloocan));
		locationTreeRepository.save(new LocationTree(biglang_awa, caloocan));
		locationTreeRepository.save(new LocationTree(dagat_dagatan, caloocan));
		locationTreeRepository.save(new LocationTree(grace_park_east, caloocan));
		locationTreeRepository.save(new LocationTree(grace_park_west, caloocan));
		locationTreeRepository.save(new LocationTree(heroes_del_96, caloocan));
		locationTreeRepository.save(new LocationTree(kaunlaran_village, caloocan));
		locationTreeRepository.save(new LocationTree(libis_reparo, caloocan));
		locationTreeRepository.save(new LocationTree(maypajo, caloocan));
		locationTreeRepository.save(new LocationTree(monumento, caloocan));
		locationTreeRepository.save(new LocationTree(morning_breeze, caloocan));
		locationTreeRepository.save(new LocationTree(sangandaan, caloocan));
		locationTreeRepository.save(new LocationTree(santa_quiteria, caloocan));
		locationTreeRepository.save(new LocationTree(talipapa, caloocan));
		locationTreeRepository.save(new LocationTree(university_hills, caloocan));
		locationTreeRepository.save(new LocationTree(almanza_dos, las_piñas));
		locationTreeRepository.save(new LocationTree(almanza_uno, las_piñas));
		locationTreeRepository.save(new LocationTree(caa_bf_international, las_piñas));
		locationTreeRepository.save(new LocationTree(daniel_fajardo, las_piñas));
		locationTreeRepository.save(new LocationTree(elias_aldana, las_piñas));
		locationTreeRepository.save(new LocationTree(ilaya, las_piñas));
		locationTreeRepository.save(new LocationTree(manuyo_dos, las_piñas));
		locationTreeRepository.save(new LocationTree(manuyo_uno, las_piñas));
		locationTreeRepository.save(new LocationTree(pamplona_dos, las_piñas));
		locationTreeRepository.save(new LocationTree(pamplona_tres, las_piñas));
		locationTreeRepository.save(new LocationTree(pamplona_uno, las_piñas));
		locationTreeRepository.save(new LocationTree(pilar_village, las_piñas));
		locationTreeRepository.save(new LocationTree(pulang_lupa_dos, las_piñas));
		locationTreeRepository.save(new LocationTree(pulang_lupa_uno, las_piñas));
		locationTreeRepository.save(new LocationTree(talon_cinco, las_piñas));
		locationTreeRepository.save(new LocationTree(talon_cuatro, las_piñas));
		locationTreeRepository.save(new LocationTree(talon_dos, las_piñas));
		locationTreeRepository.save(new LocationTree(talon_tres, las_piñas));
		locationTreeRepository.save(new LocationTree(talon_uno, las_piñas));
		locationTreeRepository.save(new LocationTree(zapote, las_piñas));
		locationTreeRepository.save(new LocationTree(bangkal, makati));
		locationTreeRepository.save(new LocationTree(bel_air, makati));
		locationTreeRepository.save(new LocationTree(carmona, makati));
		locationTreeRepository.save(new LocationTree(cembo, makati));
		locationTreeRepository.save(new LocationTree(comembo, makati));
		locationTreeRepository.save(new LocationTree(dasmariñas, makati));
		locationTreeRepository.save(new LocationTree(east_rembo, makati));
		locationTreeRepository.save(new LocationTree(forbes_park, makati));
		locationTreeRepository.save(new LocationTree(guadalupe_nuevo, makati));
		locationTreeRepository.save(new LocationTree(guadalupe_viejo, makati));
		locationTreeRepository.save(new LocationTree(kasilawan, makati));
		locationTreeRepository.save(new LocationTree(la_paz, makati));
		locationTreeRepository.save(new LocationTree(magallanes, makati));
		locationTreeRepository.save(new LocationTree(olympia, makati));
		locationTreeRepository.save(new LocationTree(palanan, makati));
		locationTreeRepository.save(new LocationTree(pembo, makati));
		locationTreeRepository.save(new LocationTree(pinagkaisahan, makati));
		locationTreeRepository.save(new LocationTree(pio_del_pilar, makati));
		locationTreeRepository.save(new LocationTree(pitogo, makati));
		locationTreeRepository.save(new LocationTree(poblacion, makati));
		locationTreeRepository.save(new LocationTree(post_proper_northside, makati));
		locationTreeRepository.save(new LocationTree(post_proper_southside, makati));
		locationTreeRepository.save(new LocationTree(rembo, makati));
		locationTreeRepository.save(new LocationTree(rizal, makati));
		locationTreeRepository.save(new LocationTree(san_antonio, makati));
		locationTreeRepository.save(new LocationTree(san_isidro, makati));
		locationTreeRepository.save(new LocationTree(san_lorenzo, makati));
		locationTreeRepository.save(new LocationTree(santa_cruz, makati));
		locationTreeRepository.save(new LocationTree(singkamas, makati));
		locationTreeRepository.save(new LocationTree(south_cembo, makati));
		locationTreeRepository.save(new LocationTree(tejeros, makati));
		locationTreeRepository.save(new LocationTree(urdaneta, makati));
		locationTreeRepository.save(new LocationTree(valenzuela, makati));
		locationTreeRepository.save(new LocationTree(west_rembo, makati));
		locationTreeRepository.save(new LocationTree(acacia, malabon));
		locationTreeRepository.save(new LocationTree(baritan, malabon));
		locationTreeRepository.save(new LocationTree(bayan_bayanan, malabon));
		locationTreeRepository.save(new LocationTree(catmon, malabon));
		locationTreeRepository.save(new LocationTree(concepcion, malabon));
		locationTreeRepository.save(new LocationTree(dampalit, malabon));
		locationTreeRepository.save(new LocationTree(flores, malabon));
		locationTreeRepository.save(new LocationTree(hulong_duhat, malabon));
		locationTreeRepository.save(new LocationTree(ibaba, malabon));
		locationTreeRepository.save(new LocationTree(longos, malabon));
		locationTreeRepository.save(new LocationTree(maysilo, malabon));
		locationTreeRepository.save(new LocationTree(muzon, malabon));
		locationTreeRepository.save(new LocationTree(niugan, malabon));
		locationTreeRepository.save(new LocationTree(panghulo, malabon));
		locationTreeRepository.save(new LocationTree(potrero, malabon));
		locationTreeRepository.save(new LocationTree(san_agustin, malabon));
		locationTreeRepository.save(new LocationTree(santolan, malabon));
		locationTreeRepository.save(new LocationTree(tañong, malabon));
		locationTreeRepository.save(new LocationTree(tinajeros, malabon));
		locationTreeRepository.save(new LocationTree(tonsuya, malabon));
		locationTreeRepository.save(new LocationTree(tugatog, malabon));
		locationTreeRepository.save(new LocationTree(addition_hills, mandaluyong));
		locationTreeRepository.save(new LocationTree(bagong_silang, mandaluyong));
		locationTreeRepository.save(new LocationTree(barangka_drive, mandaluyong));
		locationTreeRepository.save(new LocationTree(barangka_ibaba, mandaluyong));
		locationTreeRepository.save(new LocationTree(barangka_ilaya, mandaluyong));
		locationTreeRepository.save(new LocationTree(barangka_itaas, mandaluyong));
		locationTreeRepository.save(new LocationTree(buayang_bato, mandaluyong));
		locationTreeRepository.save(new LocationTree(burol, mandaluyong));
		locationTreeRepository.save(new LocationTree(daang_bakal, mandaluyong));
		locationTreeRepository.save(new LocationTree(hagdan_bato_itaas, mandaluyong));
		locationTreeRepository.save(new LocationTree(hagdan_bato_libis, mandaluyong));
		locationTreeRepository.save(new LocationTree(harapin_ang_bukas, mandaluyong));
		locationTreeRepository.save(new LocationTree(highway_hills, mandaluyong));
		locationTreeRepository.save(new LocationTree(hulo, mandaluyong));
		locationTreeRepository.save(new LocationTree(mabini_j_rizal, mandaluyong));
		locationTreeRepository.save(new LocationTree(malamig, mandaluyong));
		locationTreeRepository.save(new LocationTree(namayan, mandaluyong));
		locationTreeRepository.save(new LocationTree(new_zañiga, mandaluyong));
		locationTreeRepository.save(new LocationTree(old_zañiga, mandaluyong));
		locationTreeRepository.save(new LocationTree(pag_asa, mandaluyong));
		locationTreeRepository.save(new LocationTree(plainview, mandaluyong));
		locationTreeRepository.save(new LocationTree(pleasant_hills, mandaluyong));
		locationTreeRepository.save(new LocationTree(poblacion, mandaluyong));
		locationTreeRepository.save(new LocationTree(san_jose, mandaluyong));
		locationTreeRepository.save(new LocationTree(vergara, mandaluyong));
		locationTreeRepository.save(new LocationTree(wack_wack_greenhills, mandaluyong));
		locationTreeRepository.save(new LocationTree(binondo, manila));
		locationTreeRepository.save(new LocationTree(ermita, manila));
		locationTreeRepository.save(new LocationTree(intramuros, manila));
		locationTreeRepository.save(new LocationTree(malate, manila));
		locationTreeRepository.save(new LocationTree(paco, manila));
		locationTreeRepository.save(new LocationTree(pandacan, manila));
		locationTreeRepository.save(new LocationTree(port_area, manila));
		locationTreeRepository.save(new LocationTree(quiapo, manila));
		locationTreeRepository.save(new LocationTree(sampaloc, manila));
		locationTreeRepository.save(new LocationTree(san_andres, manila));
		locationTreeRepository.save(new LocationTree(san_miguel, manila));
		locationTreeRepository.save(new LocationTree(san_nicolas, manila));
		locationTreeRepository.save(new LocationTree(santa_ana, manila));
		locationTreeRepository.save(new LocationTree(santa_cruz, manila));
		locationTreeRepository.save(new LocationTree(santa_mesa, manila));
		locationTreeRepository.save(new LocationTree(tondo, manila));
		locationTreeRepository.save(new LocationTree(barangka, marikina));
		locationTreeRepository.save(new LocationTree(calumpang, marikina));
		locationTreeRepository.save(new LocationTree(concepcion_i_ii, marikina));
		locationTreeRepository.save(new LocationTree(fortune, marikina));
		locationTreeRepository.save(new LocationTree(industrial_valley, marikina));
		locationTreeRepository.save(new LocationTree(malanday, marikina));
		locationTreeRepository.save(new LocationTree(marikina_heights, marikina));
		locationTreeRepository.save(new LocationTree(nangka, marikina));
		locationTreeRepository.save(new LocationTree(parang, marikina));
		locationTreeRepository.save(new LocationTree(san_roque, marikina));
		locationTreeRepository.save(new LocationTree(santa_elena, marikina));
		locationTreeRepository.save(new LocationTree(santo_niño, marikina));
		locationTreeRepository.save(new LocationTree(tañong, marikina));
		locationTreeRepository.save(new LocationTree(tumana, marikina));
		locationTreeRepository.save(new LocationTree(alabang, muntinlupa));
		locationTreeRepository.save(new LocationTree(ayala_alabang, muntinlupa));
		locationTreeRepository.save(new LocationTree(bayanan, muntinlupa));
		locationTreeRepository.save(new LocationTree(buli, muntinlupa));
		locationTreeRepository.save(new LocationTree(cupang, muntinlupa));
		locationTreeRepository.save(new LocationTree(poblacion, muntinlupa));
		locationTreeRepository.save(new LocationTree(putatan, muntinlupa));
		locationTreeRepository.save(new LocationTree(sucat, muntinlupa));
		locationTreeRepository.save(new LocationTree(tunasan, muntinlupa));
		locationTreeRepository.save(new LocationTree(bagumbayan_north, navotas));
		locationTreeRepository.save(new LocationTree(bagumbayan_south, navotas));
		locationTreeRepository.save(new LocationTree(bangkulasi, navotas));
		locationTreeRepository.save(new LocationTree(daanghari, navotas));
		locationTreeRepository.save(new LocationTree(navotas_east, navotas));
		locationTreeRepository.save(new LocationTree(navotas_west, navotas));
		locationTreeRepository.save(new LocationTree(northbay_boulevard_north, navotas));
		locationTreeRepository.save(new LocationTree(northbay_boulevard_south, navotas));
		locationTreeRepository.save(new LocationTree(northbay_boulevard_south_1, navotas));
		locationTreeRepository.save(new LocationTree(northbay_boulevard_south_2, navotas));
		locationTreeRepository.save(new LocationTree(northbay_boulevard_south_3, navotas));
		locationTreeRepository.save(new LocationTree(san_jose, navotas));
		locationTreeRepository.save(new LocationTree(san_rafael_village, navotas));
		locationTreeRepository.save(new LocationTree(san_roque, navotas));
		locationTreeRepository.save(new LocationTree(sipac_almacen, navotas));
		locationTreeRepository.save(new LocationTree(tangos_north, navotas));
		locationTreeRepository.save(new LocationTree(tangos_south, navotas));
		locationTreeRepository.save(new LocationTree(tanza, navotas));
		locationTreeRepository.save(new LocationTree(baclaran, parañaque));
		locationTreeRepository.save(new LocationTree(bf_homes, parañaque));
		locationTreeRepository.save(new LocationTree(don_bosco, parañaque));
		locationTreeRepository.save(new LocationTree(don_galo, parañaque));
		locationTreeRepository.save(new LocationTree(la_huerta, parañaque));
		locationTreeRepository.save(new LocationTree(marcelo_green, parañaque));
		locationTreeRepository.save(new LocationTree(merville, parañaque));
		locationTreeRepository.save(new LocationTree(moonwalk, parañaque));
		locationTreeRepository.save(new LocationTree(san_antonio, parañaque));
		locationTreeRepository.save(new LocationTree(san_dionisio, parañaque));
		locationTreeRepository.save(new LocationTree(san_martin_de_porres, parañaque));
		locationTreeRepository.save(new LocationTree(santo_niño, parañaque));
		locationTreeRepository.save(new LocationTree(sun_valley, parañaque));
		locationTreeRepository.save(new LocationTree(tambo, parañaque));
		locationTreeRepository.save(new LocationTree(vitalez, parañaque));
		locationTreeRepository.save(new LocationTree(malibay, pasay));
		locationTreeRepository.save(new LocationTree(manila_bay_reclamation, pasay));
		locationTreeRepository.save(new LocationTree(maricaban, pasay));
		locationTreeRepository.save(new LocationTree(picc, pasay));
		locationTreeRepository.save(new LocationTree(san_isidro, pasay));
		locationTreeRepository.save(new LocationTree(san_jose, pasay));
		locationTreeRepository.save(new LocationTree(san_rafael, pasay));
		locationTreeRepository.save(new LocationTree(san_roque, pasay));
		locationTreeRepository.save(new LocationTree(santa_clara, pasay));
		locationTreeRepository.save(new LocationTree(santo_niño, pasay));
		locationTreeRepository.save(new LocationTree(villamor_airbase, pasay));
		locationTreeRepository.save(new LocationTree(bagong_ilog, pasig));
		locationTreeRepository.save(new LocationTree(bagong_katipunan, pasig));
		locationTreeRepository.save(new LocationTree(bambang, pasig));
		locationTreeRepository.save(new LocationTree(buting, pasig));
		locationTreeRepository.save(new LocationTree(caniogan, pasig));
		locationTreeRepository.save(new LocationTree(dela_paz, pasig));
		locationTreeRepository.save(new LocationTree(kalawaan, pasig));
		locationTreeRepository.save(new LocationTree(kapasigan, pasig));
		locationTreeRepository.save(new LocationTree(kapitolyo, pasig));
		locationTreeRepository.save(new LocationTree(malinao, pasig));
		locationTreeRepository.save(new LocationTree(manggahan, pasig));
		locationTreeRepository.save(new LocationTree(mauway, pasig));
		locationTreeRepository.save(new LocationTree(maybunga, pasig));
		locationTreeRepository.save(new LocationTree(oranbo, pasig));
		locationTreeRepository.save(new LocationTree(palatiw, pasig));
		locationTreeRepository.save(new LocationTree(pinagbuhatan, pasig));
		locationTreeRepository.save(new LocationTree(pineda, pasig));
		locationTreeRepository.save(new LocationTree(rosario, pasig));
		locationTreeRepository.save(new LocationTree(sagad, pasig));
		locationTreeRepository.save(new LocationTree(san_antonio, pasig));
		locationTreeRepository.save(new LocationTree(san_joaquin, pasig));
		locationTreeRepository.save(new LocationTree(san_jose, pasig));
		locationTreeRepository.save(new LocationTree(san_miguel, pasig));
		locationTreeRepository.save(new LocationTree(san_nicolas, pasig));
		locationTreeRepository.save(new LocationTree(santa_cruz, pasig));
		locationTreeRepository.save(new LocationTree(santa_lucia, pasig));
		locationTreeRepository.save(new LocationTree(santa_rosa, pasig));
		locationTreeRepository.save(new LocationTree(santo_tomas, pasig));
		locationTreeRepository.save(new LocationTree(santolan, pasig));
		locationTreeRepository.save(new LocationTree(sumilang, pasig));
		locationTreeRepository.save(new LocationTree(ugong, pasig));
		locationTreeRepository.save(new LocationTree(aguho, pateros));
		locationTreeRepository.save(new LocationTree(magtanggol, pateros));
		locationTreeRepository.save(new LocationTree(martires, pateros));
		locationTreeRepository.save(new LocationTree(poblacion, pateros));
		locationTreeRepository.save(new LocationTree(san_pedro, pateros));
		locationTreeRepository.save(new LocationTree(san_roque, pateros));
		locationTreeRepository.save(new LocationTree(santa_ana, pateros));
		locationTreeRepository.save(new LocationTree(santo_rosario_kanluran, pateros));
		locationTreeRepository.save(new LocationTree(santo_rosario_silangan, pateros));
		locationTreeRepository.save(new LocationTree(tabacalera, pateros));
		locationTreeRepository.save(new LocationTree(alicia, quezon_city));
		locationTreeRepository.save(new LocationTree(amihan, quezon_city));
		locationTreeRepository.save(new LocationTree(apolonio_samson, quezon_city));
		locationTreeRepository.save(new LocationTree(baesa, quezon_city));
		locationTreeRepository.save(new LocationTree(bagbag, quezon_city));
		locationTreeRepository.save(new LocationTree(bagong_lipunan_ng_crame, quezon_city));
		locationTreeRepository.save(new LocationTree(bagong_pag_asa, quezon_city));
		locationTreeRepository.save(new LocationTree(bagong_silangan, quezon_city));
		locationTreeRepository.save(new LocationTree(bagumbayan, quezon_city));
		locationTreeRepository.save(new LocationTree(bagumbuhay, quezon_city));
		locationTreeRepository.save(new LocationTree(bahay_toro, quezon_city));
		locationTreeRepository.save(new LocationTree(balingasa, quezon_city));
		locationTreeRepository.save(new LocationTree(balonbato, quezon_city));
		locationTreeRepository.save(new LocationTree(batasan_hills, quezon_city));
		locationTreeRepository.save(new LocationTree(bayanihan, quezon_city));
		locationTreeRepository.save(new LocationTree(blue_ridge_a_b, quezon_city));
		locationTreeRepository.save(new LocationTree(botocan, quezon_city));
		locationTreeRepository.save(new LocationTree(bungad, quezon_city));
		locationTreeRepository.save(new LocationTree(camp_aguinaldo, quezon_city));
		locationTreeRepository.save(new LocationTree(capri, quezon_city));
		locationTreeRepository.save(new LocationTree(central, quezon_city));
		locationTreeRepository.save(new LocationTree(commonwealth, quezon_city));
		locationTreeRepository.save(new LocationTree(culiat, quezon_city));
		locationTreeRepository.save(new LocationTree(damar, quezon_city));
		locationTreeRepository.save(new LocationTree(damayang_lagi, quezon_city));
		locationTreeRepository.save(new LocationTree(del_monte, quezon_city));
		locationTreeRepository.save(new LocationTree(dioquino_zobel, quezon_city));
		locationTreeRepository.save(new LocationTree(don_manuel, quezon_city));
		locationTreeRepository.save(new LocationTree(doña_aurora, quezon_city));
		locationTreeRepository.save(new LocationTree(doña_imelda, quezon_city));
		locationTreeRepository.save(new LocationTree(doña_josefa, quezon_city));
		locationTreeRepository.save(new LocationTree(duyan_duyan, quezon_city));
		locationTreeRepository.save(new LocationTree(e_rodriguez, quezon_city));
		locationTreeRepository.save(new LocationTree(east_kamias, quezon_city));
		locationTreeRepository.save(new LocationTree(escopa_i_iv, quezon_city));
		locationTreeRepository.save(new LocationTree(fairview, quezon_city));
		locationTreeRepository.save(new LocationTree(greater_lagro, quezon_city));
		locationTreeRepository.save(new LocationTree(gulod, quezon_city));
		locationTreeRepository.save(new LocationTree(holy_spirit, quezon_city));
		locationTreeRepository.save(new LocationTree(horseshoe, quezon_city));
		locationTreeRepository.save(new LocationTree(immaculate_concepcion, quezon_city));
		locationTreeRepository.save(new LocationTree(kaligayahan, quezon_city));
		locationTreeRepository.save(new LocationTree(kalusugan, quezon_city));
		locationTreeRepository.save(new LocationTree(kamuning, quezon_city));
		locationTreeRepository.save(new LocationTree(katipunan, quezon_city));
		locationTreeRepository.save(new LocationTree(kaunlaran, quezon_city));
		locationTreeRepository.save(new LocationTree(kristong_hari, quezon_city));
		locationTreeRepository.save(new LocationTree(krus_na_ligas, quezon_city));
		locationTreeRepository.save(new LocationTree(laging_handa, quezon_city));
		locationTreeRepository.save(new LocationTree(libis, quezon_city));
		locationTreeRepository.save(new LocationTree(lourdes, quezon_city));
		locationTreeRepository.save(new LocationTree(loyola_heights, quezon_city));
		locationTreeRepository.save(new LocationTree(maharlika, quezon_city));
		locationTreeRepository.save(new LocationTree(malaya, quezon_city));
		locationTreeRepository.save(new LocationTree(mangga, quezon_city));
		locationTreeRepository.save(new LocationTree(manresa, quezon_city));
		locationTreeRepository.save(new LocationTree(mariana, quezon_city));
		locationTreeRepository.save(new LocationTree(mariblo, quezon_city));
		locationTreeRepository.save(new LocationTree(marilag, quezon_city));
		locationTreeRepository.save(new LocationTree(masagana, quezon_city));
		locationTreeRepository.save(new LocationTree(masambong, quezon_city));
		locationTreeRepository.save(new LocationTree(matandang_balara, quezon_city));
		locationTreeRepository.save(new LocationTree(milagrosa, quezon_city));
		locationTreeRepository.save(new LocationTree(ns_amoranto, quezon_city));
		locationTreeRepository.save(new LocationTree(nagkaisang_nayon, quezon_city));
		locationTreeRepository.save(new LocationTree(nayong_kanluran, quezon_city));
		locationTreeRepository.save(new LocationTree(new_era, quezon_city));
		locationTreeRepository.save(new LocationTree(north_fairview, quezon_city));
		locationTreeRepository.save(new LocationTree(novaliches, quezon_city));
		locationTreeRepository.save(new LocationTree(obrero, quezon_city));
		locationTreeRepository.save(new LocationTree(old_capitol_site, quezon_city));
		locationTreeRepository.save(new LocationTree(paang_bundok, quezon_city));
		locationTreeRepository.save(new LocationTree(pag_ibig_sa_nayon, quezon_city));
		locationTreeRepository.save(new LocationTree(paligsahan, quezon_city));
		locationTreeRepository.save(new LocationTree(paltok, quezon_city));
		locationTreeRepository.save(new LocationTree(pansol, quezon_city));
		locationTreeRepository.save(new LocationTree(paraiso, quezon_city));
		locationTreeRepository.save(new LocationTree(pasong_putik, quezon_city));
		locationTreeRepository.save(new LocationTree(pasong_tamo, quezon_city));
		locationTreeRepository.save(new LocationTree(payatas, quezon_city));
		locationTreeRepository.save(new LocationTree(phil_am, quezon_city));
		locationTreeRepository.save(new LocationTree(pinagkaisahan, quezon_city));
		locationTreeRepository.save(new LocationTree(pinyahan, quezon_city));
		locationTreeRepository.save(new LocationTree(project_6, quezon_city));
		locationTreeRepository.save(new LocationTree(quirino_2_a_c, quezon_city));
		locationTreeRepository.save(new LocationTree(quirino_3_a_b, quezon_city));
		locationTreeRepository.save(new LocationTree(roxas, quezon_city));
		locationTreeRepository.save(new LocationTree(sacred_heart, quezon_city));
		locationTreeRepository.save(new LocationTree(salvacion, quezon_city));
		locationTreeRepository.save(new LocationTree(san_agustin, quezon_city));
		locationTreeRepository.save(new LocationTree(san_antonio, quezon_city));
		locationTreeRepository.save(new LocationTree(san_bartolome, quezon_city));
		locationTreeRepository.save(new LocationTree(san_isidro_galas, quezon_city));
		locationTreeRepository.save(new LocationTree(san_isidro_labrador, quezon_city));
		locationTreeRepository.save(new LocationTree(san_jose, quezon_city));
		locationTreeRepository.save(new LocationTree(san_martin_de_porres, quezon_city));
		locationTreeRepository.save(new LocationTree(san_roque, quezon_city));
		locationTreeRepository.save(new LocationTree(san_vicente, quezon_city));
		locationTreeRepository.save(new LocationTree(sangandaan, quezon_city));
		locationTreeRepository.save(new LocationTree(santa_cruz, quezon_city));
		locationTreeRepository.save(new LocationTree(santa_lucia, quezon_city));
		locationTreeRepository.save(new LocationTree(santa_monica, quezon_city));
		locationTreeRepository.save(new LocationTree(santa_teresita, quezon_city));
		locationTreeRepository.save(new LocationTree(santo_cristo, quezon_city));
		locationTreeRepository.save(new LocationTree(santo_domingo, quezon_city));
		locationTreeRepository.save(new LocationTree(santo_niño, quezon_city));
		locationTreeRepository.save(new LocationTree(santol, quezon_city));
		locationTreeRepository.save(new LocationTree(sauyo, quezon_city));
		locationTreeRepository.save(new LocationTree(siena, quezon_city));
		locationTreeRepository.save(new LocationTree(sikatuna_village, quezon_city));
		locationTreeRepository.save(new LocationTree(silangan, quezon_city));
		locationTreeRepository.save(new LocationTree(socorro_cubao, quezon_city));
		locationTreeRepository.save(new LocationTree(st_ignatius, quezon_city));
		locationTreeRepository.save(new LocationTree(st_peter, quezon_city));
		locationTreeRepository.save(new LocationTree(tagumpay, quezon_city));
		locationTreeRepository.save(new LocationTree(talayan, quezon_city));
		locationTreeRepository.save(new LocationTree(talipapa, quezon_city));
		locationTreeRepository.save(new LocationTree(tandang_sora, quezon_city));
		locationTreeRepository.save(new LocationTree(tatalon, quezon_city));
		locationTreeRepository.save(new LocationTree(teachers_village, quezon_city));
		locationTreeRepository.save(new LocationTree(ugong_norte, quezon_city));
		locationTreeRepository.save(new LocationTree(unang_sigaw, quezon_city));
		locationTreeRepository.save(new LocationTree(up_campus, quezon_city));
		locationTreeRepository.save(new LocationTree(up_village, quezon_city));
		locationTreeRepository.save(new LocationTree(valencia, quezon_city));
		locationTreeRepository.save(new LocationTree(vasra, quezon_city));
		locationTreeRepository.save(new LocationTree(veterans_village, quezon_city));
		locationTreeRepository.save(new LocationTree(villa_maria_clara, quezon_city));
		locationTreeRepository.save(new LocationTree(west_kamias, quezon_city));
		locationTreeRepository.save(new LocationTree(west_triangle, quezon_city));
		locationTreeRepository.save(new LocationTree(white_plains, quezon_city));
		locationTreeRepository.save(new LocationTree(addition_hills, san_juan));
		locationTreeRepository.save(new LocationTree(balong_bato, san_juan));
		locationTreeRepository.save(new LocationTree(batis, san_juan));
		locationTreeRepository.save(new LocationTree(corazon_de_jesus, san_juan));
		locationTreeRepository.save(new LocationTree(ermitaño, san_juan));
		locationTreeRepository.save(new LocationTree(greenhills, san_juan));
		locationTreeRepository.save(new LocationTree(isabelita, san_juan));
		locationTreeRepository.save(new LocationTree(kabayanan, san_juan));
		locationTreeRepository.save(new LocationTree(little_baguio, san_juan));
		locationTreeRepository.save(new LocationTree(maytunas, san_juan));
		locationTreeRepository.save(new LocationTree(onse, san_juan));
		locationTreeRepository.save(new LocationTree(pasadena, san_juan));
		locationTreeRepository.save(new LocationTree(pedro_cruz, san_juan));
		locationTreeRepository.save(new LocationTree(progreso, san_juan));
		locationTreeRepository.save(new LocationTree(rivera, san_juan));
		locationTreeRepository.save(new LocationTree(salapan, san_juan));
		locationTreeRepository.save(new LocationTree(san_perfecto, san_juan));
		locationTreeRepository.save(new LocationTree(santa_lucia, san_juan));
		locationTreeRepository.save(new LocationTree(st_joseph, san_juan));
		locationTreeRepository.save(new LocationTree(tibagan, san_juan));
		locationTreeRepository.save(new LocationTree(west_crame, san_juan));
		locationTreeRepository.save(new LocationTree(bagumbayan, taguig));
		locationTreeRepository.save(new LocationTree(bambang, taguig));
		locationTreeRepository.save(new LocationTree(calzada, taguig));
		locationTreeRepository.save(new LocationTree(central_bicutan, taguig));
		locationTreeRepository.save(new LocationTree(central_signal_village, taguig));
		locationTreeRepository.save(new LocationTree(fort_bonifacio, taguig));
		locationTreeRepository.save(new LocationTree(hagonoy, taguig));
		locationTreeRepository.save(new LocationTree(ibayo_tipas, taguig));
		locationTreeRepository.save(new LocationTree(katuparan, taguig));
		locationTreeRepository.save(new LocationTree(ligid_tipas, taguig));
		locationTreeRepository.save(new LocationTree(lower_bicutan, taguig));
		locationTreeRepository.save(new LocationTree(maharlika_village, taguig));
		locationTreeRepository.save(new LocationTree(napindan, taguig));
		locationTreeRepository.save(new LocationTree(new_lower_bicutan, taguig));
		locationTreeRepository.save(new LocationTree(north_daang_hari, taguig));
		locationTreeRepository.save(new LocationTree(north_signal_village, taguig));
		locationTreeRepository.save(new LocationTree(palingon, taguig));
		locationTreeRepository.save(new LocationTree(pinagsama, taguig));
		locationTreeRepository.save(new LocationTree(san_miguel, taguig));
		locationTreeRepository.save(new LocationTree(santa_ana, taguig));
		locationTreeRepository.save(new LocationTree(south_daang_hari, taguig));
		locationTreeRepository.save(new LocationTree(south_signal_village, taguig));
		locationTreeRepository.save(new LocationTree(tanyag, taguig));
		locationTreeRepository.save(new LocationTree(tuktukan, taguig));
		locationTreeRepository.save(new LocationTree(upper_bicutan, taguig));
		locationTreeRepository.save(new LocationTree(ususan, taguig));
		locationTreeRepository.save(new LocationTree(wawa, taguig));
		locationTreeRepository.save(new LocationTree(western_bicutan, taguig));
		locationTreeRepository.save(new LocationTree(arkong_bato, valenzuela_city));
		locationTreeRepository.save(new LocationTree(bagbaguin, valenzuela_city));
		locationTreeRepository.save(new LocationTree(balangkas, valenzuela_city));
		locationTreeRepository.save(new LocationTree(bignay, valenzuela_city));
		locationTreeRepository.save(new LocationTree(bisig, valenzuela_city));
		locationTreeRepository.save(new LocationTree(canumay, valenzuela_city));
		locationTreeRepository.save(new LocationTree(coloong, valenzuela_city));
		locationTreeRepository.save(new LocationTree(dalandanan, valenzuela_city));
		locationTreeRepository.save(new LocationTree(general_t_de_leon, valenzuela_city));
		locationTreeRepository.save(new LocationTree(isla, valenzuela_city));
		locationTreeRepository.save(new LocationTree(karuhatan, valenzuela_city));
		locationTreeRepository.save(new LocationTree(lawang_bato, valenzuela_city));
		locationTreeRepository.save(new LocationTree(lingunan, valenzuela_city));
		locationTreeRepository.save(new LocationTree(mabolo, valenzuela_city));
		locationTreeRepository.save(new LocationTree(malanday, valenzuela_city));
		locationTreeRepository.save(new LocationTree(malinta, valenzuela_city));
		locationTreeRepository.save(new LocationTree(mapulang_lupa, valenzuela_city));
		locationTreeRepository.save(new LocationTree(marulas, valenzuela_city));
		locationTreeRepository.save(new LocationTree(maysan, valenzuela_city));
		locationTreeRepository.save(new LocationTree(palasan, valenzuela_city));
		locationTreeRepository.save(new LocationTree(parada, valenzuela_city));
		locationTreeRepository.save(new LocationTree(pariancillo_villa, valenzuela_city));
		locationTreeRepository.save(new LocationTree(paso_de_blas, valenzuela_city));
		locationTreeRepository.save(new LocationTree(pasolo, valenzuela_city));
		locationTreeRepository.save(new LocationTree(poblacion, valenzuela_city));
		locationTreeRepository.save(new LocationTree(polo, valenzuela_city));
		locationTreeRepository.save(new LocationTree(punturin, valenzuela_city));
		locationTreeRepository.save(new LocationTree(rincon, valenzuela_city));
		locationTreeRepository.save(new LocationTree(tagalag, valenzuela_city));
		locationTreeRepository.save(new LocationTree(ugong, valenzuela_city));
		locationTreeRepository.save(new LocationTree(veinte_reales, valenzuela_city));
		locationTreeRepository.save(new LocationTree(bagong_silang, caloocan));
		locationTreeRepository.save(new LocationTree(camarin, caloocan));

		PricingType na = pricingTypeRepository.save(new PricingType("N/A"));
		purchase = pricingTypeRepository.save(new PricingType("PURCHASE"));
		list = pricingTypeRepository.save(new PricingType("LIST"));
		pricingTypeRepository.save(new PricingType("SUPERMARKET"));

		Customer magnum_talayan = new Customer("MAGNUM TALAYAN", CustomerType.CASHIER);
		magnum_talayan.setStreet("MARIA CLARA");
		magnum_talayan.setBarangay(talayan);
		magnum_talayan.setCity(quezon_city);
		magnum_talayan.setProvince(metro_manila);
		magnum_talayan.setPrimaryPricingType(na);
		customerRepository.save(magnum_talayan);

		Customer magnum_edsa = new Customer("MAGNUM EDSA", CustomerType.CASHIER);
		magnum_edsa.setStreet("48 HOWMART RD.");
		magnum_edsa.setBarangay(apolonio_samson);
		magnum_edsa.setCity(quezon_city);
		magnum_edsa.setProvince(metro_manila);
		magnum_edsa.setPrimaryPricingType(na);
		customerRepository.save(magnum_edsa);

		Customer marina = new Customer("MARINA SALES", CustomerType.VENDOR);
		marina.setStreet("HOWMART RD.");
		marina.setBarangay(apolonio_samson);
		marina.setCity(quezon_city);
		marina.setProvince(metro_manila);
		marina.setPrimaryPricingType(purchase);
		customerRepository.save(marina);

		Customer sarisari = new Customer("SARI SARI", CustomerType.OUTLET);
		sarisari.setStreet("123 DAANAN ST.");
		sarisari.setBarangay(guadalupe_nuevo);
		sarisari.setCity(makati);
		sarisari.setProvince(metro_manila);
		sarisari.setChannel(channelRepository.findOne(2L));
		sarisari.setVisitFrequency(VisitFrequency.F2);
		sarisari.setRouteHistory(Arrays.asList(new Routing(s41, startDate())));
		sarisari.setPrimaryPricingType(list);
		customerRepository.save(sarisari);

		Customer palengke = new Customer("TALIPAPA", CustomerType.OUTLET);
		palengke.setStreet("456 TALIPAPAAN ST.");
		palengke.setBarangay(apolonio_samson);
		palengke.setCity(quezon_city);
		palengke.setProvince(metro_manila);
		palengke.setChannel(channelRepository.findOne(3L));
		palengke.setVisitFrequency(VisitFrequency.F4);
		palengke.setRouteHistory(Arrays.asList(new Routing(pms1, startDate())));
		palengke.setPrimaryPricingType(list);
		customerRepository.save(palengke);

		Customer variety = new Customer("VARIETY", CustomerType.OUTLET);
		variety.setStreet("ROAD 789");
		variety.setBarangay(guadalupe_viejo);
		variety.setCity(makati);
		variety.setProvince(metro_manila);
		variety.setChannel(channelRepository.findOne(2L));
		variety.setVisitFrequency(VisitFrequency.F2);
		variety.setRouteHistory(Arrays.asList(new Routing(s42, startDate())));
		variety.setPrimaryPricingType(list);
		customerRepository.save(variety);

		Customer wetStall = new Customer("WET MARKET STALL", CustomerType.OUTLET);
		wetStall.setStreet("STALL 1, GALAS MARKET");
		wetStall.setBarangay(san_isidro_galas);
		wetStall.setCity(quezon_city);
		wetStall.setProvince(metro_manila);
		wetStall.setChannel(channelRepository.findOne(3L));
		wetStall.setVisitFrequency(VisitFrequency.F4);
		wetStall.setRouteHistory(Arrays.asList(new Routing(pms2, startDate())));
		wetStall.setPrimaryPricingType(list);
		customerRepository.save(wetStall);

		Customer dryStall = new Customer("DRY MARKET STALL", CustomerType.OUTLET);
		dryStall.setStreet("STALL 2, GALAS MARKET");
		dryStall.setBarangay(san_isidro_galas);
		dryStall.setCity(quezon_city);
		dryStall.setProvince(metro_manila);
		dryStall.setChannel(channelRepository.findOne(4L));
		dryStall.setVisitFrequency(VisitFrequency.F4);
		dryStall.setRouteHistory(Arrays.asList(new Routing(pms3, startDate())));
		dryStall.setPrimaryPricingType(list);
		customerRepository.save(dryStall);

		Item pineSliceFlat = new Item("PINE SLCE FLT 227G", "DEL MONTE SLICED PINEAPPLE FLAT 227G X 24",
				ItemType.PURCHASED);
		pineSliceFlat.setFamily(pineSolid);
		pineSliceFlat.setVendorId("364");
		pineSliceFlat.setQtyPerUomList(pineSliceQtyPerUom());
		pineSliceFlat.setPriceList(pineSliceFlatPrices());
		pineSliceFlat.setVolumeDiscounts(pineSliceVolumeDiscounts());
		pineSliceFlat = itemRepository.save(pineSliceFlat);

		Item pineSlice15 = new Item("PINE SLCE 1.5 432G", "DEL MONTE SLICED PINEAPPLE 1 1/2 432G X 24",
				ItemType.PURCHASED);
		pineSlice15.setFamily(pineSolid);
		pineSlice15.setVendorId("1596");
		pineSlice15.setQtyPerUomList(pineSliceQtyPerUom());
		pineSlice15.setPriceList(pineSlice15Prices());
		pineSlice15.setVolumeDiscounts(pineSliceVolumeDiscounts());
		pineSlice15 = itemRepository.save(pineSlice15);

		BigDecimal pineSliceFlatPurchasePricePerPC = pineSliceFlat.getPriceList().stream()
				.filter(p -> p.getStartDate().compareTo(newDate()) <= 0 && p.getType() == purchase)
				.max(Price::compareTo).get().getPriceValue();
		BigDecimal pineSliceFlatQtyPerCS = pineSliceFlat.getQtyPerUomList().stream()
				.filter(q -> q.getUom() == UomType.CS).findFirst().get().getQty();
		BigDecimal pineSlice15QtyPerCS = pineSlice15.getQtyPerUomList().stream().filter(q -> q.getUom() == UomType.CS)
				.findFirst().get().getQty();

		BigDecimal pineSlice15PurchasePricePerPC = pineSlice15PurchasePricingPerPC().getPriceValue();
		BigDecimal pineSlice15PurchasePricePerCS = pineSlice15PurchasePricePerPC.multiply(pineSlice15QtyPerCS);

		BigDecimal pineSliceFlatPurchasePricePerCS = pineSliceFlatPurchasePricePerPC.multiply(pineSliceFlatQtyPerCS);
		BigDecimal pineSliceFlatPurchaseQty = BigDecimal.ONE;

		Purchase purchaseOrder = new Purchase(marina, newDate());
		purchaseOrder.setDetails(Arrays.asList(new PurchaseDetail(pineSliceFlat, UomType.CS, pineSliceFlatPurchaseQty,
				pineSliceFlatPurchasePricePerCS, QualityType.GOOD, null)));
		// purchaseOrder.setValue(pineSliceFlatPurchaseQty.multiply(pineSliceFlatPurchasePricePerCS));

		purchaseRepository.save(purchaseOrder);

		BigDecimal pineSliceFlatListPricePerCS = pineSliceFlatListPricePerPC().getPriceValue()
				.multiply(pineSliceFlatQtyPerCS);

		BigDecimal pineSlice15ListPricePerPC = pineSlice15.getPriceList().stream()
				.filter(p -> p.getStartDate().compareTo(newDate()) <= 0 && p.getType() == list).max(Price::compareTo)
				.get().getPriceValue();
		BigDecimal pineSlice15ListPricePerCS = pineSlice15ListPricePerPC.multiply(pineSlice15QtyPerCS);

		BigDecimal sarisariBookingQty = BigDecimal.TEN;
		Booking sarisariBooking = new Booking(sarisari, newDate());
		BookingDetail sarisariBookingDetail = new BookingDetail(pineSliceFlat, UomType.PC, sarisariBookingQty,
				QualityType.GOOD);
		sarisariBookingDetail.setPriceValue(pineSliceFlatListPricePerPC().getPriceValue());

		sarisariBooking.setDetails(Arrays.asList(sarisariBookingDetail));
		bookingRepository.save(sarisariBooking);

		Booking palengkeBooking = new Booking(palengke, newDate());

		BigDecimal palengkeDetail1Qty = BigDecimal.ONE;
		BookingDetail palengkeBookingDetail1 = new BookingDetail(pineSliceFlat, UomType.CS, palengkeDetail1Qty,
				QualityType.GOOD);
		palengkeBookingDetail1.setPriceValue(pineSliceFlatListPricePerCS);
		BigDecimal palengkeDetail2Qty = new BigDecimal(2);
		BookingDetail palengkeBookingDetail2 = new BookingDetail(pineSlice15, UomType.CS, palengkeDetail2Qty,
				QualityType.GOOD);
		palengkeBookingDetail2.setPriceValue(pineSlice15ListPricePerCS);
		BigDecimal palengkeDetail3Qty = new BigDecimal(12);
		BookingDetail palengkeBookingDetail3 = new BookingDetail(pineSlice15, UomType.PC, palengkeDetail3Qty,
				QualityType.GOOD);
		palengkeBookingDetail3.setPriceValue(pineSlice15ListPricePerPC);
		palengkeBooking
				.setDetails(Arrays.asList(palengkeBookingDetail1, palengkeBookingDetail2, palengkeBookingDetail3));
		bookingRepository.save(palengkeBooking);

		BigDecimal varietyQty = new BigDecimal(20);
		Booking varietyBooking = new Booking(variety, oldDate());
		BookingDetail varietyBookingDetail = new BookingDetail(pineSliceFlat, UomType.PC, varietyQty, QualityType.GOOD);
		varietyBookingDetail.setPriceValue(pineSliceFlatListPricePerPC().getPriceValue());
		varietyBooking.setDetails(Arrays.asList(varietyBookingDetail));
		varietyBooking.setDiscounts(Arrays.asList(new BookingDiscount(1, new BigDecimal("10.00"))));
		bookingRepository.save(varietyBooking);

		BigDecimal wetStallBookingQty = new BigDecimal(5);
		Booking wetStallBooking = new Booking(wetStall, oldDate());
		BookingDetail wetStallBookingDetail = new BookingDetail(pineSliceFlat, UomType.PC, wetStallBookingQty,
				QualityType.GOOD);
		BookingDiscount wetBooking5Discount = new BookingDiscount(1, new BigDecimal("5.00"));
		BookingDiscount wetBooking1Discount = new BookingDiscount(2, new BigDecimal("1.00"));
		wetStallBookingDetail.setPriceValue(pineSliceFlatListPricePerPC().getPriceValue());
		wetStallBooking.setDetails(Arrays.asList(wetStallBookingDetail));
		wetStallBooking.setDiscounts(Arrays.asList(wetBooking5Discount, wetBooking1Discount));
		bookingRepository.save(wetStallBooking);

		BigDecimal dryStallQty = new BigDecimal(2);
		Booking dryStallBooking = new Booking(dryStall, oldDate());
		BookingDetail dryStallBookingDetail = new BookingDetail(pineSliceFlat, UomType.CS, dryStallQty,
				QualityType.GOOD);
		dryStallBookingDetail.setPriceValue(pineSliceFlatListPricePerCS);
		dryStallBooking.setDetails(Arrays.asList(dryStallBookingDetail));
		bookingRepository.save(dryStallBooking);

		Picking wsnPick = new Picking(wsn519, sysgen, sysgen, newDate());
		wsnPick.setBookings(Arrays.asList(palengkeBooking));
		pickRepository.save(wsnPick);

		Picking rdmPick = new Picking(rdm801, sysgen, sysgen, newDate());
		rdmPick.setBookings(Arrays.asList(varietyBooking));
		pickRepository.save(rdmPick);

		Picking kdlPick = new Picking(kdl170, sysgen, sysgen, newDate());
		kdlPick.setBookings(Arrays.asList(wetStallBooking, dryStallBooking));
		pickRepository.save(kdlPick);

		InvoiceBooklet ib1 = new InvoiceBooklet();
		ib1.setStartId(1L);
		ib1.setEndId(50L);
		ib1.setIssuedTo(dsp1);
		bookletRepository.save(ib1);

		InvoiceBooklet ib2 = new InvoiceBooklet();
		ib2.setStartId(51L);
		ib2.setEndId(100L);
		ib2.setIssuedTo(dsp2);
		bookletRepository.save(ib2);

		List<InvoiceDetail> varietyDetails = new ArrayList<>();
		Invoice varietyInvoice = new Invoice();
		varietyInvoice.setIdNo(1L);
		varietyInvoice.setCustomer(varietyBooking.getCustomer());
		varietyInvoice.setBooking(varietyBooking);
		varietyInvoice.setOrderDate(newDate());
		varietyInvoice.setDiscounts(getInvoiceDiscounts(varietyBooking));
		varietyBooking.getDetails().forEach(vb -> {
			InvoiceDetail detail = new InvoiceDetail(vb.getItem(), vb.getUom(), vb.getQty(), vb.getQuality(),
					vb.getPriceValue());
			varietyDetails.add(detail);
		});
		varietyInvoice.setDetails(varietyDetails);
		invoiceRepository.save(varietyInvoice);

		List<InvoiceDetail> wetStallDetails = new ArrayList<>();
		Invoice wetStallInvoice = new Invoice();
		wetStallInvoice.setIdNo(2L);
		wetStallInvoice.setCustomer(wetStallBooking.getCustomer());
		wetStallInvoice.setBooking(wetStallBooking);
		wetStallInvoice.setOrderDate(newDate());
		wetStallInvoice.setDiscounts(getInvoiceDiscounts(wetStallBooking));
		wetStallBooking.getDetails().forEach(wmb -> {
			wetStallDetails.add(new InvoiceDetail(wmb.getItem(), wmb.getUom(), wmb.getQty(), wmb.getQuality(),
					wmb.getPriceValue()));
		});
		wetStallInvoice.setDetails(wetStallDetails);
		invoiceRepository.save(wetStallInvoice);

		List<InvoiceDetail> dryStallDetails = new ArrayList<>();
		Invoice dryStallInvoice = new Invoice();
		dryStallInvoice.setIdNo(3L);
		dryStallInvoice.setCustomer(dryStallBooking.getCustomer());
		dryStallInvoice.setBooking(dryStallBooking);
		dryStallInvoice.setOrderDate(newDate());
		dryStallInvoice.setDiscounts(getInvoiceDiscounts(dryStallBooking));
		dryStallBooking.getDetails().forEach(d -> {
			InvoiceDetail detail = new InvoiceDetail(d.getItem(), d.getUom(), d.getQty(), d.getQuality(),
					d.getPriceValue());
			dryStallDetails.add(detail);
		});
		dryStallInvoice.setDetails(dryStallDetails);
		invoiceRepository.save(dryStallInvoice);

		List<InvoiceDetail> dryStall1Details = new ArrayList<>();
		dryStallBooking.getDetails().forEach(d -> {
			InvoiceDetail detail = new InvoiceDetail(d.getItem(), d.getUom(), d.getQty(), d.getQuality(),
					d.getPriceValue());
			dryStall1Details.add(detail);
		});
		Invoice dryStallInvoice1 = new Invoice();
		dryStallInvoice1.setIdNo(4L);
		dryStallInvoice1.setCustomer(dryStallBooking.getCustomer());
		dryStallInvoice1.setBooking(dryStallBooking);
		dryStallInvoice1.setOrderDate(newDate().minusDays(1L));
		dryStallInvoice1.setDiscounts(getInvoiceDiscounts(dryStallBooking));
		dryStallInvoice1.setDetails(dryStall1Details);
		invoiceRepository.save(dryStallInvoice1);

		List<InvoiceDetail> dryStall8Details = new ArrayList<>();
		dryStallBooking.getDetails().forEach(d -> {
			InvoiceDetail detail = new InvoiceDetail(d.getItem(), d.getUom(), d.getQty(), d.getQuality(),
					d.getPriceValue());
			dryStall8Details.add(detail);
		});
		Invoice dryStallInvoice8 = new Invoice();
		dryStallInvoice8.setIdNo(5L);
		dryStallInvoice8.setCustomer(dryStallBooking.getCustomer());
		dryStallInvoice8.setBooking(dryStallBooking);
		dryStallInvoice8.setOrderDate(newDate().minusDays(8L));
		dryStallInvoice8.setDiscounts(getInvoiceDiscounts(dryStallBooking));
		dryStallInvoice8.setDetails(dryStall8Details);
		invoiceRepository.save(dryStallInvoice8);

		List<InvoiceDetail> dryStall16Details = new ArrayList<>();
		dryStallBooking.getDetails().forEach(d -> {
			InvoiceDetail detail = new InvoiceDetail(d.getItem(), d.getUom(), d.getQty(), d.getQuality(),
					d.getPriceValue());
			dryStall16Details.add(detail);
		});
		Invoice dryStallInvoice16 = new Invoice();
		dryStallInvoice16.setIdNo(6L);
		dryStallInvoice16.setCustomer(dryStallBooking.getCustomer());
		dryStallInvoice16.setBooking(dryStallBooking);
		dryStallInvoice16.setOrderDate(newDate().minusDays(16L));
		dryStallInvoice16.setDiscounts(getInvoiceDiscounts(dryStallBooking));
		dryStallInvoice16.setDetails(dryStall16Details);
		invoiceRepository.save(dryStallInvoice16);

		List<InvoiceDetail> dryStall31Details = new ArrayList<>();
		dryStallBooking.getDetails().forEach(d -> {
			InvoiceDetail detail = new InvoiceDetail(d.getItem(), d.getUom(), d.getQty(), d.getQuality(),
					d.getPriceValue());
			dryStall31Details.add(detail);
		});
		Invoice dryStallInvoice31 = new Invoice();
		dryStallInvoice31.setIdNo(7L);
		dryStallInvoice31.setCustomer(dryStallBooking.getCustomer());
		dryStallInvoice31.setBooking(dryStallBooking);
		dryStallInvoice31.setOrderDate(newDate().minusDays(31L));
		dryStallInvoice31.setDiscounts(getInvoiceDiscounts(dryStallBooking));
		dryStallInvoice31.setDetails(dryStall31Details);
		invoiceRepository.save(dryStallInvoice31);

		List<InvoiceDetail> wetStall31Details = new ArrayList<>();
		wetStallBooking.getDetails().forEach(d -> {
			InvoiceDetail detail = new InvoiceDetail(d.getItem(), d.getUom(), d.getQty(), d.getQuality(),
					d.getPriceValue());
			wetStall31Details.add(detail);
		});
		Invoice wetStallInvoice31 = new Invoice();
		wetStallInvoice31.setIdNo(8L);
		wetStallInvoice31.setCustomer(wetStallBooking.getCustomer());
		wetStallInvoice31.setBooking(wetStallBooking);
		wetStallInvoice31.setOrderDate(newDate().minusDays(31L));
		wetStallInvoice31.setDiscounts(getInvoiceDiscounts(wetStallBooking));
		wetStallInvoice31.setDetails(wetStall31Details);
		invoiceRepository.save(wetStallInvoice31);

		BigDecimal rdmRemitValue = new BigDecimal(469.60);
		Remittance rdmRemit = new Remittance(newDate(), magnum_edsa, "", rdmRemitValue, dsp1);
		rdmRemit = remittanceRepository.save(rdmRemit);
		rdmRemit.setDetails(Arrays.asList(new RemittanceDetail(rdmRemit, varietyInvoice, rdmRemitValue)));
		rdmRemit = remittanceRepository.save(rdmRemit);
		varietyInvoice
				.setFullyPaid(varietyInvoice.getUnpaidValue().subtract(rdmRemitValue).compareTo(BigDecimal.ONE) <= 0);
		varietyInvoice = invoiceRepository.save(varietyInvoice);

		BigDecimal kdlRemitValue = new BigDecimal(117.40);
		Remittance kdlRemit = new Remittance(newDate(), magnum_edsa, "", kdlRemitValue, dsp2);
		kdlRemit = remittanceRepository.save(kdlRemit);
		kdlRemit.setDetails(Arrays.asList(new RemittanceDetail(kdlRemit, wetStallInvoice, kdlRemitValue)));
		kdlRemit = remittanceRepository.save(kdlRemit);
		wetStallInvoice
				.setFullyPaid(wetStallInvoice.getUnpaidValue().subtract(kdlRemitValue).compareTo(BigDecimal.ONE) <= 0);
		wetStallInvoice = invoiceRepository.save(wetStallInvoice);

		BigDecimal marinaReceiptQty = BigDecimal.TEN;
		Receipt marinaReceipt = new Receipt(marina, ReceivingReferenceType.PO, 1L, sysgen, newDate());
		ReceiptDetail marinaPineSliceFlatReceiptDetail = new ReceiptDetail(pineSliceFlat, UomType.CS, marinaReceiptQty,
				QualityType.GOOD);
		marinaPineSliceFlatReceiptDetail.setPriceValue(pineSliceFlatPurchasePricePerCS);
		ReceiptDetail marinaPineSlice15ReceiptDetail = new ReceiptDetail(pineSlice15, UomType.CS, marinaReceiptQty,
				QualityType.GOOD);
		marinaPineSlice15ReceiptDetail.setPriceValue(pineSlice15PurchasePricePerCS);
		marinaReceipt.setDetails(Arrays.asList(marinaPineSliceFlatReceiptDetail, marinaPineSlice15ReceiptDetail));
		receiptRepository.save(marinaReceipt);

		stockTakeReconciliationRepository.save(new StockTakeReconciliation(sysgen, newDate()));

		StockTake stockTake1 = stockTakeRepository.save(new StockTake(edsa, sysgen, sysgen, newDate()));
		StockTakeDetail std1 = new StockTakeDetail(pineSliceFlat, UomType.CS, BigDecimal.ONE, QualityType.GOOD);
		StockTakeDetail std2 = new StockTakeDetail(pineSliceFlat, UomType.PC, BigDecimal.TEN, QualityType.BAD);
		StockTakeDetail std3 = new StockTakeDetail(pineSlice15, UomType.PC, new BigDecimal(5), QualityType.BAD);
		stockTake1.setDetails(Arrays.asList(std1, std2, std3));
		stockTakeRepository.save(stockTake1);

		StockTake stockTake2 = stockTakeRepository.save(new StockTake(edsa, sysgen, sysgen, newDate()));
		StockTakeDetail std4 = new StockTakeDetail(pineSlice15, UomType.PC, new BigDecimal(5), QualityType.GOOD);
		stockTake2.setDetails(Arrays.asList(std4));
		stockTakeRepository.save(stockTake2);
	}

	private LocalDate startDate() {
		return LocalDate.parse("2014-07-28");
	}
}
