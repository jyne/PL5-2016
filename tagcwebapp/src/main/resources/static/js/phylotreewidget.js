var width = 800, height = 600, color_scheme = d3.scale.category10();
var tree = d3.layout.phylotree("body").size([ height, width ]).separation(
		function(a, b) {
			return 0;
		})
var container_id = '#tree_container';
// var test_string =
// "(((EELA:0.150276,CONGERA:0.213019):0.230956,(EELB:0.263487,CONGERB:0.202633):0.246917):0.094785,((CAVEFISH:0.451027,(GOLDFISH:0.340495,ZEBRAFISH:0.390163):0.220565):0.067778,((((((NSAM:0.008113,NARG:0.014065):0.052991,SPUN:0.061003,(SMIC:0.027806,SDIA:0.015298,SXAN:0.046873):0.046977):0.009822,(NAUR:0.081298,(SSPI:0.023876,STIE:0.013652):0.058179):0.091775):0.073346,(MVIO:0.012271,MBER:0.039798):0.178835):0.147992,((BFNKILLIFISH:0.317455,(ONIL:0.029217,XCAU:0.084388):0.201166):0.055908,THORNYHEAD:0.252481):0.061905):0.157214,LAMPFISH:0.717196,((SCABBARDA:0.189684,SCABBARDB:0.362015):0.282263,((VIPERFISH:0.318217,BLACKDRAGON:0.109912):0.123642,LOOSEJAW:0.397100):0.287152):0.140663):0.206729):0.222485,(COELACANTH:0.558103,((CLAWEDFROG:0.441842,SALAMANDER:0.299607):0.135307,((CHAMELEON:0.771665,((PIGEON:0.150909,CHICKEN:0.172733):0.082163,ZEBRAFINCH:0.099172):0.272338):0.014055,((BOVINE:0.167569,DOLPHIN:0.157450):0.104783,ELEPHANT:0.166557):0.367205):0.050892):0.114731):0.295021)";
// var test_string = "(a : 0.1, (b : 0.11, (c : 0.12, d : 0.13) : 0.14) :
// 0.15)";
var test_string = "((TKK_03_0029:0.0288928292315,((TKK_04_0005:1.02364427249e-06,TKK_04_0020:1.02364427249e-06):0.00581151247664,(((TKK_04_0120:0.000617768881078,TKK_04_0085:0.000180134778091):0.00054475696593,((TKK_04_0080:0.000575695127794,TKK-01-0070:0.000118243641824):0.00032713084129,TKK-01-0080:0.000308003484252):1.02364427249e-06):0.00672115893225,TKK_04_0003:0.00521542687277):1.02364427249e-06):0.0260709063338):0.0370437731182,((((TKK_04_0149:0.013097329908,(((((TKK_03_0031:0.000236091684838,(TKK_02_0046:1.02364427249e-06,TKK_02_0001:6.16151563322e-05):0.000865666787068):0.00655539955136,((TKK_04_0001:0.00145127937565,((TKK_03_0160:0.000602813110214,(TKK-01-0047:0.000445352479323,TKK_03_0045:0.0003033316094):7.13372794932e-05):7.49729239659e-05,TKK_05SA_0019:0.00103855814156):0.000329983608084):0.000430462810598,TKK-01-0088:0.00129398166876):0.00563211588972):0.00103680693846,((((TKK_04_0126:0.00242155805407,((TKK_03_0114:0.00159571254519,TKK-01-0021:0.000851786187975):0.000126444901301,(TKK_03_0058:0.00132509593445,TKK_04_0098:0.00229918412402):7.31366400767e-05):0.000320316360982):0.00448371900482,(((TKK-01-0079:0.000963441544205,TKK_04_0089:0.000851794046665):0.00213964103493,((TKK-01-0046:0.000492392858265,TKK-01-0039:0.00103405029737):0.00196959474346,(TKK-01-0002:0.000964057550824,TKK_05SA_0052:0.00201785075078):0.00075122338497):1.02364427249e-06):0.000256753482748,(((((TKK_03_0043:0.000669325677357,(TKK-01-0024:0.00086443943976,TKK_03_0075:0.000540628318306):6.52466115241e-05):0.000459169655098,((TKK_04_0014:0.000606039871562,(TKK-01-0093:0.00095122368461,TKK_04_0033:0.000601657106372):1.02364427249e-06):1.02364427249e-06,TKK_03_0078:0.00102731869325):8.48839173507e-05):1.02364427249e-06,TKK_03_0149:0.000896690468416):0.000188217656323,((((TKK-01-0043:0.000848233786794,TKK_04_0018:0.000727929086983):6.4384744609e-05,(TKK_03_0154:0.000419281634553,TKK_03_0090:0.00111129060981):1.02364427249e-06):0.000383247430056,((TKK_03_0100:0.000509354010606,TKK_05SA_0046:0.00045652912426):6.93510388015e-05,TKK_03_0065:0.000392763091437):0.000725950834667):0.000127150725096,((TKK_03_0027:0.000673905083492,((TKK_04_0006:0.00024194762838,TKK_04_0084:0.000659554398528):0.00125582043541,(TKK_03_0028:0.000893449339625,((TKK-01-0078:0.0010699514337,TKK_03_0112:0.0002413769036):0.000359072792181,TKK-01-0067:0.000630458006773):7.9980543681e-05):1.02364427249e-06):0.000202753907035):6.2872449772e-05,(TKK_03_0111:0.00108303005624,(TKK-01-0082:0.000385745753967,(TKK_03_0039:0.000472317310167,TKK-01-0003:0.000463571066822):0.000143636360596):0.000614106934662):1.02364427249e-06):1.02364427249e-06):6.33936720679e-05):6.27288583862e-05,TKK-01-0008:0.00116037633028):0.00115939468408):0.00390967525564):0.00011441874226,(TKK-01-0027:0.00144248722629,(TKK_02_0002:0.00226903606982,((TKK_02_0080:0.000633231175696,TKK-01-0013:0.000863647261816):0.000964042563574,(TKK_04_0086:0.000410179438844,TKK-01-0065:0.000843126437351):0.000810839676819):0.000318221886966):0.000134916877272):0.00590068890578):0.000898925407721,(TKK-01-0092:0.00536421346681,(TKK_03_0025:0.00154239647113,TKK_03_0040:0.00187629685391):0.00327754310486):0.00216989491276):0.000149534586438):0.000221425009997,((TKK_03_0118:0.0014947498878,TKK_03_0109:0.00143187909509):0.00511138660851,(TKK_03_0047:0.000643613861509,((TKK_03_0083:0.00100922457111,TKK_03_0156:0.000602425110662):6.65973702316e-05,TKK_03_0072:0.000602153049997):0.000124192497812):0.00549924906673):0.000806514087438):0.000228593354449,(((TKK_04_0159:0.000439632940253,(TKK-01-0056:6.27812351747e-05,TKK-01-0055:1.02364427249e-06):0.000888179505667):0.00176468897573,(TKK-01-0005:0.000297876000114,TKK_02_0010:0.000429742910323):0.0019009191052):0.00575758261202,((TKK_03_0034:0.00103177872599,TKK-01-0094:0.00107627398732):5.32105401495e-05,(TKK_04_0017:0.00109553990965,((TKK_04_0136:0.000727900342839,TKK_04_0134:0.000630500342327):0.000462879335042,TKK_04_0054:0.000466251629696):0.000568417436644):0.000648116809953):0.00692699775507):0.000299339266407):0.00795850941627):0.00740372312113,((TKK-01-0062:0.000177959794119,(TKK_02_0055:0.000250851399176,(TKK_04_0114:0.000354591699893,(TKK_04_0107:0.000228534843501,TKK_04_0131:0.00013620736521):0.00012175511124):0.000193298933559):0.000190344146918):0.000577778469069,(TKK_04_0019:0.000425456172861,TKK_04_0023:0.000880702821272):0.000825353618051):0.0193869014713):0.0271255850068,(((TKK_02_0062:0.000663649943556,((TKK_05SA_0050:0.000528965991852,TKK_04_0139:0.000525414184784):0.000163518934076,TKK-01-0018:0.000178738466545):6.246888767e-05):0.00213535492084,TKK_03_0037:0.00162185882924):0.00954440178096,(TKK_04_0148:0.000597806407807,TKK_03_0033:0.000540274099427):0.0111437384114):0.0321207088651):0.00475539724005,(((((((TKK_03_0042:0.00237566596981,(TKK_04_0002:0.00048843264408,((TKK-01-0029:0.00041521455967,(TKK-01-0015:0.000306693727478,TKK_05MA_2015:0.000536293782443):6.1135057145e-05):1.02364427249e-06,(TKK_05SA_0020:0.000589940318399,(TKK_05SA_0054:0.000573153873602,TKK_04_0125:0.00041302849612):0.000126569310724):6.31324286081e-05):0.000191359967969):0.00124756870851):0.00910844199916,(TKK_03_0021:0.00668649749532,TKK_03_0059:0.00544976128981):0.00500362446563):0.00639695369142,((TKK_04_0031:0.0096179464822,((TKK_02_0068:0.000692198455094,TKK_05SA_0044:0.000631025187403):1.02364427249e-06,(TKK_05SA_0041:0.000228092216984,(TKK-01-0026:0.000177213531178,TKK_02_0018:0.000417779641405):0.000158153839923):6.86967875588e-05):0.00997098224838):0.00477110071853,TKK-01-0058:0.0131570621346):0.000274040362622):0.00252958236493,(TKK-01-0066:5.8090538616e-05,reference:1.02364427249e-06):0.0177633481009):0.00529228956587,TKK_03_0044:0.0221370214954):0.0055023228647,(((((((TKK-01-0030:0.000300022695778,TKK_05MA_0009:6.80754893744e-05):0.000462009131545,(TKK-01-0032:0.000487476420753,TKK-01-0009:0.00066883516453):0.000296326960706):0.00443338601362,TKK_03_0098:0.00327023179078):5.76748716476e-05,(((TKK_04_0067:0.000297415494175,TKK_02_0025:0.000149680083055):0.000153733536427,TKK-01-0022:1.02364427249e-06):0.00362124563841,TKK_03_0015:0.00294075496086):0.000236019076902):0.00505544746245,((((((TKK_02_0067:6.01389625542e-05,(TKK_05SA_0016:0.000118913353624,(TKK_04_0008:1.02364427249e-06,TKK_04_0042:1.02364427249e-06):1.02364427249e-06):6.17499558441e-05):0.000123378357061,((TKK-01-0064:0.000603909888916,((TKK_02_0066:1.02364427249e-06,(TKK_05MA_0004:0.000280192833702,TKK_02_0074:0.000181577034437):1.02364427249e-06):9.52157712189e-05,TKK-01-0042:0.000120396568354):0.000389043334241):6.41660806432e-05,((TKK_04_0046:6.00049775954e-05,TKK_04_0036:5.99415452629e-05):0.000119569141943,((TKK_04_0045:0.000301415773647,TKK-01-0020:0.000180784081524):1.02364427249e-06,TKK-01-0057:0.000123107578321):6.20088674015e-05):0.0003097748047):1.02364427249e-06):0.00036177594588,((TKK_04_0021:1.02364427249e-06,TKK_04_0074:0.00125720563058):0.000441650525828,TKK_05MA_0035:0.000422204966419):0.000349767165775):0.00310014968015,(TKK_04_0007:0.00162076911578,TKK-01-0061:0.000972271189115):0.00190950668454):0.000416853971191,TKK_03_0094:0.00342923902144):6.29361466131e-05,(TKK_03_0020:0.00192463267034,TKK_04_0030:0.00216055817744):0.000850957096245):0.00688430958744):0.00103511047132,(((((TKK_04_0037:0.00120016658823,TKK_02_0042:0.00017911589026):1.02364427249e-06,TKK_04_0024:0.000580690870506):0.000241846927347,TKK_05MA_0033:0.000475744140169):1.02364427249e-06,((TKK-01-0038:1.02364427249e-06,TKK-01-0041:1.02364427249e-06):0.000241179586836,TKK-01-0016:1.02364427249e-06):0.000181877073458):7.72080295129e-05,TKK_05MA_0040:0.0002379617387):0.0102711991225):0.0166744358308,((((TKK_04_0029:0.00359691950154,TKK-01-0089:0.00378476019413):1.02364427249e-06,(TKK_03_0105:0.00261575323205,(((TKK_04_0140:0.00219700827563,(TKK-01-0090:0.0017925005495,(((TKK_03_0035:0.00250176594239,TKK-01-0049:0.00260367505747):0.000739478390359,(TKK_04_0047:0.00266065044488,((TKK-01-0044:0.000120839505043,TKK-01-0019:0.000178586289629):0.0033879768051,TKK-01-0063:0.0027540296786):6.28340300216e-05):0.000187678614751):6.34698679594e-05,(((TKK-01-0083:0.00231125866486,(TKK_03_0036:0.00208135715053,TKK_04_0015:0.00183169090128):1.02364427249e-06):6.53551742198e-05,TKK_03_0092:0.00223450747012):0.000121046588806,(((TKK_03_0023:1.02364427249e-06,TKK_03_0024:1.02364427249e-06):0.000770868262697,TKK_05SA_0025:0.00136127996504):0.00217965546925,((TKK_05SA_0024:0.000486530854286,TKK-01-0007:5.93356388003e-05):0.00115450178717,(TKK_03_0064:0.00193665550874,TKK-01-0077:0.00166510909989):0.000353810884995):0.00043778980986):1.02364427249e-06):0.000246872162509):5.58877184858e-05):6.87870668177e-06):0.000311319839268,TKK_04_0094:0.00341286784302):0.000192049755315,((TKK_05SA_0010:0.00249649831451,(TKK-01-0060:0.00317885921604,(TKK-01-0010:0.00221379560827,TKK_04_0044:0.00209793826954):6.91185254034e-05):0.000138031929058):6.32653522131e-05,TKK_03_0102:0.00392691378238):0.000252310473853):1.02364427249e-06):6.48074327087e-05):0.00614678991733,TKK_03_0082:0.00928315264523):0.0119741951953,((((TKK-01-0091:0.00244161841688,(TKK-01-0081:0.00227315023586,((((((((TKK_04_0060:0.000656541640255,(TKK_05SA_0042:0.000158865047738,TKK_04_0051:1.02364427249e-06):0.000553942719037):6.56271291414e-05,((G51950:0.000231679565432,TKK_05SA_0048:0.000643320244581):0.000209157667555,(TKK-01-0073:0.000550527689565,(TKK_04_0099:0.000569959938831,((TKK_04_0013:0.000302402633921,(TKK_04_0078:5.96673487394e-05,TKK_04_0066:1.02364427249e-06):0.000237832591297):5.86771137546e-05,(TKK_04_0064:1.02364427249e-06,TKK_04_0083:0.000119221383312):0.000187277192559):1.02364427249e-06):0.000132258259626):0.000322949792832):1.02364427249e-06):0.000101870141338,(TKK_04_0158:0.0014605344685,TKK_04_0038:0.000844263862419):9.08238509836e-05):8.82216639785e-05,(TKK-01-0069:0.00037701749783,TKK_04_0070:0.000893899499185):0.000581681195358):0.000630470679154,((TKK-01-0074:0.000366701269536,(TKK_02_0023:0.000238302338586,(TKK-01-0048:0.000537582237049,(((TKK_02_0041:5.95348366895e-05,TKK_02_0006:0.000241822438972):5.94975533803e-05,((((TKK_05SA_0043:0.000246249860789,TKK_02_0048:5.97180229987e-05):8.16277777616e-05,TKK_02_0012:1.02364427249e-06):0.000326308703297,(TKK_04_0104:0.000178770592251,((TKK_02_0056:0.000476957183862,(TKK_02_0030:5.96098932385e-05,TKK_04_0132:0.000144954538662):0.000144435680633):0.00028832861914,((TKK_02_0040:0.000265327340339,TKK-01-0076:0.000178492965616):1.02364427249e-06,TKK_02_0008:0.000333047953051):5.83791936398e-05):0.000190713315434):1.02364427249e-06):0.000117794176587,(((TKK_02_0014:0.000121098107236,((TKK_02_0043:0.000298367685291,((TKK_02_0052:0.000180959967333,TKK-01-0040:0.000240321591939):5.96231950608e-05,(TKK_04_0122:5.95591634803e-05,((((((TKK_02_0016:6.00811553015e-05,TKK-01-0017:0.000489779190202):5.95812889836e-05,(TKK-01-0025:1.02364427249e-06,TKK_02_0058:0.000250836596347):8.11654393627e-05):1.02364427249e-06,TKK_02_0044:0.000178930235457):7.4737161872e-05,TKK_02_0060:0.00011933036543):1.02364427249e-06,TKK_02_0050:0.000178739671793):1.02364427249e-06,(TKK_02_0038:1.02364427249e-06,TKK-01-0004:0.00011911763479):5.95209298477e-05):1.02364427249e-06):1.02364427249e-06):1.02364427249e-06):1.02364427249e-06,((TKK_02_0005:1.02364427249e-06,TKK_02_0024:1.02364427249e-06):5.94271119001e-05,TKK_02_0031:0.000238869206714):5.93618201282e-05):1.02364427249e-06):1.02364427249e-06,((((TKK_02_0049:1.02364427249e-06,TKK_02_0053:1.02364427249e-06):0.000145125890654,TKK_04_0124:0.000297865565435):0.000505610706675,TKK_02_0051:5.97995569512e-05):1.02364427249e-06,(TKK_02_0021:0.000120120492784,G51951:1.02364427249e-06):5.9518620807e-05):1.02364427249e-06):1.02364427249e-06,((TKK_02_0007:0.000239110004158,((((((((TKK_04_0106:0.000502605459508,TKK_04_0108:0.000118057062868):1.02364427249e-06,TKK_04_0137:0.000302271690247):1.02364427249e-06,TKK_05MA_2005:0.000186057443477):9.786444974e-05,TKK_05MA_2008:0.00026424793881):0.000349241677783,TKK-01-0033:1.02364427249e-06):0.000172675579363,(TKK_04_0109:0.000499121647282,TKK_04_0123:1.02364427249e-06):1.02364427249e-06):0.000280377724739,(TKK_04_0105:0.000471956952251,(TKK_02_0028:6.77710509247e-05,TKK_04_0112:1.02364427249e-06):6.62086103485e-05):0.000133525679125):0.000223334823004,TKK_02_0004:7.18142359554e-05):0.000311355832775):6.18227725888e-05,(TKK_04_0117:0.000302721976347,TKK_04_0129:0.000178664602541):7.52099108655e-05):1.02364427249e-06):1.02364427249e-06):6.39258817441e-05):0.000319921887135,TKK_04_0059:0.00086991111406):6.67433801842e-05):6.69281924779e-05):3.99149451687e-05):1.07288656678e-06,((((TKK_04_0157:1.02364427249e-06,TKK_04_0145:7.46154595048e-05):1.02364427249e-06,TKK_04_0130:0.000318661020148):0.000563369427266,TKK_04_0081:1.02364427249e-06):0.00036845928541,(TKK_04_0097:0.00172837615184,(((TKK-01-0052:5.96372913574e-05,TKK_04_0043:0.000240895747905):0.000178727857878,(TKK_04_0095:0.000416923824189,(TKK_04_0082:0.000179111277207,(TKK_04_0141:0.000581499807266,TKK_02_0065:0.000239262650138):0.000193032979455):1.02364427249e-06):0.000118375514743):5.90926461843e-05,(TKK-01-0068:0.000828551699323,TKK_04_0096:0.000713271049219):9.75229337491e-05):1.02364427249e-06):1.02364427249e-06):0.000264000746211):0.00113815192792):7.6454768e-05,(TKK_04_0090:0.00119317770497,TKK-01-0012:0.00186666324501):6.45254173471e-05):0.000552159100372,((((TKK_04_0062:0.000179086758928,TKK_05MA_0051:0.000524448208208):0.000970338653198,(TKK_02_0073:1.02364427249e-06,TKK_02_0076:0.00018481218079):0.000661252423714):0.000410770423812,(TKK-01-0075:8.32436577386e-05,TKK-01-0071:0.000252555106793):0.000390325161754):0.000579390097886,(TKK-01-0006:0.000240694834692,(((((TKK_04_0118:0.000230810669873,TKK_05SA_0055:0.000119247251555):0.000230343474184,(TKK-01-0035:5.96852469464e-05,TKK-01-0001:0.000305183617588):0.000178959246643):0.000200402631918,(TKK_02_0020:0.000422040165576,TKK_04_0048:0.00126030557613):1.02364427249e-06):1.02364427249e-06,(TKK_05SA_0011:0.000718021570933,(TKK_04_0039:0.000483223276083,(TKK_02_0071:0.000241331061598,TKK_02_0047:0.000299167682217):0.000179151121366):1.02364427249e-06):0.000238146970962):6.30664411959e-05,TKK_04_0150:0.00119340139677):7.78542854329e-05):0.000233591719349):7.11846270671e-05):0.000931272430784,(TKK-01-0036:0.00141604679473,(G51952:1.02364427249e-06,TKK_03_0159:0.000535220014784):0.000534419358802):0.000267473405266):0.00146718263173):0.000358433877707):0.000391335308315,TKK-01-0084:0.00278092368596):0.00816937611344,(TKK-01-0037:0.0119576727651,TKK_03_0115:0.00961532506206):0.000257752480915):0.00699508880479,(((TKK_03_0089:0.00847557748064,TKK_04_0153:0.00942007124668):0.000171274534723,((TKK-01-0011:0.00275819383186,(TKK-01-0085:0.00108722082296,TKK_05SA_0014:0.000977361377711):0.00185542147409):0.00115835939449,TKK_02_0079:0.00443359981059):0.00313221907759):0.00134001253025,TKK_05MA_0037:0.0108258624737):0.00867264334773):0.00157551455082):0.0095949884488):0.00045835922407):0.00446577512442,(((TKK_03_0050:0.00222110178522,TKK-01-0087:0.00163676786765):0.0190907469032,((TKK_03_0026:0.0150514741978,(((TKK-01-0086:0.00676311386354,TKK_03_0103:0.00971184116706):0.000780052287123,(TKK-01-0053:0.00387320892653,TKK_03_0101:0.00462988566429):0.00370410703674):0.00149480884573,((TKK_04_0061:0.00115547996337,TKK-01-0014:0.00088139392198):6.11976115045e-05,(TKK_04_0075:0.000301971533093,TKK-01-0050:0.000299950757744):0.000244751244426):0.0103843383124):0.00716935525469):0.000163181874654,(TKK_03_0030:0.00455979412038,TKK_04_0155:0.00393101538984):0.0127836273944):0.00478421814829):0.00180229603589,((TKK_04_0068:0.021951377414,(TKK_03_0063:0.0108961431505,((TKK_04_0040:1.02364427249e-06,TKK_04_0069:1.02364427249e-06):0.000543639492789,TKK-01-0028:0.000183067173611):0.0105747982076):0.00488560701826):0.00021451065,(((((TKK_03_0150:0.000126804185375,TKK_03_0108:0.00018619996855):0.00316080048037,((((((TKK_04_0071:0.000883653128594,(TKK_04_0034:0.000300413288006,TKK-01-0031:0.000120956603686):1.02364427249e-06):0.000251626941656,(TKK_02_0036:0.000481376420808,((TKK_02_0027:0.000181024992381,TKK_02_0035:0.000120124477425):1.02364427249e-06,TKK_05SA_0018:0.000238290263175):0.000178486628037):1.02364427249e-06):5.88119576291e-05,TKK_04_0022:0.000787443429955):0.00339413209748,(TKK_04_0113:1.02364427249e-06,TKK_04_0103:0.000121785871079):0.00292070303777):1.02364427249e-06,TKK-01-0034:0.00252147641904):6.80374414501e-05,(TKK-01-0045:0.00264678365306,(TKK_05SA_0021:0.00251911215751,TKK-01-0054:0.00250761347908):1.02364427249e-06):0.000939491157106):6.86738569115e-05):0.000221441721622,TKK_03_0153:0.00401661918865):0.00561738697793,TKK_02_0037:0.00846874989786):0.000577245504158,(TKK_03_0099:0.00805736738391,TKK_03_0158:0.00855832063542):0.00015802991056):0.0120718676352):0.00628808229428):0.00781249907299):0.0130533437012):0.0370437731182);"


$(document).ready(function() {
	addCompareGenomeButtonBindings();
	set_default_tree_settings()
	drawTree();
});

function drawTree() {
	var svg = d3.select(container_id).append("svg").attr("width", width).attr(
			"height", height);
	tree(test_string).svg(svg).layout();
}

function addCompareGenomeButtonBindings() {

	$('#compGenomesButton').on('click', function(e) {
		var selectedNodeObjects = tree.get_selection();
		var names = [];
		selectedNodeObjects.forEach(function(d) {
			if (d.name) {
				names.push(d.name);
			}
		});
		console.log(names);
		getRibbonGraph(names);
	})
}

function getRibbonGraph(names) {
	var url = 'http://localhost:9998/';
	$.ajax({
		url : url + 'api/getribbongraph',
		dataType : 'JSON',
		type : 'GET',
		data : {
			'names' : JSON.stringify(names)
		}
	}).done(function(data) {
		drawRibbonGraph(data);
	});
}

function drawRibbonGraph(graph) {
	console.log("drawRibbonGraph function called with data:");
	console.log(graph);
}

function set_default_tree_settings() {
	tree.branch_length(null);
	tree.branch_name(null);
	tree.node_span('equal');
	tree.options({
		'draw-size-bubbles' : false
	}, false);
	// tree.radial (true);
	tree.style_nodes(node_colorizer);
	tree.style_edges(edge_colorizer);
}

function node_colorizer(element, data) {
	try {
		var count_class = 0;

		selection_set.forEach(function(d, i) {
			if (data[d]) {
				count_class++;
				element.style("fill", color_scheme(i),
						i == current_selection_id ? "important" : null);
			}
		});

		if (count_class > 1) {

		} else {
			if (count_class == 0) {
				element.style("fill", null);
			}
		}
	} catch (e) {

	}

}

function edge_colorizer(element, data) {
	try {
		var count_class = 0;

		selection_set.forEach(function(d, i) {
			if (data[d]) {
				count_class++;
				element.style("stroke", color_scheme(i),
						i == current_selection_id ? "important" : null);
			}
		});

		if (count_class > 1) {
			element.classed("branch-multiple", true);
		} else if (count_class == 0) {
			element.style("stroke", null).classed("branch-multiple", false);
		}
	} catch (e) {
	}

}
