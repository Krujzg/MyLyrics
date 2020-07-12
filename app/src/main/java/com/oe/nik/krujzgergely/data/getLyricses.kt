package com.oe.nik.krujzgergely.data

import com.oe.nik.krujzgergely.models.LyricsModel
import com.oe.nik.krujzgergely.models.enums.SongTypes

/*
JAZZ -> 0, HIPHOP -> 1, ROCK -> 2, METAL -> 3, PUNK -> 4, POP -> 5, COUNTRY -> 6, CLASSICAL -> 7
 */

// Not used anymore. There is a json file in raw instead.
fun getLyrics()= listOf<LyricsModel>(

    LyricsModel(
        performer = "Funktasztikus",
        title = "A vamzerek műfaja",
        lyrics_text = "Elárultátok az igazi vért, és láttám a halottakat nagyokat\n" +
                "és kicsinyeket állani az Isten előtt, és könyvek nyittatnának meg.\n" +
                "És láték egy angyalt leszállni az égből, kinél vala a mélységnek kulcsa\n" +
                "\n" +
                "ÉN VAGYOK A RAP! - Ja nem, én Funk vagyok\n" +
                "Nekem is beszóltok, vagy csak bakfisoknak balfaszok?\n" +
                "Ne csak kóstolgassatok, frájer cypher\n" +
                "Százan mentek egyre, levezényli a hájfej\n" +
                "Underground pa-dö-dö, low-budget bóvli\n" +
                "Szint alatti, moslék szagú, torzszülött csóri\n" +
                "Musztáng helyett puhos, rózsaszín póni\n" +
                "Menj vissza a lacipecsenyédbe, csinálj tócsnit!\n" +
                "Mit buziskodtok ti ott a fingszagú szobába?\n" +
                "Habiszti Jean-Michel Jarre, drogfutár pojáca\n" +
                "Enervált legendák, a sírotok megásva\n" +
                "Töltött lőfegyverrel együtt kaparlak el bátya\n" +
                "Régen, régen kezdtem, úgyhogy ne gyertek nekem ezzel\n" +
                "Hogy ki a nagyobb old school, a belem kifordul\n" +
                "A harácsoló slepp egoista vén szamara\n" +
                "Hogy fosson a nyakadra a boldogság kék madara\n" +
                "Futtat a műtrágya gyár, baszódjál meg\n" +
                "Neked Oravecz Nóri a ghostwritered!\n" +
                "Mélyebben van, mint hinnétek az alagútrendszer\n" +
                "Egyenként megyek át rajtatok, mint az úthenger\n" +
                "Halott turis pulcsi, jön a végítélet\n" +
                "Telepi huzentroger végbélféreg\n" +
                "Mondd, milyen veterán az, aki szedi a szemöldökét\n" +
                "És szoliba jár, beszari csicska gyökér?!\n" +
                "Meg milyen aranykor?\n" +
                "Az amikor rave zenére buffalóban szeleteltél, mint Pumped Gabó?\n" +
                "Diszkópatkány, vigyen el a szénanátha!\n" +
                "Annyit sem ér a szavad, mint egy tré TB-kártya!\n\n" +
                "A magyar rap a vamzerek műfaja lett!\n" +
                "A magyar rap a vamzerek műfaja lett!\n" +
                "A magyar rap a vamzerek műfaja lett!\n" +
                "Becsatornázott műszar!\n" +
                "\n" +
                "(scratch)\n" +
                "\"Nincsen érzelem! Nincsen önérzet! Nincsen lelkiismeret, de ez közérdek!\"\n" +
                "\n" +
                "Apu vedd meg nekem Fluor Tomit!\n" +
                "Hadd tegyek a szájába egy óriás túró rudit!\n" +
                "Már a hűlt helyét sem találom az etikának\n" +
                "Megvezetett sunák hazug doksi filmet csinálnak\n" +
                "Csak kokszolni, boxolni, posztolni\n" +
                "Tudod-e, hogy a Denizt kúrja a Kojsz tomi?\n" +
                "Két anál kannibál, a kula kikandikál\n" +
                "Ti vagytok a fő felbujtók\n" +
                "A boszorkány üldözést mellény zsebből könnyedén\n" +
                "Közpénzből fedezitek tuskók\n" +
                "Miért vagy rám dühös, falábú csatárok gyöngye?\n" +
                "Bazseválj csak újpesti, büdös betyár körte\n" +
                "Lehetne újra február\n" +
                "Mert még hiányzik az ózdi exed, amikor menstruálsz\n" +
                "Idegesítően visít mikk-makk Misi\n" +
                "A kurva anyádat MC Galla Miki!\n" +
                "A nyugati hízlaldából kapod a korpát\n" +
                "Majd töltök belőled egy rúd békéscsabai kolbászt\n" +
                "Te bohóc! Csatlós csaholj csak csendben\n" +
                "Elefánt jelmezben, jellemtelen habcsók ember\n" +
                "Pufók puttók, pucsító kupidó\n" +
                "Fester Addams és a száján fingó\n" +
                "Rap mekkmester, black & decker, lejárt a garancia\n" +
                "Baszd fel a kéket, baszd fel a kéket, NKA maffia!\n" +
                "A magyar rap a vamzerek műfaja lett!\n" +
                "A magyar rap a vamzerek műfaja lett!\n" +
                "A magyar rap a vamzerek műfaja lett!\n" +
                "Becsatornázott műszar!\n" +
                "\n" +
                "(scratch)\n" +
                "Guillotine Rap Technika!\n" +
                "\n" +
                "Félre ne érts, szeretem a Dunakanyart\n" +
                "Attól te még buta paraszt maradsz amíg élsz!\n" +
                "Szittya szultán, kebabos Ali baba\n" +
                "Ön paródia lettetek, mint a gagyi Bagi Nacsa, Torrente\n" +
                "A szádra veheted, de soha nem fogsz olyat írni, mint a Molylepke\n" +
                "Felkapott a szarotok a Youtube-on, az Instán\n" +
                "Csak sok a köcsög a jobbikos Dzsínó majálisán\n" +
                "Úgy kétezer környékén volt egy csúnya hasmenésem\n" +
                "Na akkor születtél te farfekvéssel\n" +
                "A másik alvajáró sokat nyelt aludt tejet\n" +
                "Vágod ez az LTP, nem egy budai lakótelep\n" +
                "Te meg kiégtél láng ember, azóta gáz vagy\n" +
                "Te raggának nevezed, én óbégatásnak amit csinálsz\n" +
                "A Red Bulltól refluxot kapsz\n" +
                "A multiknak szopsz, a malacnak nyalsz\n" +
                "Ájtatos koravén kölyök, átlátok rajtad\n" +
                "A csupa szív mögött csupán csak egy szaralak vagy\n" +
                "Amaz Horvátországból utazik ide, hogy beszóljon\n" +
                "Inkább otthon foglalj állást, nagy, lóbaszó behemótom!\n" +
                "Öklözőzsírt kenjetek a szátokra gyerkőcök\n" +
                "Autentikus, tini, mutáló nindzsa teknőcök!\n" +
                "Vamzerek énekei, kelet szégyenei\n" +
                "Az utolsó felvonásban ígérem ti jöttök!\n" +
                "A magyar rap a vamzerek műfaja lett!\n" +
                "A magyar rap a vamzerek műfaja lett!\n" +
                "A magyar rap a vamzerek műfaja lett!\n" +
                "Becsatornázott műszar!",
        song_type = SongTypes.HIPHOP.toString(),
        times_watched = 0,
        favourite = true,
        youtubeLink = "https://www.youtube.com/watch?v=1LtPWUk5ibU"
    ),

    LyricsModel(
        performer = "De:Nash",
        title = "Korona Nélkül Nincsen Király",
        lyrics_text = "{Verse 1}\n" +
                "Üldögélek otthon\n" +
                "Tanakodom egyedül\n" +
                "Kérdezi az orvosom, hogy vagy?\n" +
                "Remekül, bár kicsikét\n" +
                "Az ember attól befeszül\n" +
                "Hogy odakinn a nagyvilág\n" +
                "A pusztulásba menekül\n" +
                "Sebaj, felmegyek a boltba\n" +
                "Buli van a sorban\n" +
                "Idősek és fiatalok\n" +
                "Trécselnek\n" +
                "Remek témát szolgáltat a\n" +
                "Vészhelyzet\n" +
                "Hogy van a Gizike?\n" +
                "Víz ment a fülibe\n" +
                "A Mariék meg köhögnek\n" +
                "A Laciék csak röhögnek\n" +
                "Hogy kamu ez a helyzet\n" +
                "De nem ért vele egyet\n" +
                "A többség a sorban\n" +
                "Vita alakulgat\n" +
                "Nézek egy lányra\n" +
                "Arcán a szájmaszk\n" +
                "Kicsikét arabos\n" +
                "Olyan mint a csador\n" +
                "Szervusz, hajadon!\n" +
                "Ha megbocsátsz, a hajadon\n" +
                "Köményszagot érzek\n" +
                "A vásárlók meg néznek\n" +
                "Kiderül, hogy tényleg arab\n" +
                "Három néni dalra fakad\n" +
                "Iráni arabok hozzák a vírust\n" +
                "Velük mi egy levegőt nem szívunk\n" +
                "Haj-nana-naj-naj-naj-naj-najn\n" +
                "Velük mi egy levegőt nem szívunk\n" +
                "\n" +
                "{Verse 2}\n" +
                "Növekszik a feszültség\n" +
                "Egyre csak jobban\n" +
                "Megszólal a hangosbemondó:\n" +
                "Bomba\n" +
                "Akciók várnak örökre\n" +
                "De máris\n" +
                "Berobban a pánik\n" +
                "És világosan látszik\n" +
                "Hogy megbolydult az egész ország\n" +
                "A tetején viszont a kormány\n" +
                "Érzi, hogy nem nagy\n" +
                "Rá a kabát\n" +
                "És abszolút kontrollba\n" +
                "Vágja magát\n" +
                "Rendeleti kormányzás\n" +
                "A nép érdekében\n" +
                "Jogar van a kézben\n" +
                "Hogy mikor lesz majd vége?\n" +
                "Ilyet ne is kérdezz\n" +
                "Te kis humbug!\n" +
                "A nagy igazságot meg\n" +
                "Eddig is tudtuk, hogy\n" +
                "\n" +
                "{Chorus}\n" +
                "Korona nélkül nincsen király\n" +
                "Korona nélkül nincsen király\n" +
                "Az abszolutizmus\n" +
                "Az abszolút irány\n" +
                "Korona nélkül nincsen király\n" +
                "Korona nélkül nincsen király\n" +
                "Korona nélkül nincsen király\n" +
                "Korona nélkül nincsen király\n" +
                "Korona nélkül nincsen (nincsen)\n" +
                "\n" +
                "{Verse 3}\n" +
                "Korona sincs uralkodó nélkül\n" +
                "Az ember ebbe jól beleszédül\n" +
                "Közben meg a világégés\n" +
                "Egyre csak mélyül\n" +
                "Üldögélek otthon, de\n" +
                "Kimegyek végül\n" +
                "Vár rám az erdő\n" +
                "Magányos kerengő\n" +
                "Fülembe súg lágyan egy szellő:\n" +
                "Jézus\n" +
                "Másodszor is eljő\n" +
                "De nem nyílik az égen\n" +
                "Semmilyen felhő\n" +
                "Hirtelen kettő\n" +
                "Szarvas áll az útban\n" +
                "Mi dolga az úrnak?\n" +
                "Koronavírus van\n" +
                "Az agyam majd' kidurran\n" +
                "Az egyik rám néz, azt mondja\n" +
                "Az egészet a Momentum okozza\n" +
                "Nézzük csak meg a tényeket\n" +
                "Hogy mortalitásban kit érinthet\n" +
                "A DK nem jár jól\n" +
                "Ha megcsappan a tábor\n" +
                "A neje erre rászól:\n" +
                "Hagyd már abba, László!\n" +
                "Tudjuk, hogy a Fideszre is\n" +
                "Ugyan ezt mondod\n" +
                "De inkább most a vacsorára\n" +
                "Fordítsad a gondod!\n" +
                "Veszekedni kezdenek\n" +
                "Én arrébb is állok\n" +
                "Egy mezőn meglátok\n" +
                "Egy furcsa kis tábort\n" +
                "Egy kamupróféta\n" +
                "Nagyon óbégat\n" +
                "A gazdasági problémákra\n" +
                "Immunitást kínál\n" +
                "De kicsi pénzbe kerül, ha a\n" +
                "Szenteltvízből innál\n" +
                "Erre én már hazamegyek\n" +
                "Énekelem inkább, hogy\n" +
                "\n" +
                "\n" +
                "{Chorus}\n" +
                "Korona nélkül nincsen király\n" +
                "Korona nélkül nincsen király\n" +
                "Az abszolutizmus\n" +
                "Az abszolút irány\n" +
                "Korona nélkül nincsen király\n" +
                "\n" +
                "Korona nélkül nincsen király\n" +
                "Korona nélkül nincsen király\n" +
                "Az abszolutizmus\n" +
                "Az abszolút irány\n" +
                "Korona nélkül nincsen király\n" +
                "Korona nélkül nincsen király\n" +
                "Korona nélkül nincsen király\n" +
                "Korona nélkül nincsen király\n" +
                "Korona nélkül nincsen (nincsen)",
        song_type = SongTypes.HIPHOP.toString(),
        times_watched = 0,
        favourite = true,
        youtubeLink = "https://www.youtube.com/watch?v=MOIH3dBPx88"
    ),

    LyricsModel(
        performer = "Louis Armstrong",
        title = "What a Wonderful World",
        lyrics_text = "I see trees of green, red roses too\n" +
                "I see them blue before me and you\n" +
                "And I think to myself\n" +
                "What a wonderful world\n" +
                "I see skies of blue and clouds of white\n" +
                "The bright blessed day, the dark sacred night\n" +
                "And I think to myself\n" +
                "What a wonderful world\n" +
                "The colors of the rainbow so pretty in the sky\n" +
                "Or also on the faces of people going by\n" +
                "I see friends shaking hands, saying \"How do you do?\"\n" +
                "They're really saying, I love you\n" +
                "I hear babies crying, I watch them them grow\n" +
                "They'll learn much more and I'll never know\n" +
                "And I think to myself\n" +
                "What a wonderful world\n" +
                "Yes, I think to myself\n" +
                "What a wonderful world\n" +
                "Oh yeah",
        song_type = SongTypes.JAZZ.toString(),
        times_watched = 0,
        favourite = false,
        youtubeLink = "https://www.youtube.com/watch?v=CWzrABouyeE"
    ),

    LyricsModel(
        performer = "AC DC",
        title = "Highway To Hell",
        lyrics_text = "Living easy, living free\n" +
                "Season ticket on a one-way ride\n" +
                "Asking nothing, leave me be\n" +
                "Taking everything in my stride\n" +
                "Don't need reason, don't need rhyme\n" +
                "Ain't nothing I would rather do\n" +
                "Going down, party time\n" +
                "My friends are gonna be there too\n" +
                "I'm on the highway to hell\n" +
                "On the highway to hell\n" +
                "Highway to hell\n" +
                "I'm on the highway to hell\n" +
                "No stop signs, speed limit\n" +
                "Nobody's gonna slow me down\n" +
                "Like a wheel, gonna spin it\n" +
                "Nobody's gonna mess me around\n" +
                "Hey Satan, paid my dues\n" +
                "Playing in a rocking band\n" +
                "Hey mama, look at me\n" +
                "I'm on my way to the promised land, whoo!\n" +
                "I'm on the highway to hell\n" +
                "Highway to hell\n" +
                "I'm on the highway to hell\n" +
                "Highway to hell\n" +
                "Don't stop me\n" +
                "I'm on the highway to hell\n" +
                "On the highway to hell\n" +
                "I'm on the highway to hell\n" +
                "On the highway\n" +
                "Yeah, highway to hell\n" +
                "I'm on the highway to hell\n" +
                "Highway to hell\n" +
                "Highway to hell\n" +
                "And I'm going down\n" +
                "All the way\n" +
                "Whoa!\n" +
                "I'm on the highway to hell",
        song_type = SongTypes.ROCK.toString(),
        times_watched = 0,
        favourite = false,
        youtubeLink = "https://www.youtube.com/watch?v=l482T0yNkeo"
    ),

    LyricsModel(
        performer = "Papa Roach",
        title = "Last Resort",
        lyrics_text = "Cut my life into pieces\n" +
                "This is my last resort\n" +
                "Suffocation\n" +
                "No breathing\n" +
                "Don't give a fuck if I cut my arm, bleeding\n" +
                "This is my last resort\n" +
                "Cut my life into pieces\n" +
                "I've reached my last resort\n" +
                "Suffocation, no breathing\n" +
                "Don't give a fuck if I cut my arm, bleeding\n" +
                "Do you even care if I die bleeding?\n" +
                "Would it be wrong?, would it be right?\n" +
                "If I took my life tonight\n" +
                "Chances are that I might\n" +
                "Mutilation outta sight\n" +
                "And I'm contemplating suicide\n" +
                "'Cause I'm losing my sight\n" +
                "Losing my mind\n" +
                "Wish somebody would tell me I'm fine\n" +
                "Losing my sight\n" +
                "Losing my mind\n" +
                "Wish somebody would tell me I'm fine\n" +
                "I never realized I was spread too thin\n" +
                "Till it was too late\n" +
                "And I was empty within\n" +
                "Hungry!\n" +
                "Feeding on chaos\n" +
                "And living in sin\n" +
                "Downward spiral where do I begin?\n" +
                "It all started when I lost my mother\n" +
                "No love for myself\n" +
                "And no love for another.\n" +
                "Searching to find a love up on a higher level\n" +
                "Finding nothing but questions and devils\n" +
                "'Cause I'm losing my sight\n" +
                "Losing my mind\n" +
                "Wish somebody would tell me I'm fine\n" +
                "Losing my sight\n" +
                "Losing my mind\n" +
                "Wish somebody would tell me I'm fine\n" +
                "Nothing's alright\n" +
                "Nothing is fine\n" +
                "I'm running and I'm crying\n" +
                "I'm crying\n" +
                "I'm crying\n" +
                "I'm crying\n" +
                "I'm crying\n" +
                "I can't go on living this way\n" +
                "Cut my life into pieces\n" +
                "This is my last resort\n" +
                "Suffocation\n" +
                "No breathing\n" +
                "Don't give a fuck if I cut my arm, bleeding\n" +
                "Would it be wrong?\n" +
                "Would it be right?\n" +
                "If I took my life tonight\n" +
                "Chances are that I might\n" +
                "Mutilation outta sight\n" +
                "And I'm contemplating suicide\n" +
                "'Cause I'm losing my sight\n" +
                "Losing my mind\n" +
                "Wish somebody would tell me I'm fine\n" +
                "Losing my sight\n" +
                "Losing my mind\n" +
                "Wish somebody would tell me I'm fine\n" +
                "Nothing's alright\n" +
                "Nothing is fine\n" +
                "I'm running and I'm crying\n" +
                "I can't go on living this way\n" +
                "Can't go on\n" +
                "Living this way\n" +
                "Nothing's alright",
        song_type = SongTypes.METAL.toString(),
        times_watched = 0,
        favourite = false,
        youtubeLink = "https://www.youtube.com/watch?v=j0lSpNtjPM8"
    ),

    LyricsModel(
        performer = "Nirvana",
        title = "Smells Like Teen Spirit",
        lyrics_text = "Load up on guns, bring your friends\n" +
                "It's fun to lose and to pretend\n" +
                "She's over-bored and self-assured\n" +
                "Oh no, I know a dirty word\n" +
                "Hello, hello, hello, how low\n" +
                "Hello, hello, hello, how low\n" +
                "Hello, hello, hello, how low\n" +
                "Hello, hello, hello\n" +
                "With the lights out, it's less dangerous\n" +
                "Here we are now, entertain us\n" +
                "I feel stupid and contagious\n" +
                "Here we are now, entertain us\n" +
                "A mulatto, an albino, a mosquito, my libido\n" +
                "Yeah, hey\n" +
                "I'm worse at what I do best\n" +
                "And for this gift I feel blessed\n" +
                "Our little group has always been\n" +
                "And always will until the end\n" +
                "Hello, hello, hello, how low\n" +
                "Hello, hello, hello, how low\n" +
                "Hello, hello, hello, how low\n" +
                "Hello, hello, hello\n" +
                "With the lights out, it's less dangerous\n" +
                "Here we are now, entertain us\n" +
                "I feel stupid and contagious\n" +
                "Here we are now, entertain us\n" +
                "A mulatto, an albino, a mosquito, my libido\n" +
                "Yeah, hey\n" +
                "And I forget just why I taste\n" +
                "Oh yeah, I guess it makes me smile\n" +
                "I found it hard, it's hard to find\n" +
                "Oh well, whatever, never mind\n" +
                "Hello, hello, hello, how low\n" +
                "Hello, hello, hello, how low\n" +
                "Hello, hello, hello, how low\n" +
                "Hello, hello, hello\n" +
                "With the lights out, it's less dangerous\n" +
                "Here we are now, entertain us\n" +
                "I feel stupid and contagious\n" +
                "Here we are now, entertain us\n" +
                "A mulatto, an albino, a mosquito, my libido\n" +
                "A denial, a denial, a denial, a denial, a denial\n" +
                "A denial, a denial, a denial, a denial",
        song_type = SongTypes.PUNK.toString(),
        times_watched = 0,
        favourite = false,
        youtubeLink = "https://www.youtube.com/watch?v=hTWKbfoikeg"
    ),

    LyricsModel(
        performer = "Johnny Cash",
        title = "I Walk the line",
        lyrics_text = "You know we've been on tour for about a week now\n" +
                "After our last recording session, and\n" +
                "They say ole Johnny Cash works good under pressure\n" +
                "Put the screws on me and I'ma screw right off Monday\n" +
                "Is what I'm going to do though, you know that\n" +
                "Tired of all that shit, really\n" +
                "Alright!\n" +
                "I tell you what, the show is being recorded, and televised\n" +
                "For England\n" +
                "And um\n" +
                "They said, they told me, they said, um\n" +
                "They said uh, you gotta do this song, you gotta do that song\n" +
                "You know, you gotta stand like this or act like this\n" +
                "They just don't get it, man, you know?\n" +
                "I'm here, I'm here to do what you want me to and what I want to do, ok?\n" +
                "So what do you wanna hear?\n" +
                "Alright, alright, 'I Walk The Line'\"\n" +
                "I keep a close watch on this heart of mine\n" +
                "I keep my eyes wide open all the time\n" +
                "I keep the ends out for the tie that binds\n" +
                "Because you're mine\n" +
                "I walk the line\n" +
                "I find it very, very easy to be true\n" +
                "I find myself alone when each day is through\n" +
                "Yes, I'll admit that I'm a fool for you\n" +
                "Because you're mine\n" +
                "I walk the line\n" +
                "As sure as night is dark and day is light\n" +
                "I keep you on my mind both day and night\n" +
                "And happiness I've known proves that it's right\n" +
                "Because you're mine\n" +
                "I walk the line\n" +
                "You've got a way to keep me on your side\n" +
                "You give me 'cause for love that I can't hide\n" +
                "For you I know I'd even try to turn the tide\n" +
                "Because you're mine\n" +
                "I walk the line\n" +
                "I keep a close watch on this heart of mine\n" +
                "I keep my eyes wide open all the time\n" +
                "I keep the ends out for the tie that binds\n" +
                "Because you're mine\n" +
                "I walk the line",
        song_type = SongTypes.COUNTRY.toString(),
        times_watched = 0,
        favourite = false,
        youtubeLink = "Lq0fUa0vW_E"
    ),

    LyricsModel(
        performer = "Simándy József",
        title = "Erkel Ferenc: Bánk bán - Hazám hazám",
        lyrics_text = "Mint száműzött, ki vándorol\n" +
                "A sűrű éjen át,\n" +
                "S vad förgetegben nem lelé\n" +
                "Vezérlő csillagát,\n" +
                "Az emberszív is úgy bolyong,\n" +
                "Oly egyes-egyedül,\n" +
                "Úgy tépi künn az orkán,\n" +
                "Mint az önvád itt belül.\n" +
                "Csak egy nagy érzés éltetett\n" +
                "Sok gond és gyász alatt,\n" +
                "Hogy szent hazám és hős nevem\n" +
                "Szeplőtlen megmarad.\n" +
                "Most mind a kettő orvosra vár,\n" +
                "S míg itt töprenkedem,\n" +
                "Hazám borítja szemfödél\n" +
                "S elvész becsületem!\n" +
                "\n" +
                "Hazám, hazám, te mindenem!\n" +
                "Tudom, hogy mindenem neked köszönhetem.\n" +
                "Arany mezők, ezüst folyók,\n" +
                "Hős vértől ázottak, könnytől áradók.\n" +
                "Sajgó sebét felejti Bánk,\n" +
                "Zokog, de szolgálja népe szent javát.\n" +
                "\n" +
                "Magyar hazám, te mindenem!\n" +
                "Te érted bátran meghalok,\n" +
                "Te Szent Magyar hazám!\n" +
                "\n" +
                "\n" +
                "az utolsó három sor másik változata:\n" +
                "\n" +
                "Magyar hazám, megáldalak!\n" +
                "Szép érted élni, érted halni,\n" +
                "Te hős magyar hazám!",
        song_type = SongTypes.OPERA.toString(),
        times_watched = 0,
        favourite = false,
        youtubeLink = "https://www.youtube.com/watch?v=r2sWQgCW7Wc"
    )


)