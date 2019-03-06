bytes = ['0x32','0x23','0x0b','0xaf','0x00','0x1e','0x2e','0xbb']
flag = 'picoCTF{'
l = []

for i in range(0,8):
    l.append(ord(flag[i]) ^ int(bytes[i], 16))

s = '0x'
for e in l[::-1]:
    sn = str(hex(e)).replace('0x', '')
    if len(sn) < 2:
        s += '0'
    s += sn

print(s)
print(str(int(s,16)))