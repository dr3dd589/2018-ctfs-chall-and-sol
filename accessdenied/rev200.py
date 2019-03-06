a = "Welcome guys, this is INSTROU 2k18. Thanks for Coming. Hope you w"
flag = ""
count =0
for j in range(25):

    for i in range(256):
        if ord(a[j]) == (count + 134514776)^i:
            count +=1
            flag += chr(i)
    print(flag,j)
    