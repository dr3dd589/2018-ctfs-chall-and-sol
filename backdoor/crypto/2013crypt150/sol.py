import _codecs
import time
f = open('enc.enc','r')
fy = f.read().strip()
# for j in range(32,128):
    # key = "qwer{0}".format(chr(j))
key = "qwertyuiopasdfghjklzxcvbnm"
fl = ""
for i in range(len(fy)):
    fl += chr(ord(key[i%len(key)])^ord(fy[i]))
print(fl)


# 1y0iopas!f"h\x2fklz=&"bn9