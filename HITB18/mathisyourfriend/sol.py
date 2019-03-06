a = [79,
8,
29,
58,
81,
21,
49,
123,
114,]

s=['?']*10
x = 0
while(x<10):
    x += 1
    print(x)
    for i in range(32,128):
        for j in range(3):
            for k in range(3):
                v = 0
                for l in range(3):
                        v = (i*a[3*j+l] + v)%127
                        if j==k:
                            if v==1:
                                s[3*l+k] = chr(i)
                                
                                
                        elif v==0:
                            s[3*l+k] = chr(i)
                            
                    
        
print(''.join(s))