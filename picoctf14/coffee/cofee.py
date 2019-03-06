import base64

s1 = "eux_Z]\\ayiqlog`s^hvnmwr[cpftbkjd"
s2 = "Zf91XhR7fa=ZVH2H=QlbvdHJx5omN2xc"
b1 = bytearray(s1)
b2 = bytearray(s2)
base1 = ""
for i in range(len(b2)):
    base1 += chr(b2[b1[i]-90])

print(base64.b64decode(base1))