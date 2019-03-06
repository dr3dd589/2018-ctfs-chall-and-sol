from Crypto.Util.number import *
# from secret import flag
# flag = "ctf{aaaaa}"

def egcd(a, b):
    x,y, u,v = 0,1, 1,0
    while a != 0:
        q, r = b//a, b%a
        m, n = x-u*q, y-v*q
        b,a, x,y, u,v = a,r, u,v, m,n
        gcd = b
    return gcd,x,y



obj1 = open("ciphertext.txt",'r')
a = obj1.read().split(', ')
print(a[3][1:-1])
b = bytes_to_long(a[3][1:-2].decode('hex'))
print(b)

modulus_list = [143786356117385195355522728814418684024129402954309769186869633376407480449846714776247533950484109173163811708549269029920405450237443197994941951104068001708682945191370596050916441792714228818475059839352105948003874426539429621408867171203559281132589926504992702401428910240117807627890055235377744541913L,
 73988726804584255779346831019194873108586184186524793132656027600961771331094234332693404730437468912329694216269372797532334390363774803642809945268154324370355113538927414351037561899998734391507272602074924837440885467211134022878597523920836541794820777951492188067045604789153534513271406458984968338509L,
 95666403279611361071535593067846981517930129087906362381453835849857496766736720885263927273295086034390557353492037703154353541274448884795437287235560639118986397838850340017834752502157881329960725771502503917735194236743345777337851076649842634506339513864285786698870866229339372558162315435127197444193L, 
 119235191922699211973494433973985286182951917872084464216722572875998345005104112625024274855529546680909781406076412741844254205002739352725207590519921992295941563460138887173402493503653397592300336588721082590464192875253265214253650991510709511154297580284525736720396804660126786258245028204861220690641L]

e = [114194L, 130478L, 122694L, 79874L]

c = [8661867050208110643757908040075196936181124993917228085216299563917450514764563590396680774370906306882180627593269773663352388253117655413951657650565125465743771626091302580675541987413249632936402786544772020057590806479458664752427437666215999241005833361677816125382855313168838366431299183383419115091,72885831082995053717653826762606599007985698622245467202429931193527074667117422141200003128918658400187421332081832364273088091280520575315446828764869555057306301659305115975068732646131703543309714074338341282715866487429017526124126056297942728010685036376005450614886362703823475714737552526113281790223,71544029597480420985139145642177918478733051860337181268238882268985401844920710608350523926791765565911417634566943571537809092987829464433679415044226818943967848273337080694917434412050190528970258645958147955919875039197175732141170582743104536254379645750280432509735853662568063218088041634451026303107,24623221917112130670877693423741880852921743922141497477624376057920344732066221115158692480634921187836525233985804702405086867358286544983169645790292654066940067480829558872543591954859695960050224657860754401958566313144265800481057112867532658914326318392524897058313159117472796992234466996749983594573]
# message = bytes_to_long(flag)
# ciphertext = [pow(message, e[i], modulus_list[i]) for i in range(4)]
# ciphertext = [long_to_bytes(ciphertext[i]) for i in range(4)]
# ciphertext = [ciphertext[i].encode("hex") for i in range(4)]

# for c,d in zip(modulus_list,e):
#     print(egcd(c,d))
