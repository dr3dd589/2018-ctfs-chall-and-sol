a,b=[4581059342787195479870348172120710173536L, 17357230733436291625154131493550085825122L, 9101568067640148358310535607498477276863L, 3187202316512612787802175632578489715629L, 12643255195953029337343195373892861454021L, 3194241467435161486929011954580148119790L, 16318810187226159546011700470628403707011L, 11961372122720592863102196987377956196902L, 15929179955022225032998438394279862649915L, 16786462532033381158378182848784355463875L, 11449955567104405981388805663912693976074L, 19971476663548833874137910512080039752446L, 8262042535524634147026962333234484135896L, 16255115915309570940277944822315754602752L, 11519686390445519452776866405999126500405L, 12198871836911312980223710137857966127044L, 16133466827381000565436554063137350638393L, 16233789843823693644605958574254402886490L, 17567299901509557331436441240014465959654L, 6094258077473528188540248375619449007837L, 12307244798396463278199347699731025164505L, 2434001688250716351614284303084916397609L, 8155071454027675063405608749777202741824L, 16938140539940825120647932754276539433343L, 9658039491505597542693661066514649617029L, 8426466471647296449312682530243178893249L, 11116731227249602947678821864567302475914L, 1805779771103945189326221379189301341832L, 20479644585767868996588538469340890131727L, 18052282565693588324807260005212428917852L, 4063493674726094429149491028972459134776L, 7444589744359354165579590864232004088794L, 19077835069162302329636018508441182050378L, 10081787449979920021791939859372781453547L, 14957739550622586116965652310652221162892L, 18145321722471897838241794947928088022596L, 6823392999123856393603739859595902880844L, 9862015954259860522672751074278790194517L, 11654438970101557204301298219543732100927L, 0],[20934920529022408471321655218479073766163L, 9805951913859282705008964566957301928813L, 7643240084344105369831226178443604977860L, 21610358791967779297656064276475240675532L, 1213059664101738609381676831047726558428L, 20238763361170976559018162614881885610871L, 401805174121661756789380293043785849528L, 17212831329233160576193943756749238531924L, 18742702074464226382153462229400180075194L, 8546659528681066278743178694299888295718L, 6959970801697685451896690785915464758444L, 20739758002719227539017495373803163937736L, 3306689006456927264655072129066980578629L, 12980317689025634922514386374934534360990L, 13318692247541912406013974367297676310504L, 15123210060535148448226917300602140521221L, 19292305369998653359155068968768641463140L, 17808373695268394811884701358658797935566L, 10379507739514904609200556730540661867883L, 14000812598019882794569285655705178412983L, 3144673871727182440860695918900379572154L, 9828524009954118875598280154003587162080L, 18194412189509984738038631586712766231580L, 4572630615452946207632350406246732034392L, 7086654132253872038637643785431936639914L, 21364453794723710119109916971994535010589L, 543692330858722108919643115327830540412L, 9261143179983961877665956584377377031765L, 5860015891831799455087067300508784545654L, 20954294673893968374869608307087723399126L, 5642043916034625843705165409949630256148L, 14021079400004196254977960898601080403837L, 4215533033610582377146899072509009482806L, 7378669219628873988459976335266351997350L, 14464814403873712641343013429064567685236L, 255925378604926790719254239310768694064L, 18722689072222487473821967502322953208040L, 4016334696325688791136612368984737211375L, 19808899534602891981109157115899411353860L, 1]
from sage.all import *
import time

COUNT = len(a)

def fract_of_long(l): return fractions.Fraction(l, 1)

big = fract_of_long(2**128)
small = fractions.Fraction(1, 2**6)

rows = []
rows.append(map(fract_of_long, a) + [big, fract_of_long(0)])
rows.append(map(fract_of_long, b) + [fract_of_long(0), small])
for i in xrange(COUNT):
    new_row = [fract_of_long(0)] * (COUNT + 2)
    new_row[i] = fract_of_long(p)
    rows.append(new_row)

M = matrix(rows)
print "[+] Calculated matrix, running LLL"

# I needed to pass in the arguments, otherwise I got a floating-point exception. Not sure why. I think this makes it use "exact" rationals.
N = M.LLL(fp="rr", algorithm="fpLLL:proved")

print "[+] Looking for row with 2^{128} or -2^{128} in the second-last column..."
for row in N:
    if row[-2] == 2**128:
        print "[+] Found the key!"
        break
    elif row[-2] == -1*(2**128):
        print "[+] Found the key!"
        row = [-1*x for x in row]
        break

key = row[:-2]

# Assert that all entries of the key are only 128 bits long
for c in key:
    assert c < 2 ** 128

from hashlib import sha256
from Crypto.Cipher import AES
from binascii import unhexlify 

actual_key = sha256(" ".join(map(str, key))).hexdigest()
print "[+] Key is %s" % actual_key
enc_flag = unhexlify("aef8c15e422dfb8443fc94aa9b5234383d8ee523d6da9c4875ccf0d2cf24b1c3fa234e90b9f9757862d242063dbd694806bc54582deddbcbcc")

cipher = AES.new(unhexlify(actual_key), AES.MODE_CFB, "\x00" * 16)
print cipher.decrypt(enc_flag)