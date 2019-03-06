a = """
push     rax ;; 10
push     rcx ;; 20
pop      rax ;; 20
pop      rcx ;; 10  swap value of rax to rcx vice-versa..!

xor      rax,rcx ;; rax = 20^10 = 30
xor      rcx,rax ;; rcx = 10^30 = 20
xor      rax,rcx ;; rax = 30^20 = 10 swap value of rax to rcx vice-versa..!

add      rax,rcx ;; rax = 30
sub      rcx,rax ;; rcx = -10
add      rax,rcx ;; rax = 20
neg      rcx ;; rcx = 10 2's compliment (mult operend by -1)
xchg     rax,rcx ;; swap value, rax = 10, rcx = 20

"""

its swaping the values of rax and rcx 3 times with 3 differ ways :-
1 = by push and pop
2 = by XORing values
3 = add sub and then add and 2's compliment of rcx 

result values are same !!