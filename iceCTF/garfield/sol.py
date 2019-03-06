a = "IceCTF{I_DONT_ZXHNLOERW_RBKXB_MUGJTYL}"
f= ""
for i in range(50):
    for j in range(14,38):
    	if a[j] != "_":
			f += chr(ord(a[j])+i)%256
		

    print(f)    
