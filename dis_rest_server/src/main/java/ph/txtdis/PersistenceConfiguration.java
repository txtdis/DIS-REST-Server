package ph.txtdis;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;

@Configuration("persistenceConfiguration")
public class PersistenceConfiguration {

	/*
	 * @Autowired private ChannelRepository channelRepo;
	 * 
	 * @Autowired private LocationRepository locRepo;
	 * 
	 * @Autowired private LocationTreeRepository locTreeRepo;
	 * 
	 * @Autowired private PricingTypeRepository pricingTypeRepo;
	 * 
	 * @Autowired private TruckRepository truckRepo;
	 * 
	 * @Autowired private UserRepository userRepo;
	 * 
	 * @Autowired private WarehouseRepository warehouseRepo;
	 */

	@PostConstruct
	private void start() {
		/*
		 * if (userRepo.count() >= 1) return;
		 * 
		 * List<Authority> manager = asList(new Authority(MANAGER));
		 * List<Authority> admin = asList(new Authority(ADMIN)); List<Authority>
		 * storeKeeper = asList(new Authority(STORE_KEEPER)); List<Authority>
		 * finance = asList(new Authority(FINANCE)); List<Authority> auditor =
		 * asList(new Authority(AUDITOR)); List<Authority> seller = asList(new
		 * Authority(SELLER)); List<Authority> driver = asList(new
		 * Authority(DRIVER)); List<Authority> helper = asList(new
		 * Authority(HELPER)); asList(new Authority(UserType.CASHIER));
		 * asList(new Authority(GUEST));
		 *
		 * User sysgen = new User("SYSGEN",
		 * encode("I'mSysGen4txtDIS@PostgreSQL"), manager);
		 * userRepo.save(sysgen);
		 *
		 * User jackie = new User("JACKIE", encode("robbie"), manager);
		 * jackie.setEmail("manila12@gmail.com"); userRepo.save(jackie);
		 *
		 * User ronald = new User("RONALD", encode("alphacowboy"), manager);
		 * ronald.setEmail("ronaldallanso@yahoo.com"); userRepo.save(ronald);
		 *
		 * User butch = new User("BUTCH", encode("attila"), manager);
		 * butch.setEmail("butchlim888@yahoo.com"); userRepo.save(butch);
		 *
		 * userRepo.save(new User("NOEL", encode("nash24"), seller));
		 * userRepo.save(new User("KIMBERLY", encode("070188"),
		 * admin).disable()); userRepo.save(new User("ROSBEL",
		 * encode("password"), admin)); userRepo.save(new User("JOSEPH",
		 * encode("password"), helper)); userRepo.save(new User("RYAN",
		 * encode("password"), helper)); userRepo.save(new User("MARLON",
		 * encode("password"), helper)); userRepo.save(new User("WARREN",
		 * encode("password"), helper)); userRepo.save(new User("JONATHAN",
		 * encode("password"), helper)); userRepo.save(new User("JUN",
		 * encode("sniper"), storeKeeper)); userRepo.save(new User("ROLAND",
		 * encode("TIPON"), seller).disable()); userRepo.save(new
		 * User("MARIVIC", encode("marvic"), finance)); userRepo.save(new
		 * User("BADETTE", encode("013094"), admin).disable());
		 * userRepo.save(new User("SHERYL", encode("10-8-91"), storeKeeper));
		 * userRepo.save(new User("ROELYN", encode("password"), storeKeeper));
		 * userRepo.save(new User("EDDIE", encode("password"), storeKeeper));
		 * userRepo.save(new User("BOOBY", encode("password"), storeKeeper));
		 * userRepo.save(new User("JAYSON", encode("1430"), driver));
		 * userRepo.save(new User("NOLI", encode("password"), driver));
		 * userRepo.save(new User("LORNA", encode("rod"), auditor));
		 * userRepo.save(new User("APRIL", encode("april"), auditor));
		 * userRepo.save(new User("JOSEPHINE", encode("jho"), seller));
		 * userRepo.save(new User("HOMER", encode("password"), seller));
		 * userRepo.save(new User("GLAZEL", encode("glazel"), admin).disable());
		 * userRepo.save(new User("AIZA", encode("aiza"), admin).disable());
		 * userRepo.save(new User("IRENE", encode("irene"), admin).disable());
		 * userRepo.save(new User("RJAY", encode("rjay"), storeKeeper));
		 * userRepo.save(new User("MICHELLE", encode("michelle"),
		 * admin).disable()); userRepo.save(new User("ALFRED", encode("alfred"),
		 * seller).disable()); userRepo.save(new User("RUBEN", encode("ruben"),
		 * storeKeeper).disable()); userRepo.save(new User("ROYNA",
		 * encode("royna"), seller).disable()); userRepo.save(new User("ARIEL",
		 * encode("ariel"), driver).disable()); userRepo.save(new User("MER",
		 * encode("mer"), admin).disable());
		 *
		 * channelRepo.save(new Channel("AMBULANT")); channelRepo.save(new
		 * Channel("CPP")); channelRepo.save(new Channel("FOOD SERVICE"));
		 * channelRepo.save(new Channel("GROCERY")); channelRepo.save(new
		 * Channel("INTERNAL")); channelRepo.save(new Channel("MARKET STALL"));
		 * channelRepo.save(new Channel("OTHERS")); channelRepo.save(new
		 * Channel("SARI-SARI")); channelRepo.save(new Channel("SUPERMARKET"));
		 * channelRepo.save(new Channel("MARKET DEALER")); channelRepo.save(new
		 * Channel("RAS"));
		 *
		 * Location bulacan = locRepo.save(new Location("BULACAN", PROVINCE));
		 * Location san_jose_del_monte = locRepo.save(new Location(
		 * "SAN JOSE DEL MONTE", CITY)); Location assumption = locRepo.save(new
		 * Location("ASSUMPTION", BARANGAY)); Location bagong_buhay =
		 * locRepo.save(new Location("BAGONG BUHAY", BARANGAY)); Location citrus
		 * = locRepo.save(new Location("CITRUS", BARANGAY)); Location
		 * ciudad_real = locRepo.save(new Location("CIUDAD REAL", BARANGAY));
		 * Location dulong_bayan = locRepo.save(new Location("DULONG BAYAN",
		 * BARANGAY)); Location fatima = locRepo.save(new Location("FATIMA",
		 * BARANGAY)); Location francisco_homes = locRepo.save(new Location(
		 * "FRANCISCO HOMES", BARANGAY)); Location gaya_gaya = locRepo.save(new
		 * Location("GAYA-GAYA", BARANGAY)); Location graceville =
		 * locRepo.save(new Location("GRACEVILLE", BARANGAY)); Location gumaoc =
		 * locRepo.save(new Location("GUMAOC", BARANGAY)); Location kaybanban =
		 * locRepo.save(new Location("KAYBANBAN", BARANGAY)); Location kaypian =
		 * locRepo.save(new Location("KAYPIAN", BARANGAY)); Location lawang_pare
		 * = locRepo.save(new Location("LAWANG PARE", BARANGAY)); Location
		 * maharlika = locRepo.save(new Location("MAHARLIKA", BARANGAY));
		 * Location minuyan = locRepo.save(new Location("MINUYAN", BARANGAY));
		 * Location muzon = locRepo.save(new Location("MUZON", BARANGAY));
		 * Location paradise = locRepo.save(new Location("PARADISE", BARANGAY));
		 * Location poblacion = locRepo.save(new Location("POBLACION",
		 * BARANGAY)); Location san_isidro = locRepo.save(new Location(
		 * "SAN ISIDRO", BARANGAY)); Location san_manuel = locRepo.save(new
		 * Location("SAN MANUEL", BARANGAY)); Location san_martin =
		 * locRepo.save(new Location("SAN MARTIN", BARANGAY)); Location
		 * san_pedro = locRepo.save(new Location("SAN PEDRO", BARANGAY));
		 * Location san_rafael = locRepo.save(new Location("SAN RAFAEL",
		 * BARANGAY)); Location san_roque = locRepo.save(new Location(
		 * "SAN ROQUE", BARANGAY)); Location sapang_palay = locRepo.save(new
		 * Location("SAPANG PALAY", BARANGAY)); Location saint_martin_de_porres
		 * = locRepo.save(new Location("SAINT MARTIN DE PORRES", BARANGAY));
		 * Location santa_cruz = locRepo.save(new Location("SANTA CRUZ",
		 * BARANGAY)); Location santo_cristo = locRepo.save(new Location(
		 * "SANTO CRISTO", BARANGAY)); Location santo_niño = locRepo.save(new
		 * Location("SANTO NIÑO", BARANGAY)); Location tungkong_mangga =
		 * locRepo.save(new Location("TUNGKONG MANGGA", BARANGAY)); Location
		 * santa_maria = locRepo.save(new Location("SANTA MARIA", CITY));
		 * Location bagbaguin = locRepo.save(new Location("BAGBAGUIN",
		 * BARANGAY)); Location balasing = locRepo.save(new Location("BALASING",
		 * BARANGAY)); Location buenavista = locRepo.save(new
		 * Location("BUENAVISTA", BARANGAY)); Location bulac = locRepo.save(new
		 * Location("BULAC", BARANGAY)); Location camangyanan = locRepo.save(new
		 * Location("CAMANGYANAN", BARANGAY)); Location catmon =
		 * locRepo.save(new Location("CATMON", BARANGAY)); Location caypombo =
		 * locRepo.save(new Location("CAYPOMBO", BARANGAY)); Location caysio =
		 * locRepo.save(new Location("CAYSIO", BARANGAY)); Location guyong =
		 * locRepo.save(new Location("GUYONG", BARANGAY)); Location lalakhan =
		 * locRepo.save(new Location("LALAKHAN", BARANGAY)); Location
		 * mag_asawang_sapa = locRepo.save(new Location("MAG-ASAWANG SAPA",
		 * BARANGAY)); Location mahabang_parang = locRepo.save(new Location(
		 * "MAHABANG PARANG", BARANGAY)); Location manggahan = locRepo.save(new
		 * Location("MANGGAHAN", BARANGAY)); Location parada = locRepo.save(new
		 * Location("PARADA", BARANGAY)); Location pulong_buhangin =
		 * locRepo.save(new Location("PULONG BUHANGIN", BARANGAY)); Location
		 * san_gabriel = locRepo.save(new Location("SAN GABRIEL", BARANGAY));
		 * Location san_jose_patag = locRepo.save(new Location("SAN JOSE PATAG",
		 * BARANGAY)); Location san_vicente = locRepo.save(new Location(
		 * "SAN VICENTE", BARANGAY)); Location santa_clara = locRepo.save(new
		 * Location("SANTA CLARA", BARANGAY)); Location santo_tomas =
		 * locRepo.save(new Location("SANTO TOMAS", BARANGAY)); Location
		 * silangan = locRepo.save(new Location("SILANGAN", BARANGAY)); Location
		 * tumana = locRepo.save(new Location("TUMANA", BARANGAY));
		 *
		 * Location a_mabini = locRepo.save(new Location("A. MABINI",
		 * BARANGAY)); Location baesa = locRepo.save(new Location("BAESA",
		 * BARANGAY)); Location bagong_barrio = locRepo.save(new Location(
		 * "BAGONG BARRIO", BARANGAY)); Location barrio_san_jose =
		 * locRepo.save(new Location("BARRIO SAN JOSE", BARANGAY)); Location
		 * biglang_awa = locRepo.save(new Location("BIGLANG-AWA", BARANGAY));
		 * Location dagat_dagatan = locRepo.save(new Location("DAGAT-DAGATAN",
		 * BARANGAY)); Location grace_park_east = locRepo.save(new Location(
		 * "GRACE PARK EAST", BARANGAY)); Location grace_park_west =
		 * locRepo.save(new Location("GRACE PARK WEST", BARANGAY)); Location
		 * heroes_del_96 = locRepo.save(new Location("HEROES DEL 96",
		 * BARANGAY)); Location kaunlaran_village = locRepo.save(new Location(
		 * "KAUNLARAN VILLAGE", BARANGAY)); Location libis_reparo =
		 * locRepo.save(new Location("LIBIS REPARO", BARANGAY)); Location
		 * maypajo = locRepo.save(new Location("MAYPAJO", BARANGAY)); Location
		 * monumento = locRepo.save(new Location("MONUMENTO", BARANGAY));
		 * Location morning_breeze = locRepo.save(new Location("MORNING BREEZE",
		 * BARANGAY)); Location sangandaan = locRepo.save(new
		 * Location("SANGANDAAN", BARANGAY)); Location santa_quiteria =
		 * locRepo.save(new Location("SANTA QUITERIA", BARANGAY)); Location
		 * talipapa = locRepo.save(new Location("TALIPAPA", BARANGAY)); Location
		 * university_hills = locRepo.save(new Location("UNIVERSITY HILLS",
		 * BARANGAY)); Location almanza_dos = locRepo.save(new Location(
		 * "ALMANZA DOS", BARANGAY)); Location almanza_uno = locRepo.save(new
		 * Location("ALMANZA UNO", BARANGAY)); Location caa_bf_international =
		 * locRepo.save(new Location("CAA-BF INTERNATIONAL", BARANGAY));
		 * Location daniel_fajardo = locRepo.save(new Location("DANIEL FAJARDO",
		 * BARANGAY)); Location elias_aldana = locRepo.save(new Location(
		 * "ELIAS ALDANA", BARANGAY)); Location ilaya = locRepo.save(new
		 * Location("ILAYA", BARANGAY)); Location manuyo_dos = locRepo.save(new
		 * Location("MANUYO DOS", BARANGAY)); Location manuyo_uno =
		 * locRepo.save(new Location("MANUYO UNO", BARANGAY)); Location
		 * pamplona_dos = locRepo.save(new Location("PAMPLONA DOS", BARANGAY));
		 * Location pamplona_tres = locRepo.save(new Location("PAMPLONA TRES",
		 * BARANGAY)); Location pamplona_uno = locRepo.save(new Location(
		 * "PAMPLONA UNO", BARANGAY)); Location pilar_village = locRepo.save(new
		 * Location("PILAR VILLAGE", BARANGAY)); Location pulang_lupa_dos =
		 * locRepo.save(new Location("PULANG LUPA DOS", BARANGAY)); Location
		 * pulang_lupa_uno = locRepo.save(new Location("PULANG LUPA UNO",
		 * BARANGAY)); Location talon_cinco = locRepo.save(new Location(
		 * "TALON CINCO", BARANGAY)); Location talon_cuatro = locRepo.save(new
		 * Location("TALON CUATRO", BARANGAY)); Location talon_dos =
		 * locRepo.save(new Location("TALON DOS", BARANGAY)); Location
		 * talon_tres = locRepo.save(new Location("TALON TRES", BARANGAY));
		 * Location talon_uno = locRepo.save(new Location("TALON UNO",
		 * BARANGAY)); Location zapote = locRepo.save(new Location("ZAPOTE",
		 * BARANGAY)); Location bangkal = locRepo.save(new Location("BANGKAL",
		 * BARANGAY)); Location bel_air = locRepo.save(new Location("BEL-AIR",
		 * BARANGAY)); Location carmona = locRepo.save(new Location("CARMONA",
		 * BARANGAY)); Location cembo = locRepo.save(new Location("CEMBO",
		 * BARANGAY)); Location comembo = locRepo.save(new Location("COMEMBO",
		 * BARANGAY)); Location dasmariñas = locRepo.save(new
		 * Location("DASMARIÑAS", BARANGAY)); Location east_rembo =
		 * locRepo.save(new Location("EAST REMBO", BARANGAY)); Location
		 * forbes_park = locRepo.save(new Location("FORBES PARK", BARANGAY));
		 * Location guadalupe_nuevo = locRepo.save(new Location(
		 * "GUADALUPE NUEVO", BARANGAY)); Location guadalupe_viejo =
		 * locRepo.save(new Location("GUADALUPE VIEJO", BARANGAY)); Location
		 * kasilawan = locRepo.save(new Location("KASILAWAN", BARANGAY));
		 * Location la_paz = locRepo.save(new Location("LA PAZ", BARANGAY));
		 * Location magallanes = locRepo.save(new Location("MAGALLANES",
		 * BARANGAY)); Location olympia = locRepo.save(new Location("OLYMPIA",
		 * BARANGAY)); Location palanan = locRepo.save(new Location("PALANAN",
		 * BARANGAY)); Location pembo = locRepo.save(new Location("PEMBO",
		 * BARANGAY)); Location pinagkaisahan = locRepo.save(new
		 * Location("PINAGKAISAHAN", BARANGAY)); Location pio_del_pilar =
		 * locRepo.save(new Location("PIO DEL PILAR", BARANGAY)); Location
		 * pitogo = locRepo.save(new Location("PITOGO", BARANGAY)); Location
		 * post_proper_northside = locRepo.save(new Location(
		 * "POST PROPER NORTHSIDE", BARANGAY)); Location post_proper_southside =
		 * locRepo.save(new Location("POST PROPER SOUTHSIDE", BARANGAY));
		 * Location rembo = locRepo.save(new Location("REMBO", BARANGAY));
		 * Location rizal = locRepo.save(new Location("RIZAL", BARANGAY));
		 * Location san_antonio = locRepo.save(new Location("SAN ANTONIO",
		 * BARANGAY)); Location san_lorenzo = locRepo.save(new Location(
		 * "SAN LORENZO", BARANGAY)); Location singkamas = locRepo.save(new
		 * Location("SINGKAMAS", BARANGAY)); Location south_cembo =
		 * locRepo.save(new Location("SOUTH CEMBO", BARANGAY)); Location tejeros
		 * = locRepo.save(new Location("TEJEROS", BARANGAY)); Location urdaneta
		 * = locRepo.save(new Location("URDANETA", BARANGAY)); Location
		 * valenzuela = locRepo.save(new Location("VALENZUELA", BARANGAY));
		 * Location west_rembo = locRepo.save(new Location("WEST REMBO",
		 * BARANGAY)); Location acacia = locRepo.save(new Location("ACACIA",
		 * BARANGAY)); Location baritan = locRepo.save(new Location("BARITAN",
		 * BARANGAY)); Location bayan_bayanan = locRepo.save(new
		 * Location("BAYAN-BAYANAN", BARANGAY)); Location concepcion =
		 * locRepo.save(new Location("CONCEPCION", BARANGAY)); Location dampalit
		 * = locRepo.save(new Location("DAMPALIT", BARANGAY)); Location flores =
		 * locRepo.save(new Location("FLORES", BARANGAY)); Location hulong_duhat
		 * = locRepo.save(new Location("HULONG DUHAT", BARANGAY)); Location
		 * ibaba = locRepo.save(new Location("IBABA", BARANGAY)); Location
		 * longos = locRepo.save(new Location("LONGOS", BARANGAY)); Location
		 * maysilo = locRepo.save(new Location("MAYSILO", BARANGAY)); Location
		 * niugan = locRepo.save(new Location("NIUGAN", BARANGAY)); Location
		 * panghulo = locRepo.save(new Location("PANGHULO", BARANGAY)); Location
		 * potrero = locRepo.save(new Location("POTRERO", BARANGAY)); Location
		 * san_agustin = locRepo.save(new Location("SAN AGUSTIN", BARANGAY));
		 * Location santolan = locRepo.save(new Location("SANTOLAN", BARANGAY));
		 * Location tañong = locRepo.save(new Location("TAÑONG", BARANGAY));
		 * Location tinajeros = locRepo.save(new Location("TINAJEROS",
		 * BARANGAY)); Location tonsuya = locRepo.save(new Location("TONSUYA",
		 * BARANGAY)); Location tugatog = locRepo.save(new Location("TUGATOG",
		 * BARANGAY)); Location addition_hills = locRepo.save(new Location(
		 * "ADDITION HILLS", BARANGAY)); Location bagong_silang =
		 * locRepo.save(new Location("BAGONG SILANG", BARANGAY)); Location
		 * barangka_drive = locRepo.save(new Location("BARANGKA DRIVE",
		 * BARANGAY)); Location barangka_ibaba = locRepo.save(new Location(
		 * "BARANGKA IBABA", BARANGAY)); Location barangka_ilaya =
		 * locRepo.save(new Location("BARANGKA ILAYA", BARANGAY)); Location
		 * barangka_itaas = locRepo.save(new Location("BARANGKA ITAAS",
		 * BARANGAY)); Location buayang_bato = locRepo.save(new Location(
		 * "BUAYANG BATO", BARANGAY)); Location burol = locRepo.save(new
		 * Location("BUROL", BARANGAY)); Location daang_bakal = locRepo.save(new
		 * Location("DAANG BAKAL", BARANGAY)); Location hagdan_bato_itaas =
		 * locRepo.save(new Location("HAGDAN BATO ITAAS", BARANGAY)); Location
		 * hagdan_bato_libis = locRepo.save(new Location("HAGDAN BATO LIBIS",
		 * BARANGAY)); Location harapin_ang_bukas = locRepo.save(new Location(
		 * "HARAPIN ANG BUKAS", BARANGAY)); Location highway_hills =
		 * locRepo.save(new Location("HIGHWAY HILLS", BARANGAY)); Location hulo
		 * = locRepo.save(new Location("HULO", BARANGAY)); Location
		 * mabini_j_rizal = locRepo.save(new Location("MABINI-J. RIZAL",
		 * BARANGAY)); Location malamig = locRepo.save(new Location("MALAMIG",
		 * BARANGAY)); Location namayan = locRepo.save(new Location("NAMAYAN",
		 * BARANGAY)); Location new_zañiga = locRepo.save(new Location(
		 * "NEW ZAÑIGA", BARANGAY)); Location old_zañiga = locRepo.save(new
		 * Location("OLD ZAÑIGA", BARANGAY)); Location pag_asa =
		 * locRepo.save(new Location("PAG-ASA", BARANGAY)); Location plainview =
		 * locRepo.save(new Location("PLAINVIEW", BARANGAY)); Location
		 * pleasant_hills = locRepo.save(new Location("PLEASANT HILLS",
		 * BARANGAY)); Location san_jose = locRepo.save(new Location("SAN JOSE",
		 * BARANGAY)); Location vergara = locRepo.save(new Location("VERGARA",
		 * BARANGAY)); Location wack_wack_greenhills = locRepo.save(new
		 * Location("WACK-WACK GREENHILLS", BARANGAY)); Location binondo =
		 * locRepo.save(new Location("BINONDO", BARANGAY)); Location ermita =
		 * locRepo.save(new Location("ERMITA", BARANGAY)); Location intramuros =
		 * locRepo.save(new Location("INTRAMUROS", BARANGAY)); Location malate =
		 * locRepo.save(new Location("MALATE", BARANGAY)); Location paco =
		 * locRepo.save(new Location("PACO", BARANGAY)); Location pandacan =
		 * locRepo.save(new Location("PANDACAN", BARANGAY)); Location port_area
		 * = locRepo.save(new Location("PORT AREA", BARANGAY)); Location quiapo
		 * = locRepo.save(new Location("QUIAPO", BARANGAY)); Location sampaloc =
		 * locRepo.save(new Location("SAMPALOC", BARANGAY)); Location san_andres
		 * = locRepo.save(new Location("SAN ANDRES", BARANGAY)); Location
		 * san_miguel = locRepo.save(new Location("SAN MIGUEL", BARANGAY));
		 * Location san_nicolas = locRepo.save(new Location("SAN NICOLAS",
		 * BARANGAY)); Location santa_ana = locRepo.save(new Location(
		 * "SANTA ANA", BARANGAY)); Location santa_mesa = locRepo.save(new
		 * Location("SANTA MESA", BARANGAY)); Location tondo = locRepo.save(new
		 * Location("TONDO", BARANGAY)); Location barangka = locRepo.save(new
		 * Location("BARANGKA", BARANGAY)); Location calumpang =
		 * locRepo.save(new Location("CALUMPANG", BARANGAY)); Location
		 * concepcion_i_ii = locRepo.save(new Location("CONCEPCION (I/II)",
		 * BARANGAY)); Location fortune = locRepo.save(new Location("FORTUNE",
		 * BARANGAY)); Location industrial_valley = locRepo.save(new Location(
		 * "INDUSTRIAL VALLEY", BARANGAY)); Location malanday = locRepo.save(new
		 * Location("MALANDAY", BARANGAY)); Location marikina_heights =
		 * locRepo.save(new Location("MARIKINA HEIGHTS", BARANGAY)); Location
		 * nangka = locRepo.save(new Location("NANGKA", BARANGAY)); Location
		 * parang = locRepo.save(new Location("PARANG", BARANGAY)); Location
		 * santa_elena = locRepo.save(new Location("SANTA ELENA", BARANGAY));
		 * Location alabang = locRepo.save(new Location("ALABANG", BARANGAY));
		 * Location ayala_alabang = locRepo.save(new Location("AYALA ALABANG",
		 * BARANGAY)); Location bayanan = locRepo.save(new Location("BAYANAN",
		 * BARANGAY)); Location buli = locRepo.save(new Location("BULI",
		 * BARANGAY)); Location cupang = locRepo.save(new Location("CUPANG",
		 * BARANGAY)); Location putatan = locRepo.save(new Location("PUTATAN",
		 * BARANGAY)); Location sucat = locRepo.save(new Location("SUCAT",
		 * BARANGAY)); Location tunasan = locRepo.save(new Location("TUNASAN",
		 * BARANGAY)); Location bagumbayan_north = locRepo.save(new Location(
		 * "BAGUMBAYAN NORTH", BARANGAY)); Location bagumbayan_south =
		 * locRepo.save(new Location("BAGUMBAYAN SOUTH", BARANGAY)); Location
		 * bangkulasi = locRepo.save(new Location("BANGKULASI", BARANGAY));
		 * Location daanghari = locRepo.save(new Location("DAANGHARI",
		 * BARANGAY)); Location navotas_east = locRepo.save(new Location(
		 * "NAVOTAS EAST", BARANGAY)); Location navotas_west = locRepo.save(new
		 * Location("NAVOTAS WEST", BARANGAY)); Location
		 * northbay_boulevard_north = locRepo.save(new Location(
		 * "NORTHBAY BOULEVARD NORTH", BARANGAY)); Location
		 * northbay_boulevard_south = locRepo.save(new Location(
		 * "NORTHBAY BOULEVARD SOUTH", BARANGAY)); Location
		 * northbay_boulevard_south_1 = locRepo.save(new Location(
		 * "NORTHBAY BOULEVARD SOUTH 1", BARANGAY)); Location
		 * northbay_boulevard_south_2 = locRepo.save(new Location(
		 * "NORTHBAY BOULEVARD SOUTH 2", BARANGAY)); Location
		 * northbay_boulevard_south_3 = locRepo.save(new Location(
		 * "NORTHBAY BOULEVARD SOUTH 3", BARANGAY)); Location san_rafael_village
		 * = locRepo.save(new Location("SAN RAFAEL VILLAGE", BARANGAY));
		 * Location sipac_almacen = locRepo.save(new Location("SIPAC-ALMACEN",
		 * BARANGAY)); Location tangos_north = locRepo.save(new Location(
		 * "TANGOS NORTH", BARANGAY)); Location tangos_south = locRepo.save(new
		 * Location("TANGOS SOUTH", BARANGAY)); Location tanza =
		 * locRepo.save(new Location("TANZA", BARANGAY)); Location baclaran =
		 * locRepo.save(new Location("BACLARAN", BARANGAY)); Location bf_homes =
		 * locRepo.save(new Location("BF HOMES", BARANGAY)); Location don_bosco
		 * = locRepo.save(new Location("DON BOSCO", BARANGAY)); Location
		 * don_galo = locRepo.save(new Location("DON GALO", BARANGAY)); Location
		 * la_huerta = locRepo.save(new Location("LA HUERTA", BARANGAY));
		 * Location marcelo_green = locRepo.save(new Location("MARCELO GREEN",
		 * BARANGAY)); Location merville = locRepo.save(new Location("MERVILLE",
		 * BARANGAY)); Location moonwalk = locRepo.save(new Location("MOONWALK",
		 * BARANGAY)); Location san_dionisio = locRepo.save(new Location(
		 * "SAN DIONISIO", BARANGAY)); Location san_martin_de_porres =
		 * locRepo.save(new Location("SAN MARTIN DE PORRES", BARANGAY));
		 * Location sun_valley = locRepo.save(new Location("SUN VALLEY",
		 * BARANGAY)); Location tambo = locRepo.save(new Location("TAMBO",
		 * BARANGAY)); Location vitalez = locRepo.save(new Location("VITALEZ",
		 * BARANGAY)); Location malibay = locRepo.save(new Location("MALIBAY",
		 * BARANGAY)); Location manila_bay_reclamation = locRepo.save(new
		 * Location("MANILA BAY RECLAMATION", BARANGAY)); Location maricaban =
		 * locRepo.save(new Location("MARICABAN", BARANGAY)); Location picc =
		 * locRepo.save(new Location("PICC", BARANGAY)); Location
		 * villamor_airbase = locRepo.save(new Location("VILLAMOR AIRBASE",
		 * BARANGAY)); Location bagong_ilog = locRepo.save(new Location(
		 * "BAGONG ILOG", BARANGAY)); Location bagong_katipunan =
		 * locRepo.save(new Location("BAGONG KATIPUNAN", BARANGAY)); Location
		 * bambang = locRepo.save(new Location("BAMBANG", BARANGAY)); Location
		 * buting = locRepo.save(new Location("BUTING", BARANGAY)); Location
		 * caniogan = locRepo.save(new Location("CANIOGAN", BARANGAY)); Location
		 * dela_paz = locRepo.save(new Location("DELA PAZ", BARANGAY)); Location
		 * kalawaan = locRepo.save(new Location("KALAWAAN", BARANGAY)); Location
		 * kapasigan = locRepo.save(new Location("KAPASIGAN", BARANGAY));
		 * Location kapitolyo = locRepo.save(new Location("KAPITOLYO",
		 * BARANGAY)); Location malinao = locRepo.save(new Location("MALINAO",
		 * BARANGAY)); Location mauway = locRepo.save(new Location("MAUWAY",
		 * BARANGAY)); Location maybunga = locRepo.save(new Location("MAYBUNGA",
		 * BARANGAY)); Location oranbo = locRepo.save(new Location("ORANBO",
		 * BARANGAY)); Location palatiw = locRepo.save(new Location("PALATIW",
		 * BARANGAY)); Location pinagbuhatan = locRepo.save(new
		 * Location("PINAGBUHATAN", BARANGAY)); Location pineda =
		 * locRepo.save(new Location("PINEDA", BARANGAY)); Location rosario =
		 * locRepo.save(new Location("ROSARIO", BARANGAY)); Location sagad =
		 * locRepo.save(new Location("SAGAD", BARANGAY)); Location san_joaquin =
		 * locRepo.save(new Location("SAN JOAQUIN", BARANGAY)); Location
		 * santa_lucia = locRepo.save(new Location("SANTA LUCIA", BARANGAY));
		 * Location santa_rosa = locRepo.save(new Location("SANTA ROSA",
		 * BARANGAY)); Location sumilang = locRepo.save(new Location("SUMILANG",
		 * BARANGAY)); Location ugong = locRepo.save(new Location("UGONG",
		 * BARANGAY)); Location aguho = locRepo.save(new Location("AGUHO",
		 * BARANGAY)); Location magtanggol = locRepo.save(new
		 * Location("MAGTANGGOL", BARANGAY)); Location martires =
		 * locRepo.save(new Location("MARTIRES", BARANGAY)); Location
		 * santo_rosario_kanluran = locRepo.save(new Location(
		 * "SANTO ROSARIO KANLURAN", BARANGAY)); Location santo_rosario_silangan
		 * = locRepo.save(new Location("SANTO ROSARIO SILANGAN", BARANGAY));
		 * Location tabacalera = locRepo.save(new Location("TABACALERA",
		 * BARANGAY)); Location alicia = locRepo.save(new Location("ALICIA",
		 * BARANGAY)); Location amihan = locRepo.save(new Location("AMIHAN",
		 * BARANGAY)); Location apolonio_samson = locRepo.save(new Location(
		 * "APOLONIO SAMSON", BARANGAY)); Location bagbag = locRepo.save(new
		 * Location("BAGBAG", BARANGAY)); Location bagong_lipunan_ng_crame =
		 * locRepo.save(new Location("BAGONG LIPUNAN NG CRAME", BARANGAY));
		 * Location bagong_pag_asa = locRepo.save(new Location("BAGONG PAG-ASA",
		 * BARANGAY)); Location bagong_silangan = locRepo.save(new Location(
		 * "BAGONG SILANGAN", BARANGAY)); Location bagumbayan = locRepo.save(new
		 * Location("BAGUMBAYAN", BARANGAY)); Location bagumbuhay =
		 * locRepo.save(new Location("BAGUMBUHAY", BARANGAY)); Location
		 * bahay_toro = locRepo.save(new Location("BAHAY TORO", BARANGAY));
		 * Location balingasa = locRepo.save(new Location("BALINGASA",
		 * BARANGAY)); Location balonbato = locRepo.save(new
		 * Location("BALONBATO", BARANGAY)); Location batasan_hills =
		 * locRepo.save(new Location("BATASAN HILLS", BARANGAY)); Location
		 * bayanihan = locRepo.save(new Location("BAYANIHAN", BARANGAY));
		 * Location blue_ridge_a_b = locRepo.save(new Location(
		 * "BLUE RIDGE (A/B)", BARANGAY)); Location botocan = locRepo.save(new
		 * Location("BOTOCAN", BARANGAY)); Location bungad = locRepo.save(new
		 * Location("BUNGAD", BARANGAY)); Location camp_aguinaldo =
		 * locRepo.save(new Location("CAMP AGUINALDO", BARANGAY)); Location
		 * capri = locRepo.save(new Location("CAPRI", BARANGAY)); Location
		 * central = locRepo.save(new Location("CENTRAL", BARANGAY)); Location
		 * commonwealth = locRepo.save(new Location("COMMONWEALTH", BARANGAY));
		 * Location culiat = locRepo.save(new Location("CULIAT", BARANGAY));
		 * Location damar = locRepo.save(new Location("DAMAR", BARANGAY));
		 * Location damayang_lagi = locRepo.save(new Location("DAMAYANG LAGI",
		 * BARANGAY)); Location del_monte = locRepo.save(new Location(
		 * "DEL MONTE", BARANGAY)); Location dioquino_zobel = locRepo.save(new
		 * Location("DIOQUINO ZOBEL", BARANGAY)); Location don_manuel =
		 * locRepo.save(new Location("DON MANUEL", BARANGAY)); Location
		 * doña_aurora = locRepo.save(new Location("DOÑA AURORA", BARANGAY));
		 * Location doña_imelda = locRepo.save(new Location("DOÑA IMELDA",
		 * BARANGAY)); Location doña_josefa = locRepo.save(new Location(
		 * "DOÑA JOSEFA", BARANGAY)); Location duyan_duyan = locRepo.save(new
		 * Location("DUYAN DUYAN", BARANGAY)); Location e_rodriguez =
		 * locRepo.save(new Location("E. RODRIGUEZ", BARANGAY)); Location
		 * east_kamias = locRepo.save(new Location("EAST KAMIAS", BARANGAY));
		 * Location escopa_i_iv = locRepo.save(new Location("ESCOPA (I-IV)",
		 * BARANGAY)); Location fairview = locRepo.save(new Location("FAIRVIEW",
		 * BARANGAY)); Location greater_lagro = locRepo.save(new Location(
		 * "GREATER LAGRO", BARANGAY)); Location gulod = locRepo.save(new
		 * Location("GULOD", BARANGAY)); Location holy_spirit = locRepo.save(new
		 * Location("HOLY SPIRIT", BARANGAY)); Location horseshoe =
		 * locRepo.save(new Location("HORSESHOE", BARANGAY)); Location
		 * immaculate_concepcion = locRepo.save(new Location(
		 * "IMMACULATE CONCEPCION", BARANGAY)); Location kaligayahan =
		 * locRepo.save(new Location("KALIGAYAHAN", BARANGAY)); Location
		 * kalusugan = locRepo.save(new Location("KALUSUGAN", BARANGAY));
		 * Location kamuning = locRepo.save(new Location("KAMUNING", BARANGAY));
		 * Location katipunan = locRepo.save(new Location("KATIPUNAN",
		 * BARANGAY)); Location kaunlaran = locRepo.save(new
		 * Location("KAUNLARAN", BARANGAY)); Location kristong_hari =
		 * locRepo.save(new Location("KRISTONG HARI", BARANGAY)); Location
		 * krus_na_ligas = locRepo.save(new Location("KRUS NA LIGAS",
		 * BARANGAY)); Location laging_handa = locRepo.save(new Location(
		 * "LAGING HANDA", BARANGAY)); Location libis = locRepo.save(new
		 * Location("LIBIS", BARANGAY)); Location lourdes = locRepo.save(new
		 * Location("LOURDES", BARANGAY)); Location loyola_heights =
		 * locRepo.save(new Location("LOYOLA HEIGHTS", BARANGAY)); Location
		 * malaya = locRepo.save(new Location("MALAYA", BARANGAY)); Location
		 * mangga = locRepo.save(new Location("MANGGA", BARANGAY)); Location
		 * manresa = locRepo.save(new Location("MANRESA", BARANGAY)); Location
		 * mariana = locRepo.save(new Location("MARIANA", BARANGAY)); Location
		 * mariblo = locRepo.save(new Location("MARIBLO", BARANGAY)); Location
		 * marilag = locRepo.save(new Location("MARILAG", BARANGAY)); Location
		 * masagana = locRepo.save(new Location("MASAGANA", BARANGAY)); Location
		 * masambong = locRepo.save(new Location("MASAMBONG", BARANGAY));
		 * Location matandang_balara = locRepo.save(new Location(
		 * "MATANDANG BALARA", BARANGAY)); Location milagrosa = locRepo.save(new
		 * Location("MILAGROSA", BARANGAY)); Location ns_amoranto =
		 * locRepo.save(new Location("N.S. AMORANTO", BARANGAY)); Location
		 * nagkaisang_nayon = locRepo.save(new Location("NAGKAISANG NAYON",
		 * BARANGAY)); Location nayong_kanluran = locRepo.save(new Location(
		 * "NAYONG KANLURAN", BARANGAY)); Location new_era = locRepo.save(new
		 * Location("NEW ERA", BARANGAY)); Location north_fairview =
		 * locRepo.save(new Location("NORTH FAIRVIEW", BARANGAY)); Location
		 * novaliches = locRepo.save(new Location("NOVALICHES", BARANGAY));
		 * Location obrero = locRepo.save(new Location("OBRERO", BARANGAY));
		 * Location old_capitol_site = locRepo.save(new Location(
		 * "OLD CAPITOL SITE", BARANGAY)); Location paang_bundok =
		 * locRepo.save(new Location("PAANG BUNDOK", BARANGAY)); Location
		 * pag_ibig_sa_nayon = locRepo.save(new Location("PAG-IBIG SA NAYON",
		 * BARANGAY)); Location paligsahan = locRepo.save(new
		 * Location("PALIGSAHAN", BARANGAY)); Location paltok = locRepo.save(new
		 * Location("PALTOK", BARANGAY)); Location pansol = locRepo.save(new
		 * Location("PANSOL", BARANGAY)); Location paraiso = locRepo.save(new
		 * Location("PARAISO", BARANGAY)); Location pasong_putik =
		 * locRepo.save(new Location("PASONG PUTIK", BARANGAY)); Location
		 * pasong_tamo = locRepo.save(new Location("PASONG TAMO", BARANGAY));
		 * Location payatas = locRepo.save(new Location("PAYATAS", BARANGAY));
		 * Location phil_am = locRepo.save(new Location("PHIL-AM", BARANGAY));
		 * Location pinyahan = locRepo.save(new Location("PINYAHAN", BARANGAY));
		 * Location project_6 = locRepo.save(new Location("PROJECT 6",
		 * BARANGAY)); Location quirino_2_a_c = locRepo.save(new Location(
		 * "QUIRINO 2 (A-C)", BARANGAY)); Location quirino_3_a_b =
		 * locRepo.save(new Location("QUIRINO 3 (A-B)", BARANGAY)); Location
		 * roxas = locRepo.save(new Location("ROXAS", BARANGAY)); Location
		 * sacred_heart = locRepo.save(new Location("SACRED HEART", BARANGAY));
		 * Location salvacion = locRepo.save(new Location("SALVACION",
		 * BARANGAY)); Location san_bartolome = locRepo.save(new Location(
		 * "SAN BARTOLOME", BARANGAY)); Location san_isidro_galas =
		 * locRepo.save(new Location("SAN ISIDRO GALAS", BARANGAY)); Location
		 * san_isidro_labrador = locRepo.save(new Location("SAN ISIDRO LABRADOR"
		 * , BARANGAY)); Location santa_monica = locRepo.save(new Location(
		 * "SANTA MONICA", BARANGAY)); Location santa_teresita =
		 * locRepo.save(new Location("SANTA TERESITA", BARANGAY)); Location
		 * santo_domingo = locRepo.save(new Location("SANTO DOMINGO",
		 * BARANGAY)); Location santol = locRepo.save(new Location("SANTOL",
		 * BARANGAY)); Location sauyo = locRepo.save(new Location("SAUYO",
		 * BARANGAY)); Location siena = locRepo.save(new Location("SIENA",
		 * BARANGAY)); Location sikatuna_village = locRepo.save(new Location(
		 * "SIKATUNA VILLAGE", BARANGAY)); Location socorro_cubao =
		 * locRepo.save(new Location("SOCORRO (CUBAO)", BARANGAY)); Location
		 * st_ignatius = locRepo.save(new Location("ST. IGNATIUS", BARANGAY));
		 * Location st_peter = locRepo.save(new Location("ST. PETER",
		 * BARANGAY)); Location tagumpay = locRepo.save(new Location("TAGUMPAY",
		 * BARANGAY)); Location talayan = locRepo.save(new Location("TALAYAN",
		 * BARANGAY)); Location tandang_sora = locRepo.save(new Location(
		 * "TANDANG SORA", BARANGAY)); Location tatalon = locRepo.save(new
		 * Location("TATALON", BARANGAY)); Location teachers_village =
		 * locRepo.save(new Location("TEACHER'S VILLAGE", BARANGAY)); Location
		 * ugong_norte = locRepo.save(new Location("UGONG NORTE", BARANGAY));
		 * Location unang_sigaw = locRepo.save(new Location("UNANG SIGAW",
		 * BARANGAY)); Location up_campus = locRepo.save(new Location(
		 * "UP CAMPUS", BARANGAY)); Location up_village = locRepo.save(new
		 * Location("UP VILLAGE", BARANGAY)); Location valencia =
		 * locRepo.save(new Location("VALENCIA", BARANGAY)); Location vasra =
		 * locRepo.save(new Location("VASRA", BARANGAY)); Location
		 * veterans_village = locRepo.save(new Location("VETERANS VILLAGE",
		 * BARANGAY)); Location villa_maria_clara = locRepo.save(new Location(
		 * "VILLA MARIA CLARA", BARANGAY)); Location west_kamias =
		 * locRepo.save(new Location("WEST KAMIAS", BARANGAY)); Location
		 * west_triangle = locRepo.save(new Location("WEST TRIANGLE",
		 * BARANGAY)); Location white_plains = locRepo.save(new Location(
		 * "WHITE PLAINS", BARANGAY)); Location balong_bato = locRepo.save(new
		 * Location("BALONG-BATO", BARANGAY)); Location batis = locRepo.save(new
		 * Location("BATIS", BARANGAY)); Location corazon_de_jesus =
		 * locRepo.save(new Location("CORAZON DE JESUS", BARANGAY)); Location
		 * ermitaño = locRepo.save(new Location("ERMITAÑO", BARANGAY)); Location
		 * greenhills = locRepo.save(new Location("GREENHILLS", BARANGAY));
		 * Location isabelita = locRepo.save(new Location("ISABELITA",
		 * BARANGAY)); Location kabayanan = locRepo.save(new
		 * Location("KABAYANAN", BARANGAY)); Location little_baguio =
		 * locRepo.save(new Location("LITTLE BAGUIO", BARANGAY)); Location
		 * maytunas = locRepo.save(new Location("MAYTUNAS", BARANGAY)); Location
		 * onse = locRepo.save(new Location("ONSE", BARANGAY)); Location
		 * pasadena = locRepo.save(new Location("PASADENA", BARANGAY)); Location
		 * pedro_cruz = locRepo.save(new Location("PEDRO CRUZ", BARANGAY));
		 * Location progreso = locRepo.save(new Location("PROGRESO", BARANGAY));
		 * Location rivera = locRepo.save(new Location("RIVERA", BARANGAY));
		 * Location salapan = locRepo.save(new Location("SALAPAN", BARANGAY));
		 * Location san_perfecto = locRepo.save(new Location("SAN PERFECTO",
		 * BARANGAY)); Location st_joseph = locRepo.save(new Location(
		 * "ST. JOSEPH", BARANGAY)); Location tibagan = locRepo.save(new
		 * Location("TIBAGAN", BARANGAY)); Location west_crame =
		 * locRepo.save(new Location("WEST CRAME", BARANGAY)); Location calzada
		 * = locRepo.save(new Location("CALZADA", BARANGAY)); Location
		 * central_bicutan = locRepo.save(new Location("CENTRAL BICUTAN",
		 * BARANGAY)); Location central_signal_village = locRepo.save(new
		 * Location("CENTRAL SIGNAL VILLAGE", BARANGAY)); Location
		 * fort_bonifacio = locRepo.save(new Location("FORT BONIFACIO",
		 * BARANGAY)); Location hagonoy = locRepo.save(new Location("HAGONOY",
		 * BARANGAY)); Location ibayo_tipas = locRepo.save(new Location(
		 * "IBAYO TIPAS", BARANGAY)); Location katuparan = locRepo.save(new
		 * Location("KATUPARAN", BARANGAY)); Location ligid_tipas =
		 * locRepo.save(new Location("LIGID TIPAS", BARANGAY)); Location
		 * lower_bicutan = locRepo.save(new Location("LOWER BICUTAN",
		 * BARANGAY)); Location maharlika_village = locRepo.save(new Location(
		 * "MAHARLIKA VILLAGE", BARANGAY)); Location napindan = locRepo.save(new
		 * Location("NAPINDAN", BARANGAY)); Location new_lower_bicutan =
		 * locRepo.save(new Location("NEW LOWER BICUTAN", BARANGAY)); Location
		 * north_daang_hari = locRepo.save(new Location("NORTH DAANG HARI",
		 * BARANGAY)); Location north_signal_village = locRepo.save(new
		 * Location("NORTH SIGNAL VILLAGE", BARANGAY)); Location palingon =
		 * locRepo.save(new Location("PALINGON", BARANGAY)); Location pinagsama
		 * = locRepo.save(new Location("PINAGSAMA", BARANGAY)); Location
		 * south_daang_hari = locRepo.save(new Location("SOUTH DAANG HARI",
		 * BARANGAY)); Location south_signal_village = locRepo.save(new
		 * Location("SOUTH SIGNAL VILLAGE", BARANGAY)); Location tanyag =
		 * locRepo.save(new Location("TANYAG", BARANGAY)); Location tuktukan =
		 * locRepo.save(new Location("TUKTUKAN", BARANGAY)); Location
		 * upper_bicutan = locRepo.save(new Location("UPPER BICUTAN",
		 * BARANGAY)); Location ususan = locRepo.save(new Location("USUSAN",
		 * BARANGAY)); Location wawa = locRepo.save(new Location("WAWA",
		 * BARANGAY)); Location western_bicutan = locRepo.save(new Location(
		 * "WESTERN BICUTAN", BARANGAY)); Location arkong_bato =
		 * locRepo.save(new Location("ARKONG BATO", BARANGAY)); Location
		 * balangkas = locRepo.save(new Location("BALANGKAS", BARANGAY));
		 * Location bignay = locRepo.save(new Location("BIGNAY", BARANGAY));
		 * Location bisig = locRepo.save(new Location("BISIG", BARANGAY));
		 * Location canumay = locRepo.save(new Location("CANUMAY", BARANGAY));
		 * Location coloong = locRepo.save(new Location("COLOONG", BARANGAY));
		 * Location dalandanan = locRepo.save(new Location("DALANDANAN",
		 * BARANGAY)); Location general_t_de_leon = locRepo.save(new Location(
		 * "GENERAL T. DE LEON", BARANGAY)); Location isla = locRepo.save(new
		 * Location("ISLA", BARANGAY)); Location karuhatan = locRepo.save(new
		 * Location("KARUHATAN", BARANGAY)); Location lawang_bato =
		 * locRepo.save(new Location("LAWANG BATO", BARANGAY)); Location
		 * lingunan = locRepo.save(new Location("LINGUNAN", BARANGAY)); Location
		 * mabolo = locRepo.save(new Location("MABOLO", BARANGAY)); Location
		 * malinta = locRepo.save(new Location("MALINTA", BARANGAY)); Location
		 * mapulang_lupa = locRepo.save(new Location("MAPULANG LUPA",
		 * BARANGAY)); Location marulas = locRepo.save(new Location("MARULAS",
		 * BARANGAY)); Location maysan = locRepo.save(new Location("MAYSAN",
		 * BARANGAY)); Location palasan = locRepo.save(new Location("PALASAN",
		 * BARANGAY)); Location pariancillo_villa = locRepo.save(new Location(
		 * "PARIANCILLO VILLA", BARANGAY)); Location paso_de_blas =
		 * locRepo.save(new Location("PASO DE BLAS", BARANGAY)); Location pasolo
		 * = locRepo.save(new Location("PASOLO", BARANGAY)); Location polo =
		 * locRepo.save(new Location("POLO", BARANGAY)); Location punturin =
		 * locRepo.save(new Location("PUNTURIN", BARANGAY)); Location rincon =
		 * locRepo.save(new Location("RINCON", BARANGAY)); Location tagalag =
		 * locRepo.save(new Location("TAGALAG", BARANGAY)); Location
		 * veinte_reales = locRepo.save(new Location("VEINTE REALES",
		 * BARANGAY)); Location camarin = locRepo.save(new Location("CAMARIN",
		 * BARANGAY));
		 *
		 * Location caloocan = locRepo.save(new Location("CALOOCAN", CITY));
		 * Location las_piñas = locRepo.save(new Location("LAS PIÑAS", CITY));
		 * Location makati = locRepo.save(new Location("MAKATI", CITY));
		 * Location malabon = locRepo.save(new Location("MALABON", CITY));
		 * Location mandaluyong = locRepo.save(new Location("MANDALUYONG",
		 * CITY)); Location manila = locRepo.save(new Location("MANILA", CITY));
		 * Location marikina = locRepo.save(new Location("MARIKINA", CITY));
		 * Location muntinlupa = locRepo.save(new Location("MUNTINLUPA", CITY));
		 * Location navotas = locRepo.save(new Location("NAVOTAS", CITY));
		 * Location parañaque = locRepo.save(new Location("PARAÑAQUE", CITY));
		 * Location pasay = locRepo.save(new Location("PASAY", CITY)); Location
		 * pasig = locRepo.save(new Location("PASIG", CITY)); Location pateros =
		 * locRepo.save(new Location("PATEROS", CITY)); Location quezon_city =
		 * locRepo.save(new Location("QUEZON CITY", CITY)); Location san_juan =
		 * locRepo.save(new Location("SAN JUAN", CITY)); Location taguig =
		 * locRepo.save(new Location("TAGUIG", CITY)); Location valenzuela_city
		 * = locRepo.save(new Location("VALENZUELA CITY", CITY));
		 *
		 * Location metro_manila = locRepo.save(new Location("METRO MANILA",
		 * PROVINCE)); locTreeRepo.save(new LocationTree(caloocan,
		 * metro_manila)); locTreeRepo.save(new LocationTree(las_piñas,
		 * metro_manila)); locTreeRepo.save(new LocationTree(makati,
		 * metro_manila)); locTreeRepo.save(new LocationTree(malabon,
		 * metro_manila)); locTreeRepo.save(new LocationTree(mandaluyong,
		 * metro_manila)); locTreeRepo.save(new LocationTree(manila,
		 * metro_manila)); locTreeRepo.save(new LocationTree(marikina,
		 * metro_manila)); locTreeRepo.save(new LocationTree(muntinlupa,
		 * metro_manila)); locTreeRepo.save(new LocationTree(navotas,
		 * metro_manila)); locTreeRepo.save(new LocationTree(parañaque,
		 * metro_manila)); locTreeRepo.save(new LocationTree(pasay,
		 * metro_manila)); locTreeRepo.save(new LocationTree(pasig,
		 * metro_manila)); locTreeRepo.save(new LocationTree(pateros,
		 * metro_manila)); locTreeRepo.save(new LocationTree(quezon_city,
		 * metro_manila)); locTreeRepo.save(new LocationTree(san_juan,
		 * metro_manila)); locTreeRepo.save(new LocationTree(taguig,
		 * metro_manila)); locTreeRepo.save(new LocationTree(valenzuela_city,
		 * metro_manila)); locTreeRepo.save(new LocationTree(san_jose_del_monte,
		 * bulacan)); locTreeRepo.save(new LocationTree(santa_maria, bulacan));
		 * locTreeRepo.save(new LocationTree(citrus, san_jose_del_monte));
		 * locTreeRepo.save(new LocationTree(ciudad_real, san_jose_del_monte));
		 * locTreeRepo.save(new LocationTree(dulong_bayan, san_jose_del_monte));
		 * locTreeRepo.save(new LocationTree(fatima, san_jose_del_monte));
		 * locTreeRepo.save(new LocationTree(francisco_homes,
		 * san_jose_del_monte)); locTreeRepo.save(new LocationTree(gaya_gaya,
		 * san_jose_del_monte)); locTreeRepo.save(new LocationTree(graceville,
		 * san_jose_del_monte)); locTreeRepo.save(new LocationTree(gumaoc,
		 * san_jose_del_monte)); locTreeRepo.save(new LocationTree(kaybanban,
		 * san_jose_del_monte)); locTreeRepo.save(new LocationTree(kaypian,
		 * san_jose_del_monte)); locTreeRepo.save(new LocationTree(lawang_pare,
		 * san_jose_del_monte)); locTreeRepo.save(new LocationTree(minuyan,
		 * san_jose_del_monte)); locTreeRepo.save(new LocationTree(muzon,
		 * san_jose_del_monte)); locTreeRepo.save(new LocationTree(paradise,
		 * san_jose_del_monte)); locTreeRepo.save(new LocationTree(poblacion,
		 * san_jose_del_monte)); locTreeRepo.save(new LocationTree(san_isidro,
		 * san_jose_del_monte)); locTreeRepo.save(new LocationTree(san_manuel,
		 * san_jose_del_monte)); locTreeRepo.save(new LocationTree(san_martin,
		 * san_jose_del_monte)); locTreeRepo.save(new LocationTree(san_pedro,
		 * san_jose_del_monte)); locTreeRepo.save(new LocationTree(san_rafael,
		 * san_jose_del_monte)); locTreeRepo.save(new LocationTree(san_roque,
		 * san_jose_del_monte)); locTreeRepo.save(new LocationTree(sapang_palay,
		 * san_jose_del_monte)); locTreeRepo.save(new
		 * LocationTree(saint_martin_de_porres, san_jose_del_monte));
		 * locTreeRepo.save(new LocationTree(santa_cruz, san_jose_del_monte));
		 * locTreeRepo.save(new LocationTree(santo_cristo, san_jose_del_monte));
		 * locTreeRepo.save(new LocationTree(santo_niño, san_jose_del_monte));
		 * locTreeRepo.save(new LocationTree(tungkong_mangga,
		 * san_jose_del_monte)); locTreeRepo.save(new LocationTree(maharlika,
		 * san_jose_del_monte)); locTreeRepo.save(new LocationTree(assumption,
		 * san_jose_del_monte)); locTreeRepo.save(new LocationTree(bagong_buhay,
		 * san_jose_del_monte)); locTreeRepo.save(new LocationTree(balasing,
		 * santa_maria)); locTreeRepo.save(new LocationTree(buenavista,
		 * santa_maria)); locTreeRepo.save(new LocationTree(bulac,
		 * santa_maria)); locTreeRepo.save(new LocationTree(camangyanan,
		 * santa_maria)); locTreeRepo.save(new LocationTree(catmon,
		 * santa_maria)); locTreeRepo.save(new LocationTree(caypombo,
		 * santa_maria)); locTreeRepo.save(new LocationTree(caysio,
		 * santa_maria)); locTreeRepo.save(new LocationTree(guyong,
		 * santa_maria)); locTreeRepo.save(new LocationTree(lalakhan,
		 * santa_maria)); locTreeRepo.save(new LocationTree(mag_asawang_sapa,
		 * santa_maria)); locTreeRepo.save(new LocationTree(mahabang_parang,
		 * santa_maria)); locTreeRepo.save(new LocationTree(manggahan,
		 * santa_maria)); locTreeRepo.save(new LocationTree(parada,
		 * santa_maria)); locTreeRepo.save(new LocationTree(poblacion,
		 * santa_maria)); locTreeRepo.save(new LocationTree(pulong_buhangin,
		 * santa_maria)); locTreeRepo.save(new LocationTree(san_gabriel,
		 * santa_maria)); locTreeRepo.save(new LocationTree(san_jose_patag,
		 * santa_maria)); locTreeRepo.save(new LocationTree(san_vicente,
		 * santa_maria)); locTreeRepo.save(new LocationTree(santa_clara,
		 * santa_maria)); locTreeRepo.save(new LocationTree(santa_cruz,
		 * santa_maria)); locTreeRepo.save(new LocationTree(santo_tomas,
		 * santa_maria)); locTreeRepo.save(new LocationTree(silangan,
		 * santa_maria)); locTreeRepo.save(new LocationTree(tumana,
		 * santa_maria)); locTreeRepo.save(new LocationTree(bagbaguin,
		 * santa_maria));
		 *
		 * locTreeRepo.save(new LocationTree(a_mabini, caloocan));
		 * locTreeRepo.save(new LocationTree(baesa, caloocan));
		 * locTreeRepo.save(new LocationTree(bagong_barrio, caloocan));
		 * locTreeRepo.save(new LocationTree(barrio_san_jose, caloocan));
		 * locTreeRepo.save(new LocationTree(biglang_awa, caloocan));
		 * locTreeRepo.save(new LocationTree(dagat_dagatan, caloocan));
		 * locTreeRepo.save(new LocationTree(grace_park_east, caloocan));
		 * locTreeRepo.save(new LocationTree(grace_park_west, caloocan));
		 * locTreeRepo.save(new LocationTree(heroes_del_96, caloocan));
		 * locTreeRepo.save(new LocationTree(kaunlaran_village, caloocan));
		 * locTreeRepo.save(new LocationTree(libis_reparo, caloocan));
		 * locTreeRepo.save(new LocationTree(maypajo, caloocan));
		 * locTreeRepo.save(new LocationTree(monumento, caloocan));
		 * locTreeRepo.save(new LocationTree(morning_breeze, caloocan));
		 * locTreeRepo.save(new LocationTree(sangandaan, caloocan));
		 * locTreeRepo.save(new LocationTree(santa_quiteria, caloocan));
		 * locTreeRepo.save(new LocationTree(talipapa, caloocan));
		 * locTreeRepo.save(new LocationTree(university_hills, caloocan));
		 * locTreeRepo.save(new LocationTree(almanza_dos, las_piñas));
		 * locTreeRepo.save(new LocationTree(almanza_uno, las_piñas));
		 * locTreeRepo.save(new LocationTree(caa_bf_international, las_piñas));
		 * locTreeRepo.save(new LocationTree(daniel_fajardo, las_piñas));
		 * locTreeRepo.save(new LocationTree(elias_aldana, las_piñas));
		 * locTreeRepo.save(new LocationTree(ilaya, las_piñas));
		 * locTreeRepo.save(new LocationTree(manuyo_dos, las_piñas));
		 * locTreeRepo.save(new LocationTree(manuyo_uno, las_piñas));
		 * locTreeRepo.save(new LocationTree(pamplona_dos, las_piñas));
		 * locTreeRepo.save(new LocationTree(pamplona_tres, las_piñas));
		 * locTreeRepo.save(new LocationTree(pamplona_uno, las_piñas));
		 * locTreeRepo.save(new LocationTree(pilar_village, las_piñas));
		 * locTreeRepo.save(new LocationTree(pulang_lupa_dos, las_piñas));
		 * locTreeRepo.save(new LocationTree(pulang_lupa_uno, las_piñas));
		 * locTreeRepo.save(new LocationTree(talon_cinco, las_piñas));
		 * locTreeRepo.save(new LocationTree(talon_cuatro, las_piñas));
		 * locTreeRepo.save(new LocationTree(talon_dos, las_piñas));
		 * locTreeRepo.save(new LocationTree(talon_tres, las_piñas));
		 * locTreeRepo.save(new LocationTree(talon_uno, las_piñas));
		 * locTreeRepo.save(new LocationTree(zapote, las_piñas));
		 * locTreeRepo.save(new LocationTree(bangkal, makati));
		 * locTreeRepo.save(new LocationTree(bel_air, makati));
		 * locTreeRepo.save(new LocationTree(carmona, makati));
		 * locTreeRepo.save(new LocationTree(cembo, makati));
		 * locTreeRepo.save(new LocationTree(comembo, makati));
		 * locTreeRepo.save(new LocationTree(dasmariñas, makati));
		 * locTreeRepo.save(new LocationTree(east_rembo, makati));
		 * locTreeRepo.save(new LocationTree(forbes_park, makati));
		 * locTreeRepo.save(new LocationTree(guadalupe_nuevo, makati));
		 * locTreeRepo.save(new LocationTree(guadalupe_viejo, makati));
		 * locTreeRepo.save(new LocationTree(kasilawan, makati));
		 * locTreeRepo.save(new LocationTree(la_paz, makati));
		 * locTreeRepo.save(new LocationTree(magallanes, makati));
		 * locTreeRepo.save(new LocationTree(olympia, makati));
		 * locTreeRepo.save(new LocationTree(palanan, makati));
		 * locTreeRepo.save(new LocationTree(pembo, makati));
		 * locTreeRepo.save(new LocationTree(pinagkaisahan, makati));
		 * locTreeRepo.save(new LocationTree(pio_del_pilar, makati));
		 * locTreeRepo.save(new LocationTree(pitogo, makati));
		 * locTreeRepo.save(new LocationTree(poblacion, makati));
		 * locTreeRepo.save(new LocationTree(post_proper_northside, makati));
		 * locTreeRepo.save(new LocationTree(post_proper_southside, makati));
		 * locTreeRepo.save(new LocationTree(rembo, makati));
		 * locTreeRepo.save(new LocationTree(rizal, makati));
		 * locTreeRepo.save(new LocationTree(san_antonio, makati));
		 * locTreeRepo.save(new LocationTree(san_isidro, makati));
		 * locTreeRepo.save(new LocationTree(san_lorenzo, makati));
		 * locTreeRepo.save(new LocationTree(santa_cruz, makati));
		 * locTreeRepo.save(new LocationTree(singkamas, makati));
		 * locTreeRepo.save(new LocationTree(south_cembo, makati));
		 * locTreeRepo.save(new LocationTree(tejeros, makati));
		 * locTreeRepo.save(new LocationTree(urdaneta, makati));
		 * locTreeRepo.save(new LocationTree(valenzuela, makati));
		 * locTreeRepo.save(new LocationTree(west_rembo, makati));
		 * locTreeRepo.save(new LocationTree(acacia, malabon));
		 * locTreeRepo.save(new LocationTree(baritan, malabon));
		 * locTreeRepo.save(new LocationTree(bayan_bayanan, malabon));
		 * locTreeRepo.save(new LocationTree(catmon, malabon));
		 * locTreeRepo.save(new LocationTree(concepcion, malabon));
		 * locTreeRepo.save(new LocationTree(dampalit, malabon));
		 * locTreeRepo.save(new LocationTree(flores, malabon));
		 * locTreeRepo.save(new LocationTree(hulong_duhat, malabon));
		 * locTreeRepo.save(new LocationTree(ibaba, malabon));
		 * locTreeRepo.save(new LocationTree(longos, malabon));
		 * locTreeRepo.save(new LocationTree(maysilo, malabon));
		 * locTreeRepo.save(new LocationTree(muzon, malabon));
		 * locTreeRepo.save(new LocationTree(niugan, malabon));
		 * locTreeRepo.save(new LocationTree(panghulo, malabon));
		 * locTreeRepo.save(new LocationTree(potrero, malabon));
		 * locTreeRepo.save(new LocationTree(san_agustin, malabon));
		 * locTreeRepo.save(new LocationTree(santolan, malabon));
		 * locTreeRepo.save(new LocationTree(tañong, malabon));
		 * locTreeRepo.save(new LocationTree(tinajeros, malabon));
		 * locTreeRepo.save(new LocationTree(tonsuya, malabon));
		 * locTreeRepo.save(new LocationTree(tugatog, malabon));
		 * locTreeRepo.save(new LocationTree(addition_hills, mandaluyong));
		 * locTreeRepo.save(new LocationTree(bagong_silang, mandaluyong));
		 * locTreeRepo.save(new LocationTree(barangka_drive, mandaluyong));
		 * locTreeRepo.save(new LocationTree(barangka_ibaba, mandaluyong));
		 * locTreeRepo.save(new LocationTree(barangka_ilaya, mandaluyong));
		 * locTreeRepo.save(new LocationTree(barangka_itaas, mandaluyong));
		 * locTreeRepo.save(new LocationTree(buayang_bato, mandaluyong));
		 * locTreeRepo.save(new LocationTree(burol, mandaluyong));
		 * locTreeRepo.save(new LocationTree(daang_bakal, mandaluyong));
		 * locTreeRepo.save(new LocationTree(hagdan_bato_itaas, mandaluyong));
		 * locTreeRepo.save(new LocationTree(hagdan_bato_libis, mandaluyong));
		 * locTreeRepo.save(new LocationTree(harapin_ang_bukas, mandaluyong));
		 * locTreeRepo.save(new LocationTree(highway_hills, mandaluyong));
		 * locTreeRepo.save(new LocationTree(hulo, mandaluyong));
		 * locTreeRepo.save(new LocationTree(mabini_j_rizal, mandaluyong));
		 * locTreeRepo.save(new LocationTree(malamig, mandaluyong));
		 * locTreeRepo.save(new LocationTree(namayan, mandaluyong));
		 * locTreeRepo.save(new LocationTree(new_zañiga, mandaluyong));
		 * locTreeRepo.save(new LocationTree(old_zañiga, mandaluyong));
		 * locTreeRepo.save(new LocationTree(pag_asa, mandaluyong));
		 * locTreeRepo.save(new LocationTree(plainview, mandaluyong));
		 * locTreeRepo.save(new LocationTree(pleasant_hills, mandaluyong));
		 * locTreeRepo.save(new LocationTree(poblacion, mandaluyong));
		 * locTreeRepo.save(new LocationTree(san_jose, mandaluyong));
		 * locTreeRepo.save(new LocationTree(vergara, mandaluyong));
		 * locTreeRepo.save(new LocationTree(wack_wack_greenhills,
		 * mandaluyong)); locTreeRepo.save(new LocationTree(binondo, manila));
		 * locTreeRepo.save(new LocationTree(ermita, manila));
		 * locTreeRepo.save(new LocationTree(intramuros, manila));
		 * locTreeRepo.save(new LocationTree(malate, manila));
		 * locTreeRepo.save(new LocationTree(paco, manila));
		 * locTreeRepo.save(new LocationTree(pandacan, manila));
		 * locTreeRepo.save(new LocationTree(port_area, manila));
		 * locTreeRepo.save(new LocationTree(quiapo, manila));
		 * locTreeRepo.save(new LocationTree(sampaloc, manila));
		 * locTreeRepo.save(new LocationTree(san_andres, manila));
		 * locTreeRepo.save(new LocationTree(san_miguel, manila));
		 * locTreeRepo.save(new LocationTree(san_nicolas, manila));
		 * locTreeRepo.save(new LocationTree(santa_ana, manila));
		 * locTreeRepo.save(new LocationTree(santa_cruz, manila));
		 * locTreeRepo.save(new LocationTree(santa_mesa, manila));
		 * locTreeRepo.save(new LocationTree(tondo, manila));
		 * locTreeRepo.save(new LocationTree(barangka, marikina));
		 * locTreeRepo.save(new LocationTree(calumpang, marikina));
		 * locTreeRepo.save(new LocationTree(concepcion_i_ii, marikina));
		 * locTreeRepo.save(new LocationTree(fortune, marikina));
		 * locTreeRepo.save(new LocationTree(industrial_valley, marikina));
		 * locTreeRepo.save(new LocationTree(malanday, marikina));
		 * locTreeRepo.save(new LocationTree(marikina_heights, marikina));
		 * locTreeRepo.save(new LocationTree(nangka, marikina));
		 * locTreeRepo.save(new LocationTree(parang, marikina));
		 * locTreeRepo.save(new LocationTree(san_roque, marikina));
		 * locTreeRepo.save(new LocationTree(santa_elena, marikina));
		 * locTreeRepo.save(new LocationTree(santo_niño, marikina));
		 * locTreeRepo.save(new LocationTree(tañong, marikina));
		 * locTreeRepo.save(new LocationTree(tumana, marikina));
		 * locTreeRepo.save(new LocationTree(alabang, muntinlupa));
		 * locTreeRepo.save(new LocationTree(ayala_alabang, muntinlupa));
		 * locTreeRepo.save(new LocationTree(bayanan, muntinlupa));
		 * locTreeRepo.save(new LocationTree(buli, muntinlupa));
		 * locTreeRepo.save(new LocationTree(cupang, muntinlupa));
		 * locTreeRepo.save(new LocationTree(poblacion, muntinlupa));
		 * locTreeRepo.save(new LocationTree(putatan, muntinlupa));
		 * locTreeRepo.save(new LocationTree(sucat, muntinlupa));
		 * locTreeRepo.save(new LocationTree(tunasan, muntinlupa));
		 * locTreeRepo.save(new LocationTree(bagumbayan_north, navotas));
		 * locTreeRepo.save(new LocationTree(bagumbayan_south, navotas));
		 * locTreeRepo.save(new LocationTree(bangkulasi, navotas));
		 * locTreeRepo.save(new LocationTree(daanghari, navotas));
		 * locTreeRepo.save(new LocationTree(navotas_east, navotas));
		 * locTreeRepo.save(new LocationTree(navotas_west, navotas));
		 * locTreeRepo.save(new LocationTree(northbay_boulevard_north,
		 * navotas)); locTreeRepo.save(new
		 * LocationTree(northbay_boulevard_south, navotas));
		 * locTreeRepo.save(new LocationTree(northbay_boulevard_south_1,
		 * navotas)); locTreeRepo.save(new
		 * LocationTree(northbay_boulevard_south_2, navotas));
		 * locTreeRepo.save(new LocationTree(northbay_boulevard_south_3,
		 * navotas)); locTreeRepo.save(new LocationTree(san_jose, navotas));
		 * locTreeRepo.save(new LocationTree(san_rafael_village, navotas));
		 * locTreeRepo.save(new LocationTree(san_roque, navotas));
		 * locTreeRepo.save(new LocationTree(sipac_almacen, navotas));
		 * locTreeRepo.save(new LocationTree(tangos_north, navotas));
		 * locTreeRepo.save(new LocationTree(tangos_south, navotas));
		 * locTreeRepo.save(new LocationTree(tanza, navotas));
		 * locTreeRepo.save(new LocationTree(baclaran, parañaque));
		 * locTreeRepo.save(new LocationTree(bf_homes, parañaque));
		 * locTreeRepo.save(new LocationTree(don_bosco, parañaque));
		 * locTreeRepo.save(new LocationTree(don_galo, parañaque));
		 * locTreeRepo.save(new LocationTree(la_huerta, parañaque));
		 * locTreeRepo.save(new LocationTree(marcelo_green, parañaque));
		 * locTreeRepo.save(new LocationTree(merville, parañaque));
		 * locTreeRepo.save(new LocationTree(moonwalk, parañaque));
		 * locTreeRepo.save(new LocationTree(san_antonio, parañaque));
		 * locTreeRepo.save(new LocationTree(san_dionisio, parañaque));
		 * locTreeRepo.save(new LocationTree(san_martin_de_porres, parañaque));
		 * locTreeRepo.save(new LocationTree(santo_niño, parañaque));
		 * locTreeRepo.save(new LocationTree(sun_valley, parañaque));
		 * locTreeRepo.save(new LocationTree(tambo, parañaque));
		 * locTreeRepo.save(new LocationTree(vitalez, parañaque));
		 * locTreeRepo.save(new LocationTree(malibay, pasay));
		 * locTreeRepo.save(new LocationTree(manila_bay_reclamation, pasay));
		 * locTreeRepo.save(new LocationTree(maricaban, pasay));
		 * locTreeRepo.save(new LocationTree(picc, pasay)); locTreeRepo.save(new
		 * LocationTree(san_isidro, pasay)); locTreeRepo.save(new
		 * LocationTree(san_jose, pasay)); locTreeRepo.save(new
		 * LocationTree(san_rafael, pasay)); locTreeRepo.save(new
		 * LocationTree(san_roque, pasay)); locTreeRepo.save(new
		 * LocationTree(santa_clara, pasay)); locTreeRepo.save(new
		 * LocationTree(santo_niño, pasay)); locTreeRepo.save(new
		 * LocationTree(villamor_airbase, pasay)); locTreeRepo.save(new
		 * LocationTree(bagong_ilog, pasig)); locTreeRepo.save(new
		 * LocationTree(bagong_katipunan, pasig)); locTreeRepo.save(new
		 * LocationTree(bambang, pasig)); locTreeRepo.save(new
		 * LocationTree(buting, pasig)); locTreeRepo.save(new
		 * LocationTree(caniogan, pasig)); locTreeRepo.save(new
		 * LocationTree(dela_paz, pasig)); locTreeRepo.save(new
		 * LocationTree(kalawaan, pasig)); locTreeRepo.save(new
		 * LocationTree(kapasigan, pasig)); locTreeRepo.save(new
		 * LocationTree(kapitolyo, pasig)); locTreeRepo.save(new
		 * LocationTree(malinao, pasig)); locTreeRepo.save(new
		 * LocationTree(manggahan, pasig)); locTreeRepo.save(new
		 * LocationTree(mauway, pasig)); locTreeRepo.save(new
		 * LocationTree(maybunga, pasig)); locTreeRepo.save(new
		 * LocationTree(oranbo, pasig)); locTreeRepo.save(new
		 * LocationTree(palatiw, pasig)); locTreeRepo.save(new
		 * LocationTree(pinagbuhatan, pasig)); locTreeRepo.save(new
		 * LocationTree(pineda, pasig)); locTreeRepo.save(new
		 * LocationTree(rosario, pasig)); locTreeRepo.save(new
		 * LocationTree(sagad, pasig)); locTreeRepo.save(new
		 * LocationTree(san_antonio, pasig)); locTreeRepo.save(new
		 * LocationTree(san_joaquin, pasig)); locTreeRepo.save(new
		 * LocationTree(san_jose, pasig)); locTreeRepo.save(new
		 * LocationTree(san_miguel, pasig)); locTreeRepo.save(new
		 * LocationTree(san_nicolas, pasig)); locTreeRepo.save(new
		 * LocationTree(santa_cruz, pasig)); locTreeRepo.save(new
		 * LocationTree(santa_lucia, pasig)); locTreeRepo.save(new
		 * LocationTree(santa_rosa, pasig)); locTreeRepo.save(new
		 * LocationTree(santo_tomas, pasig)); locTreeRepo.save(new
		 * LocationTree(santolan, pasig)); locTreeRepo.save(new
		 * LocationTree(sumilang, pasig)); locTreeRepo.save(new
		 * LocationTree(ugong, pasig)); locTreeRepo.save(new LocationTree(aguho,
		 * pateros)); locTreeRepo.save(new LocationTree(magtanggol, pateros));
		 * locTreeRepo.save(new LocationTree(martires, pateros));
		 * locTreeRepo.save(new LocationTree(poblacion, pateros));
		 * locTreeRepo.save(new LocationTree(san_pedro, pateros));
		 * locTreeRepo.save(new LocationTree(san_roque, pateros));
		 * locTreeRepo.save(new LocationTree(santa_ana, pateros));
		 * locTreeRepo.save(new LocationTree(santo_rosario_kanluran, pateros));
		 * locTreeRepo.save(new LocationTree(santo_rosario_silangan, pateros));
		 * locTreeRepo.save(new LocationTree(tabacalera, pateros));
		 * locTreeRepo.save(new LocationTree(alicia, quezon_city));
		 * locTreeRepo.save(new LocationTree(amihan, quezon_city));
		 * locTreeRepo.save(new LocationTree(apolonio_samson, quezon_city));
		 * locTreeRepo.save(new LocationTree(baesa, quezon_city));
		 * locTreeRepo.save(new LocationTree(bagbag, quezon_city));
		 * locTreeRepo.save(new LocationTree(bagong_lipunan_ng_crame,
		 * quezon_city)); locTreeRepo.save(new LocationTree(bagong_pag_asa,
		 * quezon_city)); locTreeRepo.save(new LocationTree(bagong_silangan,
		 * quezon_city)); locTreeRepo.save(new LocationTree(bagumbayan,
		 * quezon_city)); locTreeRepo.save(new LocationTree(bagumbuhay,
		 * quezon_city)); locTreeRepo.save(new LocationTree(bahay_toro,
		 * quezon_city)); locTreeRepo.save(new LocationTree(balingasa,
		 * quezon_city)); locTreeRepo.save(new LocationTree(balonbato,
		 * quezon_city)); locTreeRepo.save(new LocationTree(batasan_hills,
		 * quezon_city)); locTreeRepo.save(new LocationTree(bayanihan,
		 * quezon_city)); locTreeRepo.save(new LocationTree(blue_ridge_a_b,
		 * quezon_city)); locTreeRepo.save(new LocationTree(botocan,
		 * quezon_city)); locTreeRepo.save(new LocationTree(bungad,
		 * quezon_city)); locTreeRepo.save(new LocationTree(camp_aguinaldo,
		 * quezon_city)); locTreeRepo.save(new LocationTree(capri,
		 * quezon_city)); locTreeRepo.save(new LocationTree(central,
		 * quezon_city)); locTreeRepo.save(new LocationTree(commonwealth,
		 * quezon_city)); locTreeRepo.save(new LocationTree(culiat,
		 * quezon_city)); locTreeRepo.save(new LocationTree(damar,
		 * quezon_city)); locTreeRepo.save(new LocationTree(damayang_lagi,
		 * quezon_city)); locTreeRepo.save(new LocationTree(del_monte,
		 * quezon_city)); locTreeRepo.save(new LocationTree(dioquino_zobel,
		 * quezon_city)); locTreeRepo.save(new LocationTree(don_manuel,
		 * quezon_city)); locTreeRepo.save(new LocationTree(doña_aurora,
		 * quezon_city)); locTreeRepo.save(new LocationTree(doña_imelda,
		 * quezon_city)); locTreeRepo.save(new LocationTree(doña_josefa,
		 * quezon_city)); locTreeRepo.save(new LocationTree(duyan_duyan,
		 * quezon_city)); locTreeRepo.save(new LocationTree(e_rodriguez,
		 * quezon_city)); locTreeRepo.save(new LocationTree(east_kamias,
		 * quezon_city)); locTreeRepo.save(new LocationTree(escopa_i_iv,
		 * quezon_city)); locTreeRepo.save(new LocationTree(fairview,
		 * quezon_city)); locTreeRepo.save(new LocationTree(greater_lagro,
		 * quezon_city)); locTreeRepo.save(new LocationTree(gulod,
		 * quezon_city)); locTreeRepo.save(new LocationTree(holy_spirit,
		 * quezon_city)); locTreeRepo.save(new LocationTree(horseshoe,
		 * quezon_city)); locTreeRepo.save(new
		 * LocationTree(immaculate_concepcion, quezon_city));
		 * locTreeRepo.save(new LocationTree(kaligayahan, quezon_city));
		 * locTreeRepo.save(new LocationTree(kalusugan, quezon_city));
		 * locTreeRepo.save(new LocationTree(kamuning, quezon_city));
		 * locTreeRepo.save(new LocationTree(katipunan, quezon_city));
		 * locTreeRepo.save(new LocationTree(kaunlaran, quezon_city));
		 * locTreeRepo.save(new LocationTree(kristong_hari, quezon_city));
		 * locTreeRepo.save(new LocationTree(krus_na_ligas, quezon_city));
		 * locTreeRepo.save(new LocationTree(laging_handa, quezon_city));
		 * locTreeRepo.save(new LocationTree(libis, quezon_city));
		 * locTreeRepo.save(new LocationTree(lourdes, quezon_city));
		 * locTreeRepo.save(new LocationTree(loyola_heights, quezon_city));
		 * locTreeRepo.save(new LocationTree(maharlika, quezon_city));
		 * locTreeRepo.save(new LocationTree(malaya, quezon_city));
		 * locTreeRepo.save(new LocationTree(mangga, quezon_city));
		 * locTreeRepo.save(new LocationTree(manresa, quezon_city));
		 * locTreeRepo.save(new LocationTree(mariana, quezon_city));
		 * locTreeRepo.save(new LocationTree(mariblo, quezon_city));
		 * locTreeRepo.save(new LocationTree(marilag, quezon_city));
		 * locTreeRepo.save(new LocationTree(masagana, quezon_city));
		 * locTreeRepo.save(new LocationTree(masambong, quezon_city));
		 * locTreeRepo.save(new LocationTree(matandang_balara, quezon_city));
		 * locTreeRepo.save(new LocationTree(milagrosa, quezon_city));
		 * locTreeRepo.save(new LocationTree(ns_amoranto, quezon_city));
		 * locTreeRepo.save(new LocationTree(nagkaisang_nayon, quezon_city));
		 * locTreeRepo.save(new LocationTree(nayong_kanluran, quezon_city));
		 * locTreeRepo.save(new LocationTree(new_era, quezon_city));
		 * locTreeRepo.save(new LocationTree(north_fairview, quezon_city));
		 * locTreeRepo.save(new LocationTree(novaliches, quezon_city));
		 * locTreeRepo.save(new LocationTree(obrero, quezon_city));
		 * locTreeRepo.save(new LocationTree(old_capitol_site, quezon_city));
		 * locTreeRepo.save(new LocationTree(paang_bundok, quezon_city));
		 * locTreeRepo.save(new LocationTree(pag_ibig_sa_nayon, quezon_city));
		 * locTreeRepo.save(new LocationTree(paligsahan, quezon_city));
		 * locTreeRepo.save(new LocationTree(paltok, quezon_city));
		 * locTreeRepo.save(new LocationTree(pansol, quezon_city));
		 * locTreeRepo.save(new LocationTree(paraiso, quezon_city));
		 * locTreeRepo.save(new LocationTree(pasong_putik, quezon_city));
		 * locTreeRepo.save(new LocationTree(pasong_tamo, quezon_city));
		 * locTreeRepo.save(new LocationTree(payatas, quezon_city));
		 * locTreeRepo.save(new LocationTree(phil_am, quezon_city));
		 * locTreeRepo.save(new LocationTree(pinagkaisahan, quezon_city));
		 * locTreeRepo.save(new LocationTree(pinyahan, quezon_city));
		 * locTreeRepo.save(new LocationTree(project_6, quezon_city));
		 * locTreeRepo.save(new LocationTree(quirino_2_a_c, quezon_city));
		 * locTreeRepo.save(new LocationTree(quirino_3_a_b, quezon_city));
		 * locTreeRepo.save(new LocationTree(roxas, quezon_city));
		 * locTreeRepo.save(new LocationTree(sacred_heart, quezon_city));
		 * locTreeRepo.save(new LocationTree(salvacion, quezon_city));
		 * locTreeRepo.save(new LocationTree(san_agustin, quezon_city));
		 * locTreeRepo.save(new LocationTree(san_antonio, quezon_city));
		 * locTreeRepo.save(new LocationTree(san_bartolome, quezon_city));
		 * locTreeRepo.save(new LocationTree(san_isidro_galas, quezon_city));
		 * locTreeRepo.save(new LocationTree(san_isidro_labrador, quezon_city));
		 * locTreeRepo.save(new LocationTree(san_jose, quezon_city));
		 * locTreeRepo.save(new LocationTree(san_martin_de_porres,
		 * quezon_city)); locTreeRepo.save(new LocationTree(san_roque,
		 * quezon_city)); locTreeRepo.save(new LocationTree(san_vicente,
		 * quezon_city)); locTreeRepo.save(new LocationTree(sangandaan,
		 * quezon_city)); locTreeRepo.save(new LocationTree(santa_cruz,
		 * quezon_city)); locTreeRepo.save(new LocationTree(santa_lucia,
		 * quezon_city)); locTreeRepo.save(new LocationTree(santa_monica,
		 * quezon_city)); locTreeRepo.save(new LocationTree(santa_teresita,
		 * quezon_city)); locTreeRepo.save(new LocationTree(santo_cristo,
		 * quezon_city)); locTreeRepo.save(new LocationTree(santo_domingo,
		 * quezon_city)); locTreeRepo.save(new LocationTree(santo_niño,
		 * quezon_city)); locTreeRepo.save(new LocationTree(santol,
		 * quezon_city)); locTreeRepo.save(new LocationTree(sauyo,
		 * quezon_city)); locTreeRepo.save(new LocationTree(siena,
		 * quezon_city)); locTreeRepo.save(new LocationTree(sikatuna_village,
		 * quezon_city)); locTreeRepo.save(new LocationTree(silangan,
		 * quezon_city)); locTreeRepo.save(new LocationTree(socorro_cubao,
		 * quezon_city)); locTreeRepo.save(new LocationTree(st_ignatius,
		 * quezon_city)); locTreeRepo.save(new LocationTree(st_peter,
		 * quezon_city)); locTreeRepo.save(new LocationTree(tagumpay,
		 * quezon_city)); locTreeRepo.save(new LocationTree(talayan,
		 * quezon_city)); locTreeRepo.save(new LocationTree(talipapa,
		 * quezon_city)); locTreeRepo.save(new LocationTree(tandang_sora,
		 * quezon_city)); locTreeRepo.save(new LocationTree(tatalon,
		 * quezon_city)); locTreeRepo.save(new LocationTree(teachers_village,
		 * quezon_city)); locTreeRepo.save(new LocationTree(ugong_norte,
		 * quezon_city)); locTreeRepo.save(new LocationTree(unang_sigaw,
		 * quezon_city)); locTreeRepo.save(new LocationTree(up_campus,
		 * quezon_city)); locTreeRepo.save(new LocationTree(up_village,
		 * quezon_city)); locTreeRepo.save(new LocationTree(valencia,
		 * quezon_city)); locTreeRepo.save(new LocationTree(vasra,
		 * quezon_city)); locTreeRepo.save(new LocationTree(veterans_village,
		 * quezon_city)); locTreeRepo.save(new LocationTree(villa_maria_clara,
		 * quezon_city)); locTreeRepo.save(new LocationTree(west_kamias,
		 * quezon_city)); locTreeRepo.save(new LocationTree(west_triangle,
		 * quezon_city)); locTreeRepo.save(new LocationTree(white_plains,
		 * quezon_city)); locTreeRepo.save(new LocationTree(addition_hills,
		 * san_juan)); locTreeRepo.save(new LocationTree(balong_bato,
		 * san_juan)); locTreeRepo.save(new LocationTree(batis, san_juan));
		 * locTreeRepo.save(new LocationTree(corazon_de_jesus, san_juan));
		 * locTreeRepo.save(new LocationTree(ermitaño, san_juan));
		 * locTreeRepo.save(new LocationTree(greenhills, san_juan));
		 * locTreeRepo.save(new LocationTree(isabelita, san_juan));
		 * locTreeRepo.save(new LocationTree(kabayanan, san_juan));
		 * locTreeRepo.save(new LocationTree(little_baguio, san_juan));
		 * locTreeRepo.save(new LocationTree(maytunas, san_juan));
		 * locTreeRepo.save(new LocationTree(onse, san_juan));
		 * locTreeRepo.save(new LocationTree(pasadena, san_juan));
		 * locTreeRepo.save(new LocationTree(pedro_cruz, san_juan));
		 * locTreeRepo.save(new LocationTree(progreso, san_juan));
		 * locTreeRepo.save(new LocationTree(rivera, san_juan));
		 * locTreeRepo.save(new LocationTree(salapan, san_juan));
		 * locTreeRepo.save(new LocationTree(san_perfecto, san_juan));
		 * locTreeRepo.save(new LocationTree(santa_lucia, san_juan));
		 * locTreeRepo.save(new LocationTree(st_joseph, san_juan));
		 * locTreeRepo.save(new LocationTree(tibagan, san_juan));
		 * locTreeRepo.save(new LocationTree(west_crame, san_juan));
		 * locTreeRepo.save(new LocationTree(bagumbayan, taguig));
		 * locTreeRepo.save(new LocationTree(bambang, taguig));
		 * locTreeRepo.save(new LocationTree(calzada, taguig));
		 * locTreeRepo.save(new LocationTree(central_bicutan, taguig));
		 * locTreeRepo.save(new LocationTree(central_signal_village, taguig));
		 * locTreeRepo.save(new LocationTree(fort_bonifacio, taguig));
		 * locTreeRepo.save(new LocationTree(hagonoy, taguig));
		 * locTreeRepo.save(new LocationTree(ibayo_tipas, taguig));
		 * locTreeRepo.save(new LocationTree(katuparan, taguig));
		 * locTreeRepo.save(new LocationTree(ligid_tipas, taguig));
		 * locTreeRepo.save(new LocationTree(lower_bicutan, taguig));
		 * locTreeRepo.save(new LocationTree(maharlika_village, taguig));
		 * locTreeRepo.save(new LocationTree(napindan, taguig));
		 * locTreeRepo.save(new LocationTree(new_lower_bicutan, taguig));
		 * locTreeRepo.save(new LocationTree(north_daang_hari, taguig));
		 * locTreeRepo.save(new LocationTree(north_signal_village, taguig));
		 * locTreeRepo.save(new LocationTree(palingon, taguig));
		 * locTreeRepo.save(new LocationTree(pinagsama, taguig));
		 * locTreeRepo.save(new LocationTree(san_miguel, taguig));
		 * locTreeRepo.save(new LocationTree(santa_ana, taguig));
		 * locTreeRepo.save(new LocationTree(south_daang_hari, taguig));
		 * locTreeRepo.save(new LocationTree(south_signal_village, taguig));
		 * locTreeRepo.save(new LocationTree(tanyag, taguig));
		 * locTreeRepo.save(new LocationTree(tuktukan, taguig));
		 * locTreeRepo.save(new LocationTree(upper_bicutan, taguig));
		 * locTreeRepo.save(new LocationTree(ususan, taguig));
		 * locTreeRepo.save(new LocationTree(wawa, taguig));
		 * locTreeRepo.save(new LocationTree(western_bicutan, taguig));
		 * locTreeRepo.save(new LocationTree(arkong_bato, valenzuela_city));
		 * locTreeRepo.save(new LocationTree(bagbaguin, valenzuela_city));
		 * locTreeRepo.save(new LocationTree(balangkas, valenzuela_city));
		 * locTreeRepo.save(new LocationTree(bignay, valenzuela_city));
		 * locTreeRepo.save(new LocationTree(bisig, valenzuela_city));
		 * locTreeRepo.save(new LocationTree(canumay, valenzuela_city));
		 * locTreeRepo.save(new LocationTree(coloong, valenzuela_city));
		 * locTreeRepo.save(new LocationTree(dalandanan, valenzuela_city));
		 * locTreeRepo.save(new LocationTree(general_t_de_leon,
		 * valenzuela_city)); locTreeRepo.save(new LocationTree(isla,
		 * valenzuela_city)); locTreeRepo.save(new LocationTree(karuhatan,
		 * valenzuela_city)); locTreeRepo.save(new LocationTree(lawang_bato,
		 * valenzuela_city)); locTreeRepo.save(new LocationTree(lingunan,
		 * valenzuela_city)); locTreeRepo.save(new LocationTree(mabolo,
		 * valenzuela_city)); locTreeRepo.save(new LocationTree(malanday,
		 * valenzuela_city)); locTreeRepo.save(new LocationTree(malinta,
		 * valenzuela_city)); locTreeRepo.save(new LocationTree(mapulang_lupa,
		 * valenzuela_city)); locTreeRepo.save(new LocationTree(marulas,
		 * valenzuela_city)); locTreeRepo.save(new LocationTree(maysan,
		 * valenzuela_city)); locTreeRepo.save(new LocationTree(palasan,
		 * valenzuela_city)); locTreeRepo.save(new LocationTree(parada,
		 * valenzuela_city)); locTreeRepo.save(new
		 * LocationTree(pariancillo_villa, valenzuela_city));
		 * locTreeRepo.save(new LocationTree(paso_de_blas, valenzuela_city));
		 * locTreeRepo.save(new LocationTree(pasolo, valenzuela_city));
		 * locTreeRepo.save(new LocationTree(poblacion, valenzuela_city));
		 * locTreeRepo.save(new LocationTree(polo, valenzuela_city));
		 * locTreeRepo.save(new LocationTree(punturin, valenzuela_city));
		 * locTreeRepo.save(new LocationTree(rincon, valenzuela_city));
		 * locTreeRepo.save(new LocationTree(tagalag, valenzuela_city));
		 * locTreeRepo.save(new LocationTree(ugong, valenzuela_city));
		 * locTreeRepo.save(new LocationTree(veinte_reales, valenzuela_city));
		 * locTreeRepo.save(new LocationTree(bagong_silang, caloocan));
		 * locTreeRepo.save(new LocationTree(camarin, caloocan));
		 *
		 * pricingTypeRepo.save(new PricingType("PURCHASE"));
		 * pricingTypeRepo.save(new PricingType("DEALER"));
		 * pricingTypeRepo.save(new PricingType("SUPERMARKET"));
		 *
		 * truckRepo.save(new Truck("RCR359")); truckRepo.save(new
		 * Truck("RJT815")); truckRepo.save(new Truck("RJS966"));
		 *
		 * warehouseRepo.save(new Warehouse("KOOL HAUZ"));
		 * warehouseRepo.save(new Warehouse("LOMA DE GATO"));
		 * warehouseRepo.save(new Warehouse("ROYALE"));
		 */
	}
}
