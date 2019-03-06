

       







typedef signed char int8_t;
typedef short int int16_t;
typedef int int32_t;



__extension__
typedef long long int int64_t;




typedef unsigned char uint8_t;
typedef unsigned short int uint16_t;

typedef unsigned int uint32_t;





__extension__
typedef unsigned long long int uint64_t;






typedef signed char int_least8_t;
typedef short int int_least16_t;
typedef int int_least32_t;



__extension__
typedef long long int int_least64_t;



typedef unsigned char uint_least8_t;
typedef unsigned short int uint_least16_t;
typedef unsigned int uint_least32_t;



__extension__
typedef unsigned long long int uint_least64_t;






typedef signed char int_fast8_t;





typedef int int_fast16_t;
typedef int int_fast32_t;
__extension__
typedef long long int int_fast64_t;



typedef unsigned char uint_fast8_t;





typedef unsigned int uint_fast16_t;
typedef unsigned int uint_fast32_t;
__extension__
typedef unsigned long long int uint_fast64_t;
typedef int intptr_t;


typedef unsigned int uintptr_t;
__extension__
typedef long long int intmax_t;
__extension__
typedef unsigned long long int uintmax_t;







       










       










       











typedef unsigned char __u_char;
typedef unsigned short int __u_short;
typedef unsigned int __u_int;
typedef unsigned long int __u_long;


typedef signed char __int8_t;
typedef unsigned char __uint8_t;
typedef signed short int __int16_t;
typedef unsigned short int __uint16_t;
typedef signed int __int32_t;
typedef unsigned int __uint32_t;




__extension__ typedef signed long long int __int64_t;
__extension__ typedef unsigned long long int __uint64_t;







__extension__ typedef long long int __quad_t;
__extension__ typedef unsigned long long int __u_quad_t;


__extension__ typedef __u_quad_t __dev_t;
__extension__ typedef unsigned int __uid_t;
__extension__ typedef unsigned int __gid_t;
__extension__ typedef unsigned long int __ino_t;
__extension__ typedef __u_quad_t __ino64_t;
__extension__ typedef unsigned int __mode_t;
__extension__ typedef unsigned int __nlink_t;
__extension__ typedef long int __off_t;
__extension__ typedef __quad_t __off64_t;
__extension__ typedef int __pid_t;
__extension__ typedef struct { int __val[2]; } __fsid_t;
__extension__ typedef long int __clock_t;
__extension__ typedef unsigned long int __rlim_t;
__extension__ typedef __u_quad_t __rlim64_t;
__extension__ typedef unsigned int __id_t;
__extension__ typedef long int __time_t;
__extension__ typedef unsigned int __useconds_t;
__extension__ typedef long int __suseconds_t;

__extension__ typedef int __daddr_t;
__extension__ typedef long int __swblk_t;
__extension__ typedef int __key_t;


__extension__ typedef int __clockid_t;


__extension__ typedef void * __timer_t;


__extension__ typedef long int __blksize_t;




__extension__ typedef long int __blkcnt_t;
__extension__ typedef __quad_t __blkcnt64_t;


__extension__ typedef unsigned long int __fsblkcnt_t;
__extension__ typedef __u_quad_t __fsblkcnt64_t;


__extension__ typedef unsigned long int __fsfilcnt_t;
__extension__ typedef __u_quad_t __fsfilcnt64_t;

__extension__ typedef int __ssize_t;



typedef __off64_t __loff_t;
typedef __quad_t *__qaddr_t;
typedef char *__caddr_t;


__extension__ typedef int __intptr_t;


__extension__ typedef unsigned int __socklen_t;



typedef __u_char u_char;
typedef __u_short u_short;
typedef __u_int u_int;
typedef __u_long u_long;
typedef __quad_t quad_t;
typedef __u_quad_t u_quad_t;
typedef __fsid_t fsid_t;




typedef __loff_t loff_t;





typedef __ino64_t ino_t;




typedef __ino64_t ino64_t;




typedef __dev_t dev_t;




typedef __gid_t gid_t;




typedef __mode_t mode_t;




typedef __nlink_t nlink_t;




typedef __uid_t uid_t;







typedef __off64_t off_t;




typedef __off64_t off64_t;




typedef __pid_t pid_t;





typedef __id_t id_t;




typedef __ssize_t ssize_t;





typedef __daddr_t daddr_t;
typedef __caddr_t caddr_t;





typedef __key_t key_t;
       


typedef __clock_t clock_t;





typedef __time_t time_t;



typedef __clockid_t clockid_t;
typedef __timer_t timer_t;



typedef __useconds_t useconds_t;



typedef __suseconds_t suseconds_t;





typedef unsigned int size_t;



typedef unsigned long int ulong;
typedef unsigned short int ushort;
typedef unsigned int uint;
typedef unsigned int u_int8_t __attribute__ ((__mode__ (__QI__)));
typedef unsigned int u_int16_t __attribute__ ((__mode__ (__HI__)));
typedef unsigned int u_int32_t __attribute__ ((__mode__ (__SI__)));
typedef unsigned int u_int64_t __attribute__ ((__mode__ (__DI__)));

typedef int register_t __attribute__ ((__mode__ (__word__)));




typedef int __sig_atomic_t;




typedef struct
  {
    unsigned long int __val[(1024 / (8 * sizeof (unsigned long int)))];
  } __sigset_t;



typedef __sigset_t sigset_t;





       
struct timespec
  {
    __time_t tv_sec;
    long int tv_nsec;
  };

struct timeval
  {
    __time_t tv_sec;
    __suseconds_t tv_usec;
  };
typedef long int __fd_mask;
typedef struct
  {



    __fd_mask fds_bits[1024 / (8 * (int) sizeof (__fd_mask))];





  } fd_set;






typedef __fd_mask fd_mask;

extern int select (int __nfds, fd_set *__restrict __readfds,
     fd_set *__restrict __writefds,
     fd_set *__restrict __exceptfds,
     struct timeval *__restrict __timeout);
extern int pselect (int __nfds, fd_set *__restrict __readfds,
      fd_set *__restrict __writefds,
      fd_set *__restrict __exceptfds,
      const struct timespec *__restrict __timeout,
      const __sigset_t *__restrict __sigmask);





__extension__
extern unsigned int gnu_dev_major (unsigned long long int __dev)
     __attribute__ ((__nothrow__));
__extension__
extern unsigned int gnu_dev_minor (unsigned long long int __dev)
     __attribute__ ((__nothrow__));
__extension__
extern unsigned long long int gnu_dev_makedev (unsigned int __major,
            unsigned int __minor)
     __attribute__ ((__nothrow__));


__extension__ extern __inline __attribute__ ((__gnu_inline__)) unsigned int
__attribute__ ((__nothrow__)) gnu_dev_major (unsigned long long int __dev)
{
  return ((__dev >> 8) & 0xfff) | ((unsigned int) (__dev >> 32) & ~0xfff);
}

__extension__ extern __inline __attribute__ ((__gnu_inline__)) unsigned int
__attribute__ ((__nothrow__)) gnu_dev_minor (unsigned long long int __dev)
{
  return (__dev & 0xff) | ((unsigned int) (__dev >> 12) & ~0xff);
}

__extension__ extern __inline __attribute__ ((__gnu_inline__)) unsigned long long int
__attribute__ ((__nothrow__)) gnu_dev_makedev (unsigned int __major, unsigned int __minor)
{
  return ((__minor & 0xff) | ((__major & 0xfff) << 8)
   | (((unsigned long long int) (__minor & ~0xff)) << 12)
   | (((unsigned long long int) (__major & ~0xfff)) << 32));
}





typedef __blksize_t blksize_t;
typedef __blkcnt64_t blkcnt_t;



typedef __fsblkcnt64_t fsblkcnt_t;



typedef __fsfilcnt64_t fsfilcnt_t;





typedef __blkcnt64_t blkcnt64_t;
typedef __fsblkcnt64_t fsblkcnt64_t;
typedef __fsfilcnt64_t fsfilcnt64_t;





typedef unsigned long int pthread_t;


typedef union
{
  char __size[36];
  long int __align;
} pthread_attr_t;


typedef struct __pthread_internal_slist
{
  struct __pthread_internal_slist *__next;
} __pthread_slist_t;




typedef union
{
  struct __pthread_mutex_s
  {
    int __lock;
    unsigned int __count;
    int __owner;


    int __kind;
    unsigned int __nusers;
    __extension__ union
    {
      int __spins;
      __pthread_slist_t __list;
    };
  } __data;
  char __size[24];
  long int __align;
} pthread_mutex_t;

typedef union
{
  char __size[4];
  long int __align;
} pthread_mutexattr_t;




typedef union
{
  struct
  {
    int __lock;
    unsigned int __futex;
    __extension__ unsigned long long int __total_seq;
    __extension__ unsigned long long int __wakeup_seq;
    __extension__ unsigned long long int __woken_seq;
    void *__mutex;
    unsigned int __nwaiters;
    unsigned int __broadcast_seq;
  } __data;
  char __size[48];
  __extension__ long long int __align;
} pthread_cond_t;

typedef union
{
  char __size[4];
  long int __align;
} pthread_condattr_t;



typedef unsigned int pthread_key_t;



typedef int pthread_once_t;





typedef union
{
  struct
  {
    int __lock;
    unsigned int __nr_readers;
    unsigned int __readers_wakeup;
    unsigned int __writer_wakeup;
    unsigned int __nr_readers_queued;
    unsigned int __nr_writers_queued;


    unsigned char __flags;
    unsigned char __shared;
    unsigned char __pad1;
    unsigned char __pad2;
    int __writer;
  } __data;
  char __size[32];
  long int __align;
} pthread_rwlock_t;

typedef union
{
  char __size[8];
  long int __align;
} pthread_rwlockattr_t;





typedef volatile int pthread_spinlock_t;




typedef union
{
  char __size[20];
  long int __align;
} pthread_barrier_t;

typedef union
{
  char __size[4];
  int __align;
} pthread_barrierattr_t;









       






typedef int __gwchar_t;

typedef struct
  {
    long long int quot;
    long long int rem;
  } imaxdiv_t;





extern intmax_t imaxabs (intmax_t __n) __attribute__ ((__nothrow__)) __attribute__ ((__const__));


extern imaxdiv_t imaxdiv (intmax_t __numer, intmax_t __denom)
      __attribute__ ((__nothrow__)) __attribute__ ((__const__));


extern intmax_t strtoimax (__const char *__restrict __nptr,
      char **__restrict __endptr, int __base) __attribute__ ((__nothrow__));


extern uintmax_t strtoumax (__const char *__restrict __nptr,
       char ** __restrict __endptr, int __base) __attribute__ ((__nothrow__));


extern intmax_t wcstoimax (__const __gwchar_t *__restrict __nptr,
      __gwchar_t **__restrict __endptr, int __base)
     __attribute__ ((__nothrow__));


extern uintmax_t wcstoumax (__const __gwchar_t *__restrict __nptr,
       __gwchar_t ** __restrict __endptr, int __base)
     __attribute__ ((__nothrow__));
__extension__
extern long long int __strtoll_internal (__const char *__restrict __nptr,
      char **__restrict __endptr,
      int __base, int __group)
  __attribute__ ((__nothrow__)) __attribute__ ((__nonnull__ (1))) ;

extern __inline __attribute__ ((__gnu_inline__)) intmax_t
__attribute__ ((__nothrow__)) strtoimax (__const char *__restrict nptr, char **__restrict endptr, int base)

{
  return __strtoll_internal (nptr, endptr, base, 0);
}

__extension__
extern unsigned long long int __strtoull_internal (__const char *
         __restrict __nptr,
         char **
         __restrict __endptr,
         int __base,
         int __group)
  __attribute__ ((__nothrow__)) __attribute__ ((__nonnull__ (1))) ;

extern __inline __attribute__ ((__gnu_inline__)) uintmax_t
__attribute__ ((__nothrow__)) strtoumax (__const char *__restrict nptr, char **__restrict endptr, int base)

{
  return __strtoull_internal (nptr, endptr, base, 0);
}

__extension__
extern long long int __wcstoll_internal (__const __gwchar_t *
      __restrict __nptr,
      __gwchar_t **__restrict __endptr,
      int __base, int __group)
  __attribute__ ((__nothrow__)) __attribute__ ((__nonnull__ (1))) ;

extern __inline __attribute__ ((__gnu_inline__)) intmax_t
__attribute__ ((__nothrow__)) wcstoimax (__const __gwchar_t *__restrict nptr, __gwchar_t **__restrict endptr, int base)

{
  return __wcstoll_internal (nptr, endptr, base, 0);
}


__extension__
extern unsigned long long int __wcstoull_internal (__const __gwchar_t *
         __restrict __nptr,
         __gwchar_t **
         __restrict __endptr,
         int __base,
         int __group)
  __attribute__ ((__nothrow__)) __attribute__ ((__nonnull__ (1))) ;

extern __inline __attribute__ ((__gnu_inline__)) uintmax_t
__attribute__ ((__nothrow__)) wcstoumax (__const __gwchar_t *__restrict nptr, __gwchar_t **__restrict endptr, int base)

{
  return __wcstoull_internal (nptr, endptr, base, 0);
}





typedef signed char gl_int8_t;
typedef unsigned char gl_uint8_t;





typedef short int gl_int16_t;
typedef unsigned short int gl_uint16_t;





typedef int gl_int32_t;
typedef unsigned int gl_uint32_t;
typedef signed char gl_int_fast8_t;
typedef unsigned char gl_uint_fast8_t;







typedef long int gl_int_fast32_t;
typedef unsigned long int gl_uint_fast32_t;

typedef gl_int_fast32_t gl_int_fast16_t;
typedef gl_uint_fast32_t gl_uint_fast16_t;
typedef long int gl_intptr_t;
typedef unsigned long int gl_uintptr_t;
typedef int _verify_intmax_size[sizeof (intmax_t) == sizeof (uintmax_t)
                                ? 1 : -1];


typedef gl_uint32_t ucs4_t;






typedef int ptrdiff_t;
typedef int wchar_t;
typedef struct {
  long long __max_align_ll __attribute__((__aligned__(__alignof__(long long))));
  long double __max_align_ld __attribute__((__aligned__(__alignof__(long double))));
} max_align_t;
extern const gl_uint8_t *
       u8_check (const gl_uint8_t *s, size_t n)
       __attribute__ ((__pure__));



extern const gl_uint16_t *
       u16_check (const gl_uint16_t *s, size_t n)
       __attribute__ ((__pure__));



extern const gl_uint32_t *
       u32_check (const gl_uint32_t *s, size_t n)
       __attribute__ ((__pure__));





extern gl_uint16_t *
       u8_to_u16 (const gl_uint8_t *s, size_t n, gl_uint16_t *resultbuf,
                  size_t *lengthp);


extern gl_uint32_t *
       u8_to_u32 (const gl_uint8_t *s, size_t n, gl_uint32_t *resultbuf,
                  size_t *lengthp);


extern gl_uint8_t *
       u16_to_u8 (const gl_uint16_t *s, size_t n, gl_uint8_t *resultbuf,
                  size_t *lengthp);


extern gl_uint32_t *
       u16_to_u32 (const gl_uint16_t *s, size_t n, gl_uint32_t *resultbuf,
                   size_t *lengthp);


extern gl_uint8_t *
       u32_to_u8 (const gl_uint32_t *s, size_t n, gl_uint8_t *resultbuf,
                  size_t *lengthp);


extern gl_uint16_t *
       u32_to_u16 (const gl_uint32_t *s, size_t n, gl_uint16_t *resultbuf,
                   size_t *lengthp);
extern int
       u8_mblen (const gl_uint8_t *s, size_t n)
       __attribute__ ((__pure__));
extern int
       u16_mblen (const gl_uint16_t *s, size_t n)
       __attribute__ ((__pure__));
extern int
       u32_mblen (const gl_uint32_t *s, size_t n)
       __attribute__ ((__pure__));
extern int
       u8_mbtoucr (ucs4_t *puc, const gl_uint8_t *s, size_t n);
extern int
       u8_uctomb_aux (gl_uint8_t *s, ucs4_t uc, int n);




static inline int
u8_uctomb (gl_uint8_t *s, ucs4_t uc, int n)
{
  if (uc < 0x80 && n > 0)
    {
      s[0] = uc;
      return 1;
    }
  else
    return u8_uctomb_aux (s, uc, n);
}
extern gl_uint8_t *
       u8_cpy (gl_uint8_t *dest, const gl_uint8_t *src, size_t n);
extern gl_uint16_t *
       u16_cpy (gl_uint16_t *dest, const gl_uint16_t *src, size_t n);
extern gl_uint32_t *
       u32_cpy (gl_uint32_t *dest, const gl_uint32_t *src, size_t n);




extern gl_uint8_t *
       u8_move (gl_uint8_t *dest, const gl_uint8_t *src, size_t n);
extern gl_uint16_t *
       u16_move (gl_uint16_t *dest, const gl_uint16_t *src, size_t n);
extern gl_uint32_t *
       u32_move (gl_uint32_t *dest, const gl_uint32_t *src, size_t n);




extern gl_uint8_t *
       u8_set (gl_uint8_t *s, ucs4_t uc, size_t n);
extern gl_uint16_t *
       u16_set (gl_uint16_t *s, ucs4_t uc, size_t n);
extern gl_uint32_t *
       u32_set (gl_uint32_t *s, ucs4_t uc, size_t n);



extern int
       u8_cmp (const gl_uint8_t *s1, const gl_uint8_t *s2, size_t n)
       __attribute__ ((__pure__));
extern int
       u16_cmp (const gl_uint16_t *s1, const gl_uint16_t *s2, size_t n)
       __attribute__ ((__pure__));
extern int
       u32_cmp (const gl_uint32_t *s1, const gl_uint32_t *s2, size_t n)
       __attribute__ ((__pure__));



extern int
       u8_cmp2 (const gl_uint8_t *s1, size_t n1, const gl_uint8_t *s2, size_t n2)
       __attribute__ ((__pure__));
extern int
       u16_cmp2 (const gl_uint16_t *s1, size_t n1, const gl_uint16_t *s2, size_t n2)
       __attribute__ ((__pure__));
extern int
       u32_cmp2 (const gl_uint32_t *s1, size_t n1, const gl_uint32_t *s2, size_t n2)
       __attribute__ ((__pure__));



extern gl_uint8_t *
       u8_chr (const gl_uint8_t *s, size_t n, ucs4_t uc)
       __attribute__ ((__pure__));
extern gl_uint16_t *
       u16_chr (const gl_uint16_t *s, size_t n, ucs4_t uc)
       __attribute__ ((__pure__));
extern gl_uint32_t *
       u32_chr (const gl_uint32_t *s, size_t n, ucs4_t uc)
       __attribute__ ((__pure__));



extern size_t
       u8_mbsnlen (const gl_uint8_t *s, size_t n)
       __attribute__ ((__pure__));
extern size_t
       u16_mbsnlen (const gl_uint16_t *s, size_t n)
       __attribute__ ((__pure__));
extern size_t
       u32_mbsnlen (const gl_uint32_t *s, size_t n)
       __attribute__ ((__pure__));




extern gl_uint8_t *
       u8_cpy_alloc (const gl_uint8_t *s, size_t n);
extern gl_uint16_t *
       u16_cpy_alloc (const gl_uint16_t *s, size_t n);
extern gl_uint32_t *
       u32_cpy_alloc (const gl_uint32_t *s, size_t n);





extern int
       u8_strmblen (const gl_uint8_t *s)
       __attribute__ ((__pure__));
extern int
       u16_strmblen (const gl_uint16_t *s)
       __attribute__ ((__pure__));
extern int
       u32_strmblen (const gl_uint32_t *s)
       __attribute__ ((__pure__));




extern int
       u8_strmbtouc (ucs4_t *puc, const gl_uint8_t *s);
extern int
       u16_strmbtouc (ucs4_t *puc, const gl_uint16_t *s);
extern int
       u32_strmbtouc (ucs4_t *puc, const gl_uint32_t *s);




extern const gl_uint8_t *
       u8_next (ucs4_t *puc, const gl_uint8_t *s);
extern const gl_uint16_t *
       u16_next (ucs4_t *puc, const gl_uint16_t *s);
extern const gl_uint32_t *
       u32_next (ucs4_t *puc, const gl_uint32_t *s);




extern const gl_uint8_t *
       u8_prev (ucs4_t *puc, const gl_uint8_t *s, const gl_uint8_t *start);
extern const gl_uint16_t *
       u16_prev (ucs4_t *puc, const gl_uint16_t *s, const gl_uint16_t *start);
extern const gl_uint32_t *
       u32_prev (ucs4_t *puc, const gl_uint32_t *s, const gl_uint32_t *start);



extern size_t
       u8_strlen (const gl_uint8_t *s)
       __attribute__ ((__pure__));
extern size_t
       u16_strlen (const gl_uint16_t *s)
       __attribute__ ((__pure__));
extern size_t
       u32_strlen (const gl_uint32_t *s)
       __attribute__ ((__pure__));



extern size_t
       u8_strnlen (const gl_uint8_t *s, size_t maxlen)
       __attribute__ ((__pure__));
extern size_t
       u16_strnlen (const gl_uint16_t *s, size_t maxlen)
       __attribute__ ((__pure__));
extern size_t
       u32_strnlen (const gl_uint32_t *s, size_t maxlen)
       __attribute__ ((__pure__));



extern gl_uint8_t *
       u8_strcpy (gl_uint8_t *dest, const gl_uint8_t *src);
extern gl_uint16_t *
       u16_strcpy (gl_uint16_t *dest, const gl_uint16_t *src);
extern gl_uint32_t *
       u32_strcpy (gl_uint32_t *dest, const gl_uint32_t *src);



extern gl_uint8_t *
       u8_stpcpy (gl_uint8_t *dest, const gl_uint8_t *src);
extern gl_uint16_t *
       u16_stpcpy (gl_uint16_t *dest, const gl_uint16_t *src);
extern gl_uint32_t *
       u32_stpcpy (gl_uint32_t *dest, const gl_uint32_t *src);



extern gl_uint8_t *
       u8_strncpy (gl_uint8_t *dest, const gl_uint8_t *src, size_t n);
extern gl_uint16_t *
       u16_strncpy (gl_uint16_t *dest, const gl_uint16_t *src, size_t n);
extern gl_uint32_t *
       u32_strncpy (gl_uint32_t *dest, const gl_uint32_t *src, size_t n);




extern gl_uint8_t *
       u8_stpncpy (gl_uint8_t *dest, const gl_uint8_t *src, size_t n);
extern gl_uint16_t *
       u16_stpncpy (gl_uint16_t *dest, const gl_uint16_t *src, size_t n);
extern gl_uint32_t *
       u32_stpncpy (gl_uint32_t *dest, const gl_uint32_t *src, size_t n);



extern gl_uint8_t *
       u8_strcat (gl_uint8_t *dest, const gl_uint8_t *src);
extern gl_uint16_t *
       u16_strcat (gl_uint16_t *dest, const gl_uint16_t *src);
extern gl_uint32_t *
       u32_strcat (gl_uint32_t *dest, const gl_uint32_t *src);



extern gl_uint8_t *
       u8_strncat (gl_uint8_t *dest, const gl_uint8_t *src, size_t n);
extern gl_uint16_t *
       u16_strncat (gl_uint16_t *dest, const gl_uint16_t *src, size_t n);
extern gl_uint32_t *
       u32_strncat (gl_uint32_t *dest, const gl_uint32_t *src, size_t n);
extern int
       u8_strcmp (const gl_uint8_t *s1, const gl_uint8_t *s2)
       __attribute__ ((__pure__));

extern int
       u16_strcmp (const gl_uint16_t *s1, const gl_uint16_t *s2)
       __attribute__ ((__pure__));
extern int
       u32_strcmp (const gl_uint32_t *s1, const gl_uint32_t *s2)
       __attribute__ ((__pure__));





extern int
       u8_strcoll (const gl_uint8_t *s1, const gl_uint8_t *s2);
extern int
       u16_strcoll (const gl_uint16_t *s1, const gl_uint16_t *s2);
extern int
       u32_strcoll (const gl_uint32_t *s1, const gl_uint32_t *s2);



extern int
       u8_strncmp (const gl_uint8_t *s1, const gl_uint8_t *s2, size_t n)
       __attribute__ ((__pure__));
extern int
       u16_strncmp (const gl_uint16_t *s1, const gl_uint16_t *s2, size_t n)
       __attribute__ ((__pure__));
extern int
       u32_strncmp (const gl_uint32_t *s1, const gl_uint32_t *s2, size_t n)
       __attribute__ ((__pure__));



extern gl_uint8_t *
       u8_strdup (const gl_uint8_t *s);
extern gl_uint16_t *
       u16_strdup (const gl_uint16_t *s);
extern gl_uint32_t *
       u32_strdup (const gl_uint32_t *s);



extern gl_uint8_t *
       u8_strchr (const gl_uint8_t *str, ucs4_t uc)
       __attribute__ ((__pure__));
extern gl_uint16_t *
       u16_strchr (const gl_uint16_t *str, ucs4_t uc)
       __attribute__ ((__pure__));
extern gl_uint32_t *
       u32_strchr (const gl_uint32_t *str, ucs4_t uc)
       __attribute__ ((__pure__));



extern gl_uint8_t *
       u8_strrchr (const gl_uint8_t *str, ucs4_t uc)
       __attribute__ ((__pure__));
extern gl_uint16_t *
       u16_strrchr (const gl_uint16_t *str, ucs4_t uc)
       __attribute__ ((__pure__));
extern gl_uint32_t *
       u32_strrchr (const gl_uint32_t *str, ucs4_t uc)
       __attribute__ ((__pure__));




extern size_t
       u8_strcspn (const gl_uint8_t *str, const gl_uint8_t *reject)
       __attribute__ ((__pure__));
extern size_t
       u16_strcspn (const gl_uint16_t *str, const gl_uint16_t *reject)
       __attribute__ ((__pure__));
extern size_t
       u32_strcspn (const gl_uint32_t *str, const gl_uint32_t *reject)
       __attribute__ ((__pure__));




extern size_t
       u8_strspn (const gl_uint8_t *str, const gl_uint8_t *accept)
       __attribute__ ((__pure__));
extern size_t
       u16_strspn (const gl_uint16_t *str, const gl_uint16_t *accept)
       __attribute__ ((__pure__));
extern size_t
       u32_strspn (const gl_uint32_t *str, const gl_uint32_t *accept)
       __attribute__ ((__pure__));



extern gl_uint8_t *
       u8_strpbrk (const gl_uint8_t *str, const gl_uint8_t *accept)
       __attribute__ ((__pure__));
extern gl_uint16_t *
       u16_strpbrk (const gl_uint16_t *str, const gl_uint16_t *accept)
       __attribute__ ((__pure__));
extern gl_uint32_t *
       u32_strpbrk (const gl_uint32_t *str, const gl_uint32_t *accept)
       __attribute__ ((__pure__));



extern gl_uint8_t *
       u8_strstr (const gl_uint8_t *haystack, const gl_uint8_t *needle)
       __attribute__ ((__pure__));
extern gl_uint16_t *
       u16_strstr (const gl_uint16_t *haystack, const gl_uint16_t *needle)
       __attribute__ ((__pure__));
extern gl_uint32_t *
       u32_strstr (const gl_uint32_t *haystack, const gl_uint32_t *needle)
       __attribute__ ((__pure__));


extern _Bool
       u8_startswith (const gl_uint8_t *str, const gl_uint8_t *prefix)
       __attribute__ ((__pure__));
extern _Bool
       u16_startswith (const gl_uint16_t *str, const gl_uint16_t *prefix)
       __attribute__ ((__pure__));
extern _Bool
       u32_startswith (const gl_uint32_t *str, const gl_uint32_t *prefix)
       __attribute__ ((__pure__));


extern _Bool
       u8_endswith (const gl_uint8_t *str, const gl_uint8_t *suffix)
       __attribute__ ((__pure__));
extern _Bool
       u16_endswith (const gl_uint16_t *str, const gl_uint16_t *suffix)
       __attribute__ ((__pure__));
extern _Bool
       u32_endswith (const gl_uint32_t *str, const gl_uint32_t *suffix)
       __attribute__ ((__pure__));




extern gl_uint8_t *
       u8_strtok (gl_uint8_t *str, const gl_uint8_t *delim, gl_uint8_t **ptr);
extern gl_uint16_t *
       u16_strtok (gl_uint16_t *str, const gl_uint16_t *delim, gl_uint16_t **ptr);
extern gl_uint32_t *
       u32_strtok (gl_uint32_t *str, const gl_uint32_t *delim, gl_uint32_t **ptr);
int
u8_uctomb_aux (gl_uint8_t *s, ucs4_t uc, int n)
{
  int count;

  if (uc < 0x80)

    return -2;
  else if (uc < 0x800)
    count = 2;
  else if (uc < 0x10000)
    {
      if (uc < 0xd800 || uc >= 0xe000)
        count = 3;
      else
        return -1;
    }
  else if (uc < 0x110000)
    count = 4;
  else
    return -1;

  if (n < count)
    return -2;

  switch (count)
    {
    case 4: s[3] = 0x80 | (uc & 0x3f); uc = uc >> 6; uc |= 0x10000;
      ((void) 0);
    case 3: s[2] = 0x80 | (uc & 0x3f); uc = uc >> 6; uc |= 0x800;
      ((void) 0);
    case 2: s[1] = 0x80 | (uc & 0x3f); uc = uc >> 6; uc |= 0xc0;
              s[0] = uc;
    }
  return count;
}
