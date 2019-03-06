a = "bd"
b = "af"

c = 0

for i in range(len(a)):
    c = c | (ord(a[i])^ord(b[i]))

print(c)