def egcd(a, b):
    x,y, u,v = 0,1, 1,0
    while a != 0:
        q, r = b//a, b%a
        m, n = x-u*q, y-v*q
        b,a, x,y, u,v = a,r, u,v, m,n
        gcd = b
    return gcd, x, y

# def main():


p =  1039 
q = 1049 

e = 19
ct = [1088668, 857173, 665353, 350800, 205575, 646327, 716830, 857173, 646327, 1083334, 857173, 610750, 604320, 665353, 687595] 



n = p * q
print n


phi = (p - 1) * (q - 1)

# Compute modular inverse of e
gcd, a, b = egcd(e, phi)
d = a
d = d % phi
if (d < 0):
    d += phi

    # print( "d:  " + str(d) )
flag = ""

for c in ct:
    pt = int(pow(c, d, n))
    flag += chr(pt)
    # # pt = pow(ct, d, n)
    # print( "flag: " + chr(pt) )
print(flag)
# if __name__ == "__main__":
#     main()