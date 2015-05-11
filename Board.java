import java.util.*;

/**
 * The board class is responsible for keeping track 
 * of the cities locations and routes between them.
 * Includes methods to change, access and mutate
 * the routes on the board
 * 
 * Anna, Mary, Ryan, Jordan and Paul
 * Ticket to Ride: Legendary Asia
 */
public class Board
{
    //variable name for city's int vals
    public final int CHITA = 0;
    public final int ULANBATOR = 1;
    public final int KHABAROVSK = 2;
    public final int VLADIVOSTOK = 3;
    public final int KOBE = 4;
    public final int SEOUL = 5;
    public final int PEKING = 6;
    public final int XIAN = 7;
    public final int SHANGHAI = 8;
    public final int HANOI = 9;
    public final int MACAU = 10;
    public final int TAIPEI = 11;
    public final int SAIGON = 12;
    public final int SINGAPORE = 13;
    public final int BANGKOK = 14;
    public final int RANGOON = 15;
    public final int COLOMBO = 16;
    public final int BOMBAY = 17;
    public final int LHASA =18;
    public final int KARACHI = 19;
    public final int MOSCOW = 20;
    public final int PERM = 21;
    public final int TBILISI = 24;
    public final int ANKARA = 25;
    public final int SAMARKAND = 23;
    public final int CALCUTTA = 37;
    public final int KATHMANDU = 36;
    public final int MANDALAY = 38;
    public final int BAGHDAD = 28;
    public final int ASTRAKHAN = 22;
    public final int OMSK = 30;
    public final int TEHRAN = 27;
    public final int RAWALPINDI = 34;
    public final int DIHUA = 33;
    public final int KRASNOYARSK = 31;
    public final int IRKUTSK = 32;
    public final int SHIRAZ = 29;
    public final int MECCA = 26;
    public final int AGRA = 35;

    //array to hold all of the citries in order of val
    private City[] cities = new City[39];

    //array to hold the number of routes between cities
    int[][] isRoute = new int[39][39];
    Route[] routes = new Route[100];

    // array list of destination cards
    public ArrayList<DestinationCard> dests =
        new ArrayList<DestinationCard>();
    // array for the long destination cards
    public ArrayList<DestinationCard> longDests = 
        new ArrayList<DestinationCard>();

    /**
     * The Board consrtuctor creates 39 city objects
     * according to thier position on the board,
     * places them into an array to keep track of a 
     * city val.
     * It also initializes the route 2D array holding
     * the single, double, and invalid routes between cities
     */
    public Board(){
        //creating citties and putting them into appropriate 
        //arrray position according to val
        City chita = new City("Chita",619,32,0);
        cities[0] = chita;
        City ulanBator = new City("Ulan Bator",557,89,1);
        cities[1] = ulanBator;
        City khabarovsk = new City("Khabarovsk",794,50,2);
        cities[2] = khabarovsk;
        City vladivostok = new City("Vladivostok",773,133,3);
        cities[3] = vladivostok;
        City kobe = new City("Kobe",825,212,4);
        cities[4] = kobe;
        City seoul = new City("Seoul",737,214,5);
        cities[5] = seoul;
        City peking = new City("Peking",646,170,6);
        cities[6] = peking;
        City xian = new City("Xi'an",576,222,7);
        cities[7] = xian;
        City shanghai = new City("Shanghai",685,249,8);
        cities[8] = shanghai;
        City hanoi = new City("Hanoi",569,331,9);
        cities[9] = hanoi;
        City macau = new City("Macau",619,341,10);
        cities[10] = macau;
        City taipei = new City("Taipei",695,316,11);
        cities[11] = taipei;
        City saigon = new City("Saigon",568,447,12);
        cities[12] = saigon;
        City singapore = new City("Singapore",548,525,13);
        cities[13] = singapore;
        City bangkok = new City("Bangkok",525,418,14);
        cities[14] = bangkok;
        City rangoon = new City("Rangoon",486,390,15);
        cities[15] = rangoon;
        City colombo = new City("Colombo",376,495,16);
        cities[16] = colombo;
        City bombay = new City("Bombay",316,375,17);
        cities[17] = bombay;
        City lhasa = new City("Lhasa",454,253,18);
        cities[18] = lhasa;
        City karachi = new City("Karachi",261,312,19);
        cities[19] = karachi;
        City moscow = new City("Moscow",40,14,20);
        cities[20] = moscow;
        City perm = new City("Perm",167,9,21);
        cities[21] = perm;
        City astrakhan = new City("Astrakhan",107,109,22);
        cities[22] = astrakhan;
        City samarkand = new City("Samarkand",263,169,23);
        cities[23] = samarkand;
        City tbilisi = new City("Tbilisi",84,153,24);
        cities[24] = tbilisi;
        City ankara = new City("Ankara",6,172, 25);
        cities[25] = ankara;
        City mecca = new City("Mecca", 54,354,26);
        cities[26] = mecca;
        City tehran = new City("Tehran", 142,214,27);
        cities[27] = tehran;
        City baghdad = new City("Baghdad", 94,245,28);
        cities[28] = baghdad;
        City shiraz = new City("Shiraz", 182,286,29);
        cities[29] = shiraz;
        City omsk = new City("Omsk", 290,12,30);
        cities[30] = omsk;
        City krasnoyarsk = new City("Krasnoyarsk", 413,14,31);
        cities[31] = krasnoyarsk;
        City irkutsk = new City("Irkutsk", 533,19,32);
        cities[32] = irkutsk;
        City dihua = new City("Dihua", 423,129,33);
        cities[33] = dihua;
        City rawalpindi = new City("Rawalpindi", 308, 241,34);
        cities[34] = rawalpindi;
        City agra = new City("Agra", 342,298,35);
        cities[35] = agra;
        City kathmandu = new City("Kathmandu", 408,285,36);
        cities[36] = kathmandu;
        City calcutta = new City("Calcutta", 429,324,37);
        cities[37] = calcutta;
        City mandalay = new City("Mandalay", 486,335,38);
        cities[38] = mandalay;

        //ititialize all routes to 0
        for(int i = 0; i < 39; i ++)
        {
            for(int j = 0; j < 39; j ++)
            {
                isRoute[i][j] = 0;
            }
        }

        //single routes
        isRoute[PERM][ASTRAKHAN] = 1;
        isRoute[ASTRAKHAN][SAMARKAND] = 1;
        isRoute[ASTRAKHAN][TEHRAN] = 1;
        isRoute[OMSK][SAMARKAND] = 1;
        isRoute[OMSK][DIHUA] = 1;
        isRoute[TBILISI][ANKARA] = 1;
        isRoute[SAMARKAND][TEHRAN] = 1;
        isRoute[SAMARKAND][RAWALPINDI] = 1;
        isRoute[SAMARKAND][KARACHI] = 1;
        isRoute[DIHUA][KRASNOYARSK] = 1;
        isRoute[DIHUA][PEKING] = 1;
        isRoute[DIHUA][XIAN] = 1;
        isRoute[RAWALPINDI][KATHMANDU] = 1;
        isRoute[RAWALPINDI][AGRA] = 1;
        isRoute[RAWALPINDI][KARACHI] = 1;
        isRoute[KARACHI][AGRA] = 1;
        isRoute[SHIRAZ][MECCA] = 1;
        isRoute[PEKING][SEOUL] = 1;
        isRoute[PEKING][KHABAROVSK] = 1;
        isRoute[XIAN][LHASA] = 1;
        isRoute[XIAN][MANDALAY] = 1;
        isRoute[IRKUTSK][ULANBATOR] = 1;
        isRoute[KATHMANDU][MANDALAY] = 1;
        isRoute[KATHMANDU][AGRA] = 1;
        isRoute[AGRA][BOMBAY] = 1;
        isRoute[AGRA][CALCUTTA] = 1;
        isRoute[ULANBATOR][CHITA] = 1;
        isRoute[SHANGHAI][MACAU] = 1;
        isRoute[SHANGHAI][TAIPEI] = 1;
        isRoute[SHANGHAI][KOBE] = 1;
        isRoute[SEOUL][VLADIVOSTOK] = 1;
        isRoute[SEOUL][KOBE] = 1;
        isRoute[KHABAROVSK][CHITA] = 1;
        isRoute[LHASA][MANDALAY] = 1; 
        isRoute[MANDALAY][RANGOON] = 1;
        isRoute[MANDALAY][HANOI] = 1;
        isRoute[CALCUTTA][COLOMBO] = 1;
        isRoute[MACAU][TAIPEI] = 1;
        isRoute[HANOI][BANGKOK] = 1;
        isRoute[SAIGON][SINGAPORE] = 1;
        isRoute[ASTRAKHAN][PERM] = 1;
        isRoute[SAMARKAND][ASTRAKHAN] = 1;
        isRoute[TEHRAN][ASTRAKHAN] = 1;
        isRoute[SAMARKAND][OMSK] = 1;
        isRoute[DIHUA][OMSK] = 1;
        isRoute[ANKARA][TBILISI] = 1;
        isRoute[TEHRAN][SAMARKAND] = 1;
        isRoute[RAWALPINDI][SAMARKAND] = 1;
        isRoute[KARACHI][SAMARKAND] = 1;
        isRoute[KRASNOYARSK][DIHUA] = 1;
        isRoute[PEKING][DIHUA] = 1;
        isRoute[XIAN][DIHUA] = 1;
        isRoute[KATHMANDU][RAWALPINDI] = 1;
        isRoute[AGRA][RAWALPINDI] = 1;
        isRoute[KARACHI][RAWALPINDI] = 1;
        isRoute[AGRA][KARACHI] = 1;
        isRoute[MECCA][SHIRAZ] = 1;
        isRoute[SEOUL][PEKING] = 1;
        isRoute[KHABAROVSK][PEKING] = 1;
        isRoute[LHASA][XIAN] = 1;
        isRoute[MANDALAY][XIAN] = 1;
        isRoute[ULANBATOR][IRKUTSK] = 1;
        isRoute[MANDALAY][KATHMANDU] = 1;
        isRoute[AGRA][KATHMANDU] = 1;
        isRoute[BOMBAY][AGRA] = 1;
        isRoute[CALCUTTA][AGRA] = 1;
        isRoute[CHITA][ULANBATOR] = 1;
        isRoute[MACAU][SHANGHAI] = 1;
        isRoute[TAIPEI][SHANGHAI] = 1;
        isRoute[KOBE][SHANGHAI] = 1;
        isRoute[VLADIVOSTOK][SEOUL] = 1;
        isRoute[KOBE][SEOUL] = 1;
        isRoute[CHITA][KHABAROVSK] = 1;
        isRoute[MANDALAY][LHASA] = 1; 
        isRoute[RANGOON][MANDALAY] = 1;
        isRoute[HANOI][MANDALAY] = 1;
        isRoute[COLOMBO][CALCUTTA] = 1;
        isRoute[TAIPEI][MACAU] = 1;
        isRoute[BANGKOK][HANOI] = 1;
        isRoute[SINGAPORE][SAIGON] = 1;

        //double routes
        isRoute[MOSCOW][PERM] = 2;
        isRoute[MOSCOW][ASTRAKHAN] = 2;
        isRoute[PERM][OMSK] = 2;
        isRoute[ASTRAKHAN][TBILISI] = 2;
        isRoute[OMSK][KRASNOYARSK] = 2;
        isRoute[TBILISI][TEHRAN] = 2;
        isRoute[SAMARKAND][DIHUA] = 2;
        isRoute[TEHRAN][BAGHDAD] = 2;
        isRoute[TEHRAN][SHIRAZ] = 2;
        isRoute[KHABAROVSK][IRKUTSK] = 2;
        isRoute[ANKARA][BAGHDAD] = 2;
        isRoute[KARACHI][SHIRAZ] = 2;
        isRoute[KARACHI][BOMBAY] = 2;
        isRoute[BAGHDAD][MECCA] = 2;
        isRoute[PEKING][ULANBATOR] = 2;
        isRoute[PEKING][XIAN] = 2;
        isRoute[PEKING][SHANGHAI] = 2;
        isRoute[XIAN][MACAU] = 2;
        isRoute[IRKUTSK][CHITA] = 2;
        isRoute[BOMBAY][COLOMBO] = 2;
        isRoute[BOMBAY][CALCUTTA] = 2;
        isRoute[KHABAROVSK][VLADIVOSTOK] = 2;
        isRoute[MANDALAY][CALCUTTA] = 2;
        isRoute[CALCUTTA][RANGOON] = 2;
        isRoute[MACAU][HANOI] = 2;
        isRoute[TAIPEI][KOBE] = 2;
        isRoute[RANGOON][BANGKOK] = 2;
        isRoute[HANOI][SAIGON] = 2;
        isRoute[BANGKOK][SINGAPORE] = 2;
        isRoute[BANGKOK][SAIGON] = 2;  
        isRoute[PERM][MOSCOW] = 2;
        isRoute[ASTRAKHAN][MOSCOW] = 2;
        isRoute[OMSK][PERM] = 2;
        isRoute[TBILISI][ASTRAKHAN] = 2;
        isRoute[KRASNOYARSK][OMSK] = 2;
        isRoute[TEHRAN][TBILISI] = 2;
        isRoute[DIHUA][SAMARKAND] = 2;
        isRoute[BAGHDAD][TEHRAN] = 2;
        isRoute[SHIRAZ][TEHRAN] = 2;
        isRoute[IRKUTSK][KHABAROVSK] = 2;
        isRoute[BAGHDAD][ANKARA] = 2;
        isRoute[SHIRAZ][KARACHI] = 2;
        isRoute[BOMBAY][KARACHI] = 2;
        isRoute[MECCA][BAGHDAD] = 2;
        isRoute[ULANBATOR][PEKING] = 2;
        isRoute[XIAN][PEKING] = 2;
        isRoute[SHANGHAI][PEKING] = 2;
        isRoute[MACAU][XIAN] = 2;
        isRoute[CHITA][IRKUTSK] = 2;
        isRoute[COLOMBO][BOMBAY] = 2;
        isRoute[CALCUTTA][BOMBAY] = 2;
        isRoute[VLADIVOSTOK][KHABAROVSK] = 2;
        isRoute[CALCUTTA][MANDALAY] = 2;
        isRoute[RANGOON][CALCUTTA] = 2;
        isRoute[HANOI][MACAU] = 2;
        isRoute[KOBE][TAIPEI] = 2;
        isRoute[BANGKOK][RANGOON] = 2;
        isRoute[SAIGON][HANOI] = 2;
        isRoute[SINGAPORE][BANGKOK] = 2;
        isRoute[SAIGON][BANGKOK] = 2; 
        isRoute[KRASNOYARSK][IRKUTSK] = 2;
        isRoute[IRKUTSK][KRASNOYARSK] = 2;

        //array list for long destinations with path string, 
        //points, and the cities the path is between
        longDests.add(new DestinationCard("Path is from ASKTRAKHAN to" 
                + " HANOI",18,astrakhan,hanoi));
        longDests.add(new DestinationCard("Path is from KHABAROVSK"
                +" to KARACHI",17,khabarovsk,karachi));
        longDests.add(new DestinationCard("Path is from KRASNOYARSK to"+
                " SINGAPORE",17,krasnoyarsk,singapore));
        longDests.add(new DestinationCard("Path is from ANKARA to COLOMBO",
                18,ankara,colombo));
        longDests.add(new DestinationCard("Path is from OMSK to KOBE",
                16,omsk,kobe));
        longDests.add(new DestinationCard("Path is from MOSCOW to CALCUTTA",
                16,moscow,calcutta));

        //array list for short destinations with path string,
        //points, and the cities the path is between
        dests.add(new DestinationCard("Path is from ASKTRAKHAN to" 
                +" KARACHI",10,astrakhan,karachi));
        dests.add(new DestinationCard("Path is from DIHUA to MANDALY"
            ,10,dihua,mandalay));
        dests.add(new DestinationCard("Path is from DIHUA to SEOUL"
            ,9,dihua,seoul));
        dests.add(new DestinationCard("Path is from IRKUTSK to DIHUA"
            ,6,irkutsk,dihua));
        dests.add(new DestinationCard("Path is from IRKUTSK to"+
                " KHABAROVSK",7,irkutsk,khabarovsk));
        dests.add(new DestinationCard("Path is from KATHMANDU to"+
                " CALCUTTA",5,kathmandu,calcutta));
        dests.add(new DestinationCard("Path is from KOBE to MACAU",7
            ,kobe,macau));
        dests.add(new DestinationCard("Path is from MACAU to SINGAPORE"
            ,6, macau,singapore));
        dests.add(new DestinationCard("Path is from XI'AN to TAIPEI",5
            ,xian,taipei));
        dests.add(new DestinationCard("Path is from MOSCOW to MECCA",10,
                moscow,mecca));
        dests.add(new DestinationCard("Path is from LHASA to BANGKOK",7,
                lhasa,bangkok));
        dests.add(new DestinationCard("Path is from KRASNOYARSK to SAMARKAND"
            ,8,krasnoyarsk,samarkand));
        dests.add(new DestinationCard("Path is from CHITA to SHANGHAI",8,
                chita,shanghai));
        dests.add(new DestinationCard("Path is from KRASNOYARSK to PEKING"
            ,9,krasnoyarsk,peking));
        dests.add(new DestinationCard("Path is from XI'AN to BOMBAY",9
            ,xian,bombay));
        dests.add(new DestinationCard("Path is from VLADIVOSTOK to LHASA",12
            ,vladivostok,lhasa));
        dests.add(new DestinationCard("Path is from ULAN BATOR to VLADIVOSTOK"
            ,9,ulanBator,vladivostok));
        dests.add(new DestinationCard("Path is from TEHRAN to KATHMANDU",
            11,tehran, kathmandu));
        dests.add(new DestinationCard("Path is from TBILISI to SHIRAZ",7,
            tbilisi,shiraz));
        dests.add(new DestinationCard("Path is from MOSCOW to ANKARA",7,
        moscow, ankara));
        dests.add(new DestinationCard("Path is from OMSK to ASTRAKHAN",6,
            omsk,astrakhan));
        dests.add(new DestinationCard("Path is from OMSK to BAGHDAD",10,omsk,
                baghdad));
        dests.add(new DestinationCard("Path is from PEKING to SAIGON",9,
            peking,saigon));
        dests.add(new DestinationCard("Path is from PERM to IRKUTSK",9,perm,
                irkutsk));
        dests.add(new DestinationCard("Path is from PERM to TEHRAN",6,perm,
                tehran));
        dests.add(new DestinationCard("Path is from RANGOON to COLOMBO",7,
            rangoon,colombo));
        dests.add(new DestinationCard("Path is from RAWALPINDI to BOMBAY",5,
                rawalpindi,bombay));
        dests.add(new DestinationCard("Path is from SAMARKAND to AGRA",6,
            samarkand,agra));
        dests.add(new DestinationCard("Path is from SAMARKAND to MECCA",8,
            samarkand,mecca));
        dests.add(new DestinationCard("Path is from SEOUL to HANOI",9,seoul,
                hanoi));

        routes[0] = new Route(moscow,perm,3,1,
            "Red",false);
        routes[1] = new Route(moscow,perm,3,0,
            "Gray",false);
        routes[2] = new Route(moscow,astrakhan,3,0,
            "Brown",false);
        routes[3] = new Route(perm,omsk,3,1,"White",
            false);
        routes[4] = new Route(perm,astrakhan,3,0,
            "Blue",false);
        routes[5] = new Route(perm,omsk,3,0,"Gray",
            false);
        routes[6] = new Route(astrakhan,tbilisi,1,0,
            "Black",false);
        routes[7] = new Route(astrakhan,tbilisi,1,0,
            "White",false);
        routes[8] = new Route(astrakhan,
            samarkand,5,0,"Purple",true);
        routes[9] = new Route(astrakhan,tehran,3,0,
            "Gray",true);
        routes[10] = new Route(tbilisi,ankara,2,1,
            "Yellow",false);
        routes[11] = new Route(tbilisi,tehran,2,1,
            "Brown",false);
        routes[12] = new Route(tbilisi,tehran,2,1,
            "Red",false);
        routes[13] = new Route(ankara,baghdad,3,0,
            "Purple",false);
        routes[14] = new Route(ankara,baghdad,3,0,
            "White",false);
        routes[15] = new Route(samarkand,tehran,3,1,
            "Black",false);
        routes[16] = new Route(samarkand,omsk,4,1,
            "Green",false);
        routes[17] = new Route(samarkand,dihua,5,0,
            "Gray",false);
        routes[18] = new Route(samarkand,dihua,5,0,
            "Gray",false); 
        routes[19] = new Route(samarkand,karachi,
            4,1,"White",false);
        routes[20] = new Route(tehran,baghdad,1,0,
            "Blue",false);
        routes[21] = new Route(tehran,baghdad,1,0,
            "Yellow",false);
        routes[22] = new Route(tehran,shiraz,2,2,
            "White",false);
        routes[23] = new Route(tehran,shiraz,2,2,
            "Green",false);
        routes[24] = new Route(baghdad,mecca,3,0,
            "Green",false);
        routes[25] = new Route(baghdad,mecca,3,0,
            "Red",false);
        routes[26] = new Route(shiraz,mecca,4,0,
            "Yellow",true);
        routes[27] = new Route(shiraz,karachi,2,2,
            "Purple",false);
        routes[28] = new Route(shiraz,karachi,2,2,
            "Blue",false);
        routes[29] = new Route(karachi,bombay,2,0,
            "Gray",false);
        routes[30] = new Route(karachi,bombay,2,0,
            "Gray",false);
        routes[31] = new Route(omsk,krasnoyarsk,3,
            1,"Purple",false);
        routes[32] = new Route(omsk,krasnoyarsk,3,0,
            "Gray",false);
        routes[33] = new Route(omsk,dihua,5,0,"Brown",
            false);
        routes[34] = new Route(krasnoyarsk,dihua,3,0,
            "Black",false);
        routes[35] = new Route(krasnoyarsk,irkutsk,
            3,0,"Gray",false);
        routes[36] = new Route(krasnoyarsk,irkutsk,
            3,1,"Blue",false);
        routes[37] = new Route(dihua,peking,6,0,"Gray",
            false);
        routes[38] = new Route(dihua,xian,5,0,"Yellow",
            false);
        routes[39] = new Route(rawalpindi,samarkand,
            2,1,"Blue",false);
        routes[40] = new Route(rawalpindi,kathmandu,
            3,1,"Yellow",false);
        routes[41] = new Route(rawalpindi,karachi,2,1,
            "Black",false);
        routes[42] = new Route(rawalpindi,agra,2,1,
            "Green",false);
        routes[43] = new Route(lhasa,xian,4,1,"Blue",false);
        routes[44] = new Route(lhasa,mandalay,2,2,"Black",
            false);
        routes[45] = new Route(kathmandu,agra,2,1,"Purple",
            false);
        routes[46] = new Route(kathmandu,mandalay,2,2,
            "Brown",false);
        routes[47] = new Route(agra,karachi,2,0,"Red",false);
        routes[48] = new Route(agra,bombay,2,0,"Brown",false);
        routes[49] = new Route(agra,calcutta,2,0,
            "Gray",false);
        routes[50] = new Route(calcutta,bombay,3,0,
            "Yellow",false);
        routes[51] = new Route(calcutta,bombay,3,0,
            "Purple",false);
        routes[52] = new Route(calcutta,colombo,5,0,
            "Green",true);
        routes[53] = new Route(calcutta,rangoon,2,0,
            "Blue",false);
        routes[54] = new Route(calcutta,rangoon,2,0,
            "Black",false);
        routes[55] = new Route(calcutta,mandalay,1,
            0,"Gray",false);
        routes[56] = new Route(calcutta,mandalay,1,
            0,"Gray",false);
        routes[57] = new Route(mandalay,hanoi,2,2,
            "Red",false);
        routes[58] = new Route(mandalay,rangoon,1,1,
            "Gray",false);
        routes[59] = new Route(bombay,colombo,4,0,
            "Black",true);
        routes[60] = new Route(bombay,colombo,4,0,
            "Red",true);
        routes[61] = new Route(rangoon,bangkok,1,0,
            "Green",false);
        routes[62] = new Route(rangoon,bangkok,1,0,
            "Brown",false);
        routes[63] = new Route(bangkok,saigon,1,0,
            "Purple",false);
        routes[64] = new Route(bangkok,saigon,1,0,
            "Red",false);
        routes[65] = new Route(bangkok,singapore,
            3,0,"Black",false);
        routes[66] = new Route(bangkok,singapore,
            3,0,"Blue",false);
        routes[67] = new Route(irkutsk,ulanBator,
            2,2,"Yellow",false);
        routes[68] = new Route(chita,khabarovsk,5,0,
            "Red",false);
        routes[69] = new Route(chita,ulanBator,2,0,
            "Purple",false);
        routes[70] = new Route(chita,irkutsk,2,1,
            "White",false);
        routes[71] = new Route(chita,irkutsk,2,0,
            "Gray",false);
        routes[72] = new Route(khabarovsk,peking,5,0,
            "Black",false);
        routes[73] = new Route(khabarovsk,vladivostok,
            2,1,"Gray",false);
        routes[74] = new Route(khabarovsk,vladivostok,
            2,0,"Gray",false);
        routes[75] = new Route(ulanBator,peking,3,1,
            "Brown",false);
        routes[76] = new Route(ulanBator,peking,3,1,
            "Red",false);
        routes[77] = new Route(vladivostok,seoul,2,0,
            "Gray",true);
        routes[78] = new Route(peking,xian,2,0,"Gray",false);
        routes[79] = new Route(peking,xian,2,0,"Gray",false);
        routes[80] = new Route(peking,seoul,3,0,"Gray",
            false);
        routes[81] = new Route(peking,shanghai,2,0,
            "Green",false);
        routes[82] = new Route(peking,shanghai,2,0,
            "Yellow",false);
        routes[83] = new Route(seoul,kobe,2,0,"Gray",false);
        routes[84] = new Route(kobe,shanghai,4,0,
            "Brown",true);
        routes[85] = new Route(kobe,taipei,5,0,"White",
            true);
        routes[86] = new Route(kobe,taipei,5,0,"Blue",
            true);
        routes[87] = new Route(xian,mandalay,4,1,
            "Purple",false);
        routes[88] = new Route(xian,macau,3,0,"Green",
            false);
        routes[89] = new Route(xian,macau,3,0,"White",
            false);
        routes[90] = new Route(shanghai,macau,3,0,
            "Gray",false);
        routes[91] = new Route(shanghai,taipei,2,0,
            "Gray",true);
        routes[92] = new Route(taipei,macau,2,0,
            "Gray",true);
        routes[93] = new Route(hanoi,macau,1,0,"Gray",
            false);
        routes[94] = new Route(hanoi,macau,1,0,"Gray",
            false);
        routes[95] = new Route(hanoi,saigon,3,0,
            "Yellow",false);
        routes[96] = new Route(hanoi,saigon,3,0,
            "Brown",false);
        routes[97] = new Route(saigon,singapore,2,0,
            "Gray",true);
        routes[98] = new Route(hanoi,bangkok,2,1,"White",false);
        routes[99] = new Route(moscow,astrakhan,3,0,"Green",false);

    }


    /**
     * Method that returns a route
     * 
     * @param begin is the first city location
     * @param end is the end city location
     * 
     * @return the int value of the route
     */
    public int getRoute(int begin, int end)
    {
        return isRoute[begin][end];
    }


    /**
     * Sets a route to captured
     * 
     * @param begin is the first city location
     * @param end is the end city location
     */
    public void capture(int begin, int end)
    {
        isRoute[begin][end] = 0;
        isRoute[end][begin] = 0;
    }


    /**
     * Method to access the destination deck
     * 
     * @return the ArrayList of destination cards
     */
    public ArrayList<DestinationCard> getDestDeck()
    {
        return dests;
    }


    /**
     * Method to access the long destinations
     * 
     * @return the ArrayList of Long Dest Cards
     */
    public ArrayList<DestinationCard> getLongDestDeck()
    {
        return longDests;
    }

    /**
     * this method takes the x,y points of the mouse click
     * and returns the city (if any) that the mouse was on
     * 
     * @param x is the x value of the mouse click
     * @param y is the y value of the mouse click
     * @return the city (if any) corresponging to the point
     */
    public City cityClicked(int x, int y){
        for(int i = 0; i<39;i++){
            City tempC = cities[i];
            if(tempC.isSelected(x,y)){
                return tempC;
            }
        }
        return null;
    }

    /**
     * Method to retrieve a specific route from the
     * routes array
     * 
     * @param a is the first city
     * @param b is the second city
     * @return the route between the two cities, null
     * if there is none
     */
    public Route getRoute(City a, City b){
        // Goes through the routes array and searches
        // for a route with the two specified cities. 
        for (int i = 0; i < routes.length; i++){
            Route r = routes[i];
            String first = r.getCityA();
            String second = r.getCityB();

            if (first.equals(a.getName()) ||
            second.equals(a.getName())){
                if(first.equals(b.getName()) ||
                second.equals(b.getName())){
                    return r;
                } 
            }
        }
        return null;
    }

    /**
     * Checks to see if a route is a double route
     * 
     * @param begin is the first city
     * @param end is the second city
     * @return boolean, returns true if the route is a double
     * and false if the route is a single
     */
    public ArrayList<Route> getArray(City begin, City end){
        // creates a new array list for the two routes to be 
        // stored
        ArrayList<Route> r = new ArrayList<Route>();
        // for the length of the route array, it searches
        // through each route and if there are two routes
        // that match, they are put into the array and returned
        for (int i = 0; i < routes.length; i++){
            Route temp = routes[i];
            String first = temp.getCityA();
            String second = temp.getCityB();
            if(first.equals(begin.getName()) || 
            second.equals(begin.getName())){
                if(first.equals(end.getName())|| 
                second.equals(end.getName())){
                    r.add(routes[i]);
                }
            }
        }
        return r;
    }

}
