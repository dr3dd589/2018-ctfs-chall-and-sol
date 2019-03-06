# a = """fa31c08ed88ec08ee08ed08ee831dbbc007cbeb17ce88d0
# 0bfc a7c30 e 4cd
# 1  6 aab40 ecd1 081
# ffe37c7   5 f  1b 00d
# cd10b00  acd1
# 0b bf f7e88 1ffe
# cb7 5f a3 1ff31f
# 6 0  3318 9 f8b1d
#  3  f6f197c1ef0 80   3b
#  50 07c8 1e6ff0089c 7 8a01 
# 8a20880 088214 781f  f 0001 
# 75 db31ff31 f64 781f f170 174
# 2  2 5781     e7ff0 0 0 3  3181 
#  e6ff00 8a01 8 a20 8  8008821 0
#   0e030e489c78a 095f3 08de 
#  2 7 ce bd76   6a1  e3  7c663d
# ef    beadde7 44ab e c57ce8020 
#   0eb feb40   eac84c 07404 cd 1 
#  0e  bf5c3 2876657273696f6e2 0 32  
#  290d   0  a666c61673  a200 06e  6f7 0 
#   6 500 41 4f54 577b6e6f 745f746 86  55f 
#    6  66c616  75f28796 574 2 9  5f7db 7157 
#   40   30 67 1 3b 02e af2a1982f 8faef8  5107 
#  c67  33e ca 540760  3d9 4 c 55a 84d de5abc551
# ee09d 93e0a2 6  32  2640e5   1  850 e50890c0  f 
#   9  118 e7f 4c756954e4 85 69 56716644 d  b  
#  19f6e84954701b8  d f97 a f15 3e450c5 55a3 a 
#  6ab0 6 667d 234 39 e8f1 7 3 de 1b 46 84c b3b4 
# 013 a8e7332cc  b 1  14a490 0f 260 ab7817b4ed d4 
#  807e4a163098d1c  b82 76a  dc 12 e3 a1c 7 c1b f198 
# e2d f7f9ae8ec 8 71 d7aa7 1 8  6 60 c2af 6f36fabc  e 
#  5 7 5e6c fadc429a4f  d1a208c b dc8   379f 8 560  399a 
#  f0 b5  45f  599 5342  53   04ec d 3 f10 bcc4 1f5d a  1f 
# bdd0 67 ffac6 1 064544 a38   50 a1c ff dcaf 3 ef60 71771 
#  a2 7ae79b363    eaf3f bf5190c63 5 2  d cb 4  9 68   5be08d  
# a64a2c 6 671ae5 4004cc 7127 b0 6c88e691 1  55   8d3efdd47459c 
# 9 403c 574a6 a9 a fb2c068 db 336  1 56c685f73 2 b64 f0e65 455aa"""
# b = a.replace("\n" , "").replace(" ", "")
# f = open('hex.txt','w')
# f.write(b)
# f.close()

f = open('binary.txt','r')
bin = f.read().strip()
hexa = str(int(bin,16))

f = open('pcap.txt','w')
f.write(hexa)