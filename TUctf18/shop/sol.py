import string
a = "jmt_j]tm`q`t_j]mpjtf^"
f5=""
# i = 0 
for i in range(len(a)):
    for j in range(500):
        d = j + (i+10)%21
        if d == ord(a[i]):
            f5 += chr(d+5)
            i +=1
            break

print(f5[::-1],len(f5),len(a))