package com.sfkao.pokeviewer.utils;

import android.graphics.drawable.Drawable;

import com.sfkao.pokeviewer.modelo.pojo_tipos.DoubleDamageFrom;
import com.sfkao.pokeviewer.modelo.pojo_tipos.HalfDamageFrom;
import com.sfkao.pokeviewer.modelo.pojo_tipos.NoDamageFrom;
import com.sfkao.pokeviewer.modelo.pojo_tipos.Tipo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase que añade funciones desde el lugar que se necesiten.
 * Alacena un diccionario con los tipos y sus correspondientes imagenes.
 */
public class Util  {

    public static HashMap<String, Drawable> diccionarioNombreAID;

    /**
     * Devuelve la imagen a partir del tipo
     * @param type nombre del tipo en ingles (como lo devuelve la api)
     * @return imagen del tipo
     */
    public static Drawable getType(String type){
        return diccionarioNombreAID.get(type);
    }

    /**
     * Metodo que calcula la relacion de tipos a partir de dos tipos, tendrá mas utilidad en un futuro
     * @param tipo1 tipo 1 del pokemon
     * @param tipo2 tipo 2 del pokemon
     * @return Map con sus distintas relaciones
     */
    public static Map<String,ArrayList<String>> calcularRelacionDeTipos(Tipo tipo1, Tipo tipo2){
        ArrayList<String> debilidades = new ArrayList<>();
        ArrayList<String> debilidadesX4 = new ArrayList<>();
        ArrayList<String> inmunidades = new ArrayList<>();
        ArrayList<String> resistencias = new ArrayList<>();
        ArrayList<String> resistenciasX4 = new ArrayList<>();


        for(DoubleDamageFrom d : tipo1.getDamageRelations().getDoubleDamageFrom()){
            debilidades.add(d.getName());
        }

        for(HalfDamageFrom d : tipo1.getDamageRelations().getHalfDamageFrom()){
            resistencias.add(d.getName());
        }

        for(NoDamageFrom d : tipo1.getDamageRelations().getNoDamageFrom()){
            inmunidades.add(d.getName());
        }



        if(tipo2 != null) {
            for(NoDamageFrom d : tipo2.getDamageRelations().getNoDamageFrom()){
                if(!inmunidades.contains(d.getName())) {
                    inmunidades.add(d.getName());
                    debilidades.remove(d.getName());
                    resistencias.remove(d.getName());
                }
            }

            for(DoubleDamageFrom d : tipo2.getDamageRelations().getDoubleDamageFrom()){
                String nombre = d.getName();
                if(debilidades.contains(nombre)) {
                    debilidades.remove(nombre);
                    debilidadesX4.add(nombre);
                }else if(resistencias.contains(nombre)){
                    resistencias.remove(nombre);
                }else if(!inmunidades.contains(nombre)){
                    debilidades.add(nombre);
                }
            }

            for(HalfDamageFrom d : tipo2.getDamageRelations().getHalfDamageFrom()){
                String nombre = d.getName();
                if(resistencias.contains(nombre)) {
                    resistencias.remove(nombre);
                    resistenciasX4.add(nombre);
                }else if(debilidades.contains(nombre)){
                    debilidades.remove(nombre);
                }else if(!inmunidades.contains(nombre)){
                    resistencias.add(nombre);
                }
            }

        }

        HashMap<String, ArrayList<String>> map = new HashMap<>();
        map.put("debilidades",debilidades);
        map.put("debilidadesX4",debilidadesX4);
        map.put("resistencias",resistencias);
        map.put("resistenciasX4",resistenciasX4);
        map.put("inmunidades",inmunidades);
        return map;
    }

    /**
     * Lista con todos los nombre de los pokemons hasta gen 8 (se actualizara cuando añadan la 9 en la pokeapi)
     * Se utiliza en el autocompletar del buscador
     */
    public static final String[] POKEMONS = new String[]{
            "bulbasaur","ivysaur","venusaur","charmander","charmeleon","charizard","squirtle","wartortle","blastoise","caterpie","metapod","butterfree","weedle","kakuna","beedrill","pidgey","pidgeotto","pidgeot","rattata","raticate","spearow","fearow","ekans","arbok","pikachu","raichu","sandshrew","sandslash","nidoran-f","nidorina","nidoqueen","nidoran-m","nidorino","nidoking","clefairy","clefable","vulpix","ninetales","jigglypuff","wigglytuff","zubat","golbat","oddish","gloom","vileplume","paras","parasect","venonat","venomoth","diglett","dugtrio","meowth","persian","psyduck","golduck","mankey","primeape","growlithe","arcanine","poliwag","poliwhirl","poliwrath","abra","kadabra","alakazam","machop","machoke","machamp","bellsprout","weepinbell","victreebel","tentacool","tentacruel","geodude","graveler","golem","ponyta","rapidash","slowpoke","slowbro","magnemite","magneton","farfetchd","doduo","dodrio","seel","dewgong","grimer","muk","shellder","cloyster","gastly","haunter","gengar","onix","drowzee","hypno","krabby","kingler","voltorb","electrode","exeggcute","exeggutor","cubone","marowak","hitmonlee","hitmonchan","lickitung","koffing","weezing","rhyhorn","rhydon","chansey","tangela","kangaskhan","horsea","seadra","goldeen","seaking","staryu","starmie","mr-mime","scyther","jynx","electabuzz","magmar","pinsir","tauros","magikarp","gyarados","lapras","ditto","eevee","vaporeon","jolteon","flareon","porygon","omanyte","omastar","kabuto","kabutops","aerodactyl","snorlax","articuno","zapdos","moltres","dratini","dragonair","dragonite","mewtwo","mew","chikorita","bayleef","meganium","cyndaquil","quilava","typhlosion","totodile","croconaw","feraligatr","sentret","furret","hoothoot","noctowl","ledyba","ledian","spinarak","ariados","crobat","chinchou","lanturn","pichu","cleffa","igglybuff","togepi","togetic","natu","xatu","mareep","flaaffy","ampharos","bellossom","marill","azumarill","sudowoodo","politoed","hoppip","skiploom","jumpluff","aipom","sunkern","sunflora","yanma","wooper","quagsire","espeon","umbreon","murkrow","slowking","misdreavus","unown","wobbuffet","girafarig","pineco","forretress","dunsparce","gligar","steelix","snubbull","granbull","qwilfish","scizor","shuckle","heracross","sneasel","teddiursa","ursaring","slugma","magcargo","swinub","piloswine","corsola","remoraid","octillery","delibird","mantine","skarmory","houndour","houndoom","kingdra","phanpy","donphan","porygon2","stantler","smeargle","tyrogue","hitmontop","smoochum","elekid","magby","miltank","blissey","raikou","entei","suicune","larvitar","pupitar","tyranitar","lugia","ho-oh","celebi","treecko","grovyle","sceptile","torchic","combusken","blaziken","mudkip","marshtomp","swampert","poochyena","mightyena","zigzagoon","linoone","wurmple","silcoon","beautifly","cascoon","dustox","lotad","lombre","ludicolo","seedot","nuzleaf","shiftry","taillow","swellow","wingull","pelipper","ralts","kirlia","gardevoir","surskit","masquerain","shroomish","breloom","slakoth","vigoroth","slaking","nincada","ninjask","shedinja","whismur","loudred","exploud","makuhita","hariyama","azurill","nosepass","skitty","delcatty","sableye","mawile","aron","lairon","aggron","meditite","medicham","electrike","manectric","plusle","minun","volbeat","illumise","roselia","gulpin","swalot","carvanha","sharpedo","wailmer","wailord","numel","camerupt","torkoal","spoink","grumpig","spinda","trapinch","vibrava","flygon","cacnea","cacturne","swablu","altaria","zangoose","seviper","lunatone","solrock","barboach","whiscash","corphish","crawdaunt","baltoy","claydol","lileep","cradily","anorith","armaldo","feebas","milotic","castform","kecleon","shuppet","banette","duskull","dusclops","tropius","chimecho","absol","wynaut","snorunt","glalie","spheal","sealeo","walrein","clamperl","huntail","gorebyss","relicanth","luvdisc","bagon","shelgon","salamence","beldum","metang","metagross","regirock","regice","registeel","latias","latios","kyogre","groudon","rayquaza","jirachi","deoxys-normal","turtwig","grotle","torterra","chimchar","monferno","infernape","piplup","prinplup","empoleon","starly","staravia","staraptor","bidoof","bibarel","kricketot","kricketune","shinx","luxio","luxray","budew","roserade","cranidos","rampardos","shieldon","bastiodon","burmy","wormadam-plant","mothim","combee","vespiquen","pachirisu","buizel","floatzel","cherubi","cherrim","shellos","gastrodon","ambipom","drifloon","drifblim","buneary","lopunny","mismagius","honchkrow","glameow","purugly","chingling","stunky","skuntank","bronzor","bronzong","bonsly","mime-jr","happiny","chatot","spiritomb","gible","gabite","garchomp","munchlax","riolu","lucario","hippopotas","hippowdon","skorupi","drapion","croagunk","toxicroak","carnivine","finneon","lumineon","mantyke","snover","abomasnow","weavile","magnezone","lickilicky","rhyperior","tangrowth","electivire","magmortar","togekiss","yanmega","leafeon","glaceon","gliscor","mamoswine","porygon-z","gallade","probopass","dusknoir","froslass","rotom","uxie","mesprit","azelf","dialga","palkia","heatran","regigigas","giratina-altered","cresselia","phione","manaphy","darkrai","shaymin-land","arceus","victini","snivy","servine","serperior","tepig","pignite","emboar","oshawott","dewott","samurott","patrat","watchog","lillipup","herdier","stoutland","purrloin","liepard","pansage","simisage","pansear","simisear","panpour","simipour","munna","musharna","pidove","tranquill","unfezant","blitzle","zebstrika","roggenrola","boldore","gigalith","woobat","swoobat","drilbur","excadrill","audino","timburr","gurdurr","conkeldurr","tympole","palpitoad","seismitoad","throh","sawk","sewaddle","swadloon","leavanny","venipede","whirlipede","scolipede","cottonee","whimsicott","petilil","lilligant","basculin-red-striped","sandile","krokorok","krookodile","darumaka","darmanitan-standard","maractus","dwebble","crustle","scraggy","scrafty","sigilyph","yamask","cofagrigus","tirtouga","carracosta","archen","archeops","trubbish","garbodor","zorua","zoroark","minccino","cinccino","gothita","gothorita","gothitelle","solosis","duosion","reuniclus","ducklett","swanna","vanillite","vanillish","vanilluxe","deerling","sawsbuck","emolga","karrablast","escavalier","foongus","amoonguss","frillish","jellicent","alomomola","joltik","galvantula","ferroseed","ferrothorn","klink","klang","klinklang","tynamo","eelektrik","eelektross","elgyem","beheeyem","litwick","lampent","chandelure","axew","fraxure","haxorus","cubchoo","beartic","cryogonal","shelmet","accelgor","stunfisk","mienfoo","mienshao","druddigon","golett","golurk","pawniard","bisharp","bouffalant","rufflet","braviary","vullaby","mandibuzz","heatmor","durant","deino","zweilous","hydreigon","larvesta","volcarona","cobalion","terrakion","virizion","tornadus-incarnate","thundurus-incarnate","reshiram","zekrom","landorus-incarnate","kyurem","keldeo-ordinary","meloetta-aria","genesect","chespin","quilladin","chesnaught","fennekin","braixen","delphox","froakie","frogadier","greninja","bunnelby","diggersby","fletchling","fletchinder","talonflame","scatterbug","spewpa","vivillon","litleo","pyroar","flabebe","floette","florges","skiddo","gogoat","pancham","pangoro","furfrou","espurr","meowstic-male","honedge","doublade","aegislash-shield","spritzee","aromatisse","swirlix","slurpuff","inkay","malamar","binacle","barbaracle","skrelp","dragalge","clauncher","clawitzer","helioptile","heliolisk","tyrunt","tyrantrum","amaura","aurorus","sylveon","hawlucha","dedenne","carbink","goomy","sliggoo","goodra","klefki","phantump","trevenant","pumpkaboo-average","gourgeist-average","bergmite","avalugg","noibat","noivern","xerneas","yveltal","zygarde-50","diancie","hoopa","volcanion","rowlet","dartrix","decidueye","litten","torracat","incineroar","popplio","brionne","primarina","pikipek","trumbeak","toucannon","yungoos","gumshoos","grubbin","charjabug","vikavolt","crabrawler","crabominable","oricorio-baile","cutiefly","ribombee","rockruff","lycanroc-midday","wishiwashi-solo","mareanie","toxapex","mudbray","mudsdale","dewpider","araquanid","fomantis","lurantis","morelull","shiinotic","salandit","salazzle","stufful","bewear","bounsweet","steenee","tsareena","comfey","oranguru","passimian","wimpod","golisopod","sandygast","palossand","pyukumuku","type-null","silvally","minior-red-meteor","komala","turtonator","togedemaru","mimikyu-disguised","bruxish","drampa","dhelmise","jangmo-o","hakamo-o","kommo-o","tapu-koko","tapu-lele","tapu-bulu","tapu-fini","cosmog","cosmoem","solgaleo","lunala","nihilego","buzzwole","pheromosa","xurkitree","celesteela","kartana","guzzlord","necrozma","magearna","marshadow","poipole","naganadel","stakataka","blacephalon","zeraora","meltan","melmetal","grookey","thwackey","rillaboom","scorbunny","raboot","cinderace","sobble","drizzile","inteleon","skwovet","greedent","rookidee","corvisquire","corviknight","blipbug","dottler","orbeetle","nickit","thievul","gossifleur","eldegoss","wooloo","dubwool","chewtle","drednaw","yamper","boltund","rolycoly","carkol","coalossal","applin","flapple","appletun","silicobra","sandaconda","cramorant","arrokuda","barraskewda","toxel","toxtricity-amped","sizzlipede","centiskorch","clobbopus","grapploct","sinistea","polteageist","hatenna","hattrem","hatterene","impidimp","morgrem","grimmsnarl","obstagoon","perrserker","cursola","sirfetchd","mr-rime","runerigus","milcery","alcremie","falinks","pincurchin","snom","frosmoth","stonjourner","eiscue-ice","indeedee-male","morpeko-full-belly","cufant","copperajah","dracozolt","arctozolt","dracovish","arctovish","duraludon","dreepy","drakloak","dragapult","zacian","zamazenta","eternatus","kubfu","urshifu-single-strike","zarude","regieleki","regidrago","glastrier","spectrier","calyrex","wyrdeer","kleavor","ursaluna","basculegion-male","sneasler","overqwil","enamorus-incarnate","sprigatito","floragato","meowscarada","fuecoco","crocalor","skeledirge","quaxly","quaxwell","quaquaval","lechonk","oinkologne","tarountula","spidops","nymble","lokix","pawmi","pawmo","pawmot","tandemaus","maushold","fidough","dachsbun","smoliv","dolliv","arboliva","squawkabilly","nacli","naclstack","garganacl","charcadet","armarouge","ceruledge","tadbulb","bellibolt","wattrel","kilowattrel","maschiff","mabosstiff","shroodle","grafaiai","bramblin","brambleghast","toedscool","toedscruel","klawf","capsakid","scovillain","rellor","rabsca","flittle","espathra","tinkatink","tinkatuff","tinkaton","wiglett","wugtrio","bombirdier","finizen","palafin","varoom","revavroom","cyclizar","orthworm","glimmet","glimmora","greavard","houndstone","flamigo","cetoddle","cetitan","veluza","dondozo","tatsugiri","annihilape","clodsire","farigiraf","dudunsparce","kingambit","great-tusk","scream-tail","brute-bonnet","flutter-mane","slither-wing","sandy-shocks","iron-treads","iron-bundle","iron-hands","iron-jugulis","iron-moth","iron-thorns","frigibax","arctibax","baxcalibur","gimmighoul","gholdengo","wo-chien","chien-pao","ting-lu","chi-yu","roaring-moon","iron-valiant","koraidon","miraidon"
    };


}
