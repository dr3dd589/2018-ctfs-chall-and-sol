       










typedef unsigned int size_t;



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
struct _IO_FILE;



typedef struct _IO_FILE FILE;





typedef struct _IO_FILE __FILE;




       
typedef struct
{
  int __count;
  union
  {

    unsigned int __wch;



    char __wchb[4];
  } __value;
} __mbstate_t;

typedef struct
{
  __off_t __pos;
  __mbstate_t __state;
} _G_fpos_t;
typedef struct
{
  __off64_t __pos;
  __mbstate_t __state;
} _G_fpos64_t;
typedef int _G_int16_t __attribute__ ((__mode__ (__HI__)));
typedef int _G_int32_t __attribute__ ((__mode__ (__SI__)));
typedef unsigned int _G_uint16_t __attribute__ ((__mode__ (__HI__)));
typedef unsigned int _G_uint32_t __attribute__ ((__mode__ (__SI__)));
typedef __builtin_va_list __gnuc_va_list;
struct _IO_jump_t; struct _IO_FILE;
typedef void _IO_lock_t;





struct _IO_marker {
  struct _IO_marker *_next;
  struct _IO_FILE *_sbuf;



  int _pos;
};


enum __codecvt_result
{
  __codecvt_ok,
  __codecvt_partial,
  __codecvt_error,
  __codecvt_noconv
};
struct _IO_FILE {
  int _flags;




  char* _IO_read_ptr;
  char* _IO_read_end;
  char* _IO_read_base;
  char* _IO_write_base;
  char* _IO_write_ptr;
  char* _IO_write_end;
  char* _IO_buf_base;
  char* _IO_buf_end;

  char *_IO_save_base;
  char *_IO_backup_base;
  char *_IO_save_end;

  struct _IO_marker *_markers;

  struct _IO_FILE *_chain;

  int _fileno;



  int _flags2;

  __off_t _old_offset;



  unsigned short _cur_column;
  signed char _vtable_offset;
  char _shortbuf[1];



  _IO_lock_t *_lock;
  __off64_t _offset;
  void *__pad1;
  void *__pad2;
  void *__pad3;
  void *__pad4;
  size_t __pad5;

  int _mode;

  char _unused2[15 * sizeof (int) - 4 * sizeof (void *) - sizeof (size_t)];

};


typedef struct _IO_FILE _IO_FILE;


struct _IO_FILE_plus;

extern struct _IO_FILE_plus _IO_2_1_stdin_;
extern struct _IO_FILE_plus _IO_2_1_stdout_;
extern struct _IO_FILE_plus _IO_2_1_stderr_;
typedef __ssize_t __io_read_fn (void *__cookie, char *__buf, size_t __nbytes);







typedef __ssize_t __io_write_fn (void *__cookie, __const char *__buf,
     size_t __n);







typedef int __io_seek_fn (void *__cookie, __off64_t *__pos, int __w);


typedef int __io_close_fn (void *__cookie);
extern int __underflow (_IO_FILE *);
extern int __uflow (_IO_FILE *);
extern int __overflow (_IO_FILE *, int);
extern int _IO_getc (_IO_FILE *__fp);
extern int _IO_putc (int __c, _IO_FILE *__fp);
extern int _IO_feof (_IO_FILE *__fp) __attribute__ ((__nothrow__));
extern int _IO_ferror (_IO_FILE *__fp) __attribute__ ((__nothrow__));

extern int _IO_peekc_locked (_IO_FILE *__fp);





extern void _IO_flockfile (_IO_FILE *) __attribute__ ((__nothrow__));
extern void _IO_funlockfile (_IO_FILE *) __attribute__ ((__nothrow__));
extern int _IO_ftrylockfile (_IO_FILE *) __attribute__ ((__nothrow__));
extern int _IO_vfscanf (_IO_FILE * __restrict, const char * __restrict,
   __gnuc_va_list, int *__restrict);
extern int _IO_vfprintf (_IO_FILE *__restrict, const char *__restrict,
    __gnuc_va_list);
extern __ssize_t _IO_padn (_IO_FILE *, int, __ssize_t);
extern size_t _IO_sgetn (_IO_FILE *, void *, size_t);

extern __off64_t _IO_seekoff (_IO_FILE *, __off64_t, int, int);
extern __off64_t _IO_seekpos (_IO_FILE *, __off64_t, int);

extern void _IO_free_backup_area (_IO_FILE *) __attribute__ ((__nothrow__));




typedef __gnuc_va_list va_list;
typedef __off_t off_t;
typedef __ssize_t ssize_t;







typedef _G_fpos_t fpos_t;







extern struct _IO_FILE *stdin;
extern struct _IO_FILE *stdout;
extern struct _IO_FILE *stderr;







extern int remove (__const char *__filename) __attribute__ ((__nothrow__));

extern int rename (__const char *__old, __const char *__new) __attribute__ ((__nothrow__));




extern int renameat (int __oldfd, __const char *__old, int __newfd,
       __const char *__new) __attribute__ ((__nothrow__));








extern FILE *tmpfile (void) ;
extern char *tmpnam (char *__s) __attribute__ ((__nothrow__)) ;





extern char *tmpnam_r (char *__s) __attribute__ ((__nothrow__)) ;
extern char *tempnam (__const char *__dir, __const char *__pfx)
     __attribute__ ((__nothrow__)) __attribute__ ((__malloc__)) ;








extern int fclose (FILE *__stream);




extern int fflush (FILE *__stream);

extern int fflush_unlocked (FILE *__stream);






extern FILE *fopen (__const char *__restrict __filename,
      __const char *__restrict __modes) ;




extern FILE *freopen (__const char *__restrict __filename,
        __const char *__restrict __modes,
        FILE *__restrict __stream) ;

extern FILE *fdopen (int __fd, __const char *__modes) __attribute__ ((__nothrow__)) ;
extern FILE *fmemopen (void *__s, size_t __len, __const char *__modes)
  __attribute__ ((__nothrow__)) ;




extern FILE *open_memstream (char **__bufloc, size_t *__sizeloc) __attribute__ ((__nothrow__)) ;






extern void setbuf (FILE *__restrict __stream, char *__restrict __buf) __attribute__ ((__nothrow__));



extern int setvbuf (FILE *__restrict __stream, char *__restrict __buf,
      int __modes, size_t __n) __attribute__ ((__nothrow__));





extern void setbuffer (FILE *__restrict __stream, char *__restrict __buf,
         size_t __size) __attribute__ ((__nothrow__));


extern void setlinebuf (FILE *__stream) __attribute__ ((__nothrow__));








extern int fprintf (FILE *__restrict __stream,
      __const char *__restrict __format, ...);




extern int printf (__const char *__restrict __format, ...);

extern int sprintf (char *__restrict __s,
      __const char *__restrict __format, ...) __attribute__ ((__nothrow__));





extern int vfprintf (FILE *__restrict __s, __const char *__restrict __format,
       __gnuc_va_list __arg);




extern int vprintf (int *data_flow, __const char *__restrict __format, __gnuc_va_list __arg);

extern int vsprintf (char *__restrict __s, __const char *__restrict __format,
       __gnuc_va_list __arg) __attribute__ ((__nothrow__));





extern int snprintf (char *__restrict __s, size_t __maxlen,
       __const char *__restrict __format, ...)
     __attribute__ ((__nothrow__)) __attribute__ ((__format__ (__printf__, 3, 4)));

extern int vsnprintf (char *__restrict __s, size_t __maxlen,
        __const char *__restrict __format, __gnuc_va_list __arg)
     __attribute__ ((__nothrow__)) __attribute__ ((__format__ (__printf__, 3, 0)));

extern int vdprintf (int __fd, __const char *__restrict __fmt,
       __gnuc_va_list __arg)
     __attribute__ ((__format__ (__printf__, 2, 0)));
extern int dprintf (int __fd, __const char *__restrict __fmt, ...)
     __attribute__ ((__format__ (__printf__, 2, 3)));








extern int fscanf (FILE *__restrict __stream,
     __const char *__restrict __format, ...) ;




extern int scanf (__const char *__restrict __format, ...) ;

extern int sscanf (__const char *__restrict __s,
     __const char *__restrict __format, ...) __attribute__ ((__nothrow__));
extern int fscanf (FILE *__restrict __stream, __const char *__restrict __format, ...) __asm__ ("" "__isoc99_fscanf")

                               ;
extern int scanf (__const char *__restrict __format, ...) __asm__ ("" "__isoc99_scanf")
                              ;
extern int sscanf (__const char *__restrict __s, __const char *__restrict __format, ...) __asm__ ("" "__isoc99_sscanf") __attribute__ ((__nothrow__))

                      ;








extern int vfscanf (FILE *__restrict __s, __const char *__restrict __format,
      __gnuc_va_list __arg)
     __attribute__ ((__format__ (__scanf__, 2, 0))) ;





extern int vscanf (__const char *__restrict __format, __gnuc_va_list __arg)
     __attribute__ ((__format__ (__scanf__, 1, 0))) ;


extern int vsscanf (__const char *__restrict __s,
      __const char *__restrict __format, __gnuc_va_list __arg)
     __attribute__ ((__nothrow__)) __attribute__ ((__format__ (__scanf__, 2, 0)));
extern int vfscanf (FILE *__restrict __s, __const char *__restrict __format, __gnuc_va_list __arg) __asm__ ("" "__isoc99_vfscanf")



     __attribute__ ((__format__ (__scanf__, 2, 0))) ;
extern int vscanf (__const char *__restrict __format, __gnuc_va_list __arg) __asm__ ("" "__isoc99_vscanf")

     __attribute__ ((__format__ (__scanf__, 1, 0))) ;
extern int vsscanf (__const char *__restrict __s, __const char *__restrict __format, __gnuc_va_list __arg) __asm__ ("" "__isoc99_vsscanf") __attribute__ ((__nothrow__))



     __attribute__ ((__format__ (__scanf__, 2, 0)));









extern int fgetc (FILE *__stream);
extern int getc (FILE *__stream);





extern int getchar (int *data_flow);

extern int getc_unlocked (FILE *__stream);
extern int getchar_unlocked (int *data_flow);
extern int fgetc_unlocked (int *data_flow, FILE *__stream);











extern int fputc (int __c, FILE *__stream);
extern int putc (int __c, FILE *__stream);





extern int putchar (int *data_flow, int __c);

extern int fputc_unlocked (int *data_flow, int __c, FILE *__stream);







extern int putc_unlocked (int *data_flow, int __c, FILE *__stream);
extern int putchar_unlocked (int *data_flow, int __c);






extern int getw (FILE *__stream);


extern int putw (int __w, FILE *__stream);








extern char *fgets (char *__restrict __s, int __n, FILE *__restrict __stream)
     ;






extern char *gets (char *__s) ;

extern __ssize_t __getdelim (char **__restrict __lineptr,
          size_t *__restrict __n, int __delimiter,
          FILE *__restrict __stream) ;
extern __ssize_t getdelim (char **__restrict __lineptr,
        size_t *__restrict __n, int __delimiter,
        FILE *__restrict __stream) ;







extern __ssize_t getline (char **__restrict __lineptr,
       size_t *__restrict __n,
       FILE *__restrict __stream) ;








extern int fputs (__const char *__restrict __s, FILE *__restrict __stream);





extern int puts (__const char *__s);






extern int ungetc (int __c, FILE *__stream);






extern size_t fread (void *__restrict __ptr, size_t __size,
       size_t __n, FILE *__restrict __stream) ;




extern size_t fwrite (__const void *__restrict __ptr, size_t __size,
        size_t __n, FILE *__restrict __s);

extern size_t fread_unlocked (void *__restrict __ptr, size_t __size,
         size_t __n, FILE *__restrict __stream) ;
extern size_t fwrite_unlocked (__const void *__restrict __ptr, size_t __size,
          size_t __n, FILE *__restrict __stream);








extern int fseek (FILE *__stream, long int __off, int __whence);




extern long int ftell (FILE *__stream) ;




extern void rewind (FILE *__stream);

extern int fseeko (FILE *__stream, __off_t __off, int __whence);




extern __off_t ftello (FILE *__stream) ;






extern int fgetpos (FILE *__restrict __stream, fpos_t *__restrict __pos);




extern int fsetpos (FILE *__stream, __const fpos_t *__pos);



extern void clearerr (FILE *__stream) __attribute__ ((__nothrow__));

extern int feof (FILE *__stream) __attribute__ ((__nothrow__)) ;

extern int ferror (FILE *__stream) __attribute__ ((__nothrow__)) ;




extern void clearerr_unlocked (FILE *__stream) __attribute__ ((__nothrow__));
extern int feof_unlocked (int *data_flow, FILE *__stream) __attribute__ ((__nothrow__)) ;
extern int ferror_unlocked (FILE *__stream) __attribute__ ((__nothrow__)) ;








extern void perror (__const char *__s);






extern int sys_nerr;
extern __const char *__const sys_errlist[];




extern int fileno (FILE *__stream) __attribute__ ((__nothrow__)) ;




extern int fileno_unlocked (FILE *__stream) __attribute__ ((__nothrow__)) ;
extern FILE *popen (__const char *__command, __const char *__modes) ;





extern int pclose (FILE *__stream);





extern char *ctermid (char *__s) __attribute__ ((__nothrow__));
extern void flockfile (FILE *__stream) __attribute__ ((__nothrow__));



extern int ftrylockfile (FILE *__stream) __attribute__ ((__nothrow__)) ;


extern void funlockfile (FILE *__stream) __attribute__ ((__nothrow__));
extern __inline __attribute__ ((__gnu_inline__)) int
vprintf (int *data_flow, __const char *__restrict __fmt, __gnuc_va_list __arg)
{
  return vfprintf (stdout, __fmt, __arg);
}



extern __inline __attribute__ ((__gnu_inline__)) int
getchar (int *data_flow)
{
  return _IO_getc (stdin);
}




extern __inline __attribute__ ((__gnu_inline__)) int
fgetc_unlocked (int *data_flow, FILE *__fp)
{
  return (__builtin_expect (((__fp)->_IO_read_ptr >= (__fp)->_IO_read_end), 0) ? __uflow (__fp) : *(unsigned char *) (__fp)->_IO_read_ptr++);
}





extern __inline __attribute__ ((__gnu_inline__)) int
getc_unlocked (FILE *__fp)
{
  return (__builtin_expect (((__fp)->_IO_read_ptr >= (__fp)->_IO_read_end), 0) ? __uflow (__fp) : *(unsigned char *) (__fp)->_IO_read_ptr++);
}


extern __inline __attribute__ ((__gnu_inline__)) int
getchar_unlocked (int *data_flow)
{
  return (__builtin_expect (((stdin)->_IO_read_ptr >= (stdin)->_IO_read_end), 0) ? __uflow (stdin) : *(unsigned char *) (stdin)->_IO_read_ptr++);
}




extern __inline __attribute__ ((__gnu_inline__)) int
putchar (int *data_flow, int __c)
{
  return _IO_putc (__c, stdout);
}




extern __inline __attribute__ ((__gnu_inline__)) int
fputc_unlocked (int *data_flow, int __c, FILE *__stream)
{
  return (__builtin_expect (((__stream)->_IO_write_ptr >= (__stream)->_IO_write_end), 0) ? __overflow (__stream, (unsigned char) (__c)) : (unsigned char) (*(__stream)->_IO_write_ptr++ = (__c)));
}





extern __inline __attribute__ ((__gnu_inline__)) int
putc_unlocked (int *data_flow, int __c, FILE *__stream)
{
  return (__builtin_expect (((__stream)->_IO_write_ptr >= (__stream)->_IO_write_end), 0) ? __overflow (__stream, (unsigned char) (__c)) : (unsigned char) (*(__stream)->_IO_write_ptr++ = (__c)));
}


extern __inline __attribute__ ((__gnu_inline__)) int
putchar_unlocked (int *data_flow, int __c)
{
  return (__builtin_expect (((stdout)->_IO_write_ptr >= (stdout)->_IO_write_end), 0) ? __overflow (stdout, (unsigned char) (__c)) : (unsigned char) (*(stdout)->_IO_write_ptr++ = (__c)));
}
extern __inline __attribute__ ((__gnu_inline__)) int
__attribute__ ((__nothrow__)) feof_unlocked (int *data_flow, FILE *__stream)
{
  return (((__stream)->_flags & 0x10) != 0);
}


extern __inline __attribute__ ((__gnu_inline__)) int
__attribute__ ((__nothrow__)) ferror_unlocked (FILE *__stream)
{
  return (((__stream)->_flags & 0x20) != 0);
}









typedef int ptrdiff_t;
typedef int wchar_t;
typedef struct {
  long long __max_align_ll __attribute__((__aligned__(__alignof__(long long))));
  long double __max_align_ld __attribute__((__aligned__(__alignof__(long double))));
} max_align_t;




       













typedef __u_char u_char;
typedef __u_short u_short;
typedef __u_int u_int;
typedef __u_long u_long;
typedef __quad_t quad_t;
typedef __u_quad_t u_quad_t;
typedef __fsid_t fsid_t;




typedef __loff_t loff_t;



typedef __ino_t ino_t;
typedef __dev_t dev_t;




typedef __gid_t gid_t;




typedef __mode_t mode_t;




typedef __nlink_t nlink_t;




typedef __uid_t uid_t;
typedef __pid_t pid_t;





typedef __id_t id_t;
typedef __daddr_t daddr_t;
typedef __caddr_t caddr_t;





typedef __key_t key_t;
       


typedef __clock_t clock_t;





typedef __time_t time_t;



typedef __clockid_t clockid_t;
typedef __timer_t timer_t;



typedef unsigned long int ulong;
typedef unsigned short int ushort;
typedef unsigned int uint;
typedef int int8_t __attribute__ ((__mode__ (__QI__)));
typedef int int16_t __attribute__ ((__mode__ (__HI__)));
typedef int int32_t __attribute__ ((__mode__ (__SI__)));
typedef int int64_t __attribute__ ((__mode__ (__DI__)));


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


typedef __suseconds_t suseconds_t;





typedef long int __fd_mask;
typedef struct
  {






    __fd_mask __fds_bits[1024 / (8 * (int) sizeof (__fd_mask))];


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
extern unsigned int gnu_dev_major (int *data_flow, unsigned long long int __dev)
     __attribute__ ((__nothrow__));
__extension__
extern unsigned int gnu_dev_minor (int *data_flow, unsigned long long int __dev)
     __attribute__ ((__nothrow__));
__extension__
extern unsigned long long int gnu_dev_makedev (int *data_flow, unsigned int __major,
            unsigned int __minor)
     __attribute__ ((__nothrow__));


__extension__ extern __inline __attribute__ ((__gnu_inline__)) unsigned int
__attribute__ ((__nothrow__)) gnu_dev_major (int *data_flow, unsigned long long int __dev)
{
  return ((__dev >> 8) & 0xfff) | ((unsigned int) (__dev >> 32) & ~0xfff);
}

__extension__ extern __inline __attribute__ ((__gnu_inline__)) unsigned int
__attribute__ ((__nothrow__)) gnu_dev_minor (int *data_flow, unsigned long long int __dev)
{
  return (__dev & 0xff) | ((unsigned int) (__dev >> 12) & ~0xff);
}

__extension__ extern __inline __attribute__ ((__gnu_inline__)) unsigned long long int
__attribute__ ((__nothrow__)) gnu_dev_makedev (int *data_flow, unsigned int __major, unsigned int __minor)
{
  return ((__minor & 0xff) | ((__major & 0xfff) << 8)
   | (((unsigned long long int) (__minor & ~0xff)) << 12)
   | (((unsigned long long int) (__major & ~0xfff)) << 32));
}





typedef __blksize_t blksize_t;






typedef __blkcnt_t blkcnt_t;



typedef __fsblkcnt_t fsblkcnt_t;



typedef __fsfilcnt_t fsfilcnt_t;
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



extern int _gl_cxxalias_dummy;

extern int _gl_cxxalias_dummy;
extern int _gl_cxxalias_dummy;

extern int _gl_cxxalias_dummy;
extern int _gl_cxxalias_dummy;

extern int _gl_cxxalias_dummy;
extern int _gl_cxxalias_dummy;

extern int _gl_cxxalias_dummy;
extern int _gl_cxxalias_dummy;

extern int _gl_cxxalias_dummy;
extern int _gl_cxxalias_dummy;

extern int _gl_cxxalias_dummy;
extern int _gl_cxxalias_dummy;

extern int _gl_cxxalias_dummy;
extern int _gl_cxxalias_dummy
                                                                      ;
extern int _gl_cxxalias_dummy;
extern int _gl_cxxalias_dummy;

extern int _gl_cxxalias_dummy;
extern int _gl_cxxalias_dummy;

extern int _gl_cxxalias_dummy;
extern int _gl_cxxalias_dummy;

extern int _gl_cxxalias_dummy;
extern int _gl_cxxalias_dummy;

extern int _gl_cxxalias_dummy;
extern int _gl_cxxalias_dummy;

extern int _gl_cxxalias_dummy;
extern int _gl_cxxalias_dummy;

extern int _gl_cxxalias_dummy;
extern int _gl_cxxalias_dummy;

extern int _gl_cxxalias_dummy;
extern int _gl_cxxalias_dummy
                                                                    ;

extern int _gl_cxxalias_dummy;
extern int _gl_cxxalias_dummy;

extern int _gl_cxxalias_dummy;


struct kwsmatch
{
  ptrdiff_t index;
  ptrdiff_t offset[1];
  ptrdiff_t size[1];
};


struct kwset;
typedef struct kwset *kwset_t;

extern kwset_t kwsalloc (int *data_flow, char const *);
extern void kwsincr (int *data_flow, kwset_t, char const *, ptrdiff_t);
extern ptrdiff_t kwswords (int *data_flow, kwset_t) __attribute__ ((__pure__));
extern void kwsprep (int *data_flow, kwset_t);
extern ptrdiff_t kwsexec (int *data_flow, kwset_t, char const *, ptrdiff_t,
                          struct kwsmatch *, _Bool)
  ;
extern void kwsfree (int *data_flow, kwset_t);

       


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







       










       







       






       






typedef int __gwchar_t;

typedef struct
  {
    long long int quot;
    long long int rem;
  } imaxdiv_t;





extern intmax_t imaxabs (intmax_t __n) __attribute__ ((__nothrow__)) __attribute__ ((__const__));


extern imaxdiv_t imaxdiv (intmax_t __numer, intmax_t __denom)
      __attribute__ ((__nothrow__)) __attribute__ ((__const__));


extern intmax_t strtoimax (int *data_flow, __const char *__restrict __nptr,
      char **__restrict __endptr, int __base) __attribute__ ((__nothrow__));


extern uintmax_t strtoumax (int *data_flow, __const char *__restrict __nptr,
       char ** __restrict __endptr, int __base) __attribute__ ((__nothrow__));


extern intmax_t wcstoimax (int *data_flow, __const __gwchar_t *__restrict __nptr,
      __gwchar_t **__restrict __endptr, int __base)
     __attribute__ ((__nothrow__));


extern uintmax_t wcstoumax (int *data_flow, __const __gwchar_t *__restrict __nptr,
       __gwchar_t ** __restrict __endptr, int __base)
     __attribute__ ((__nothrow__));
__extension__
extern long long int __strtoll_internal (__const char *__restrict __nptr,
      char **__restrict __endptr,
      int __base, int __group)
  __attribute__ ((__nothrow__))  ;

extern __inline __attribute__ ((__gnu_inline__)) intmax_t
__attribute__ ((__nothrow__)) strtoimax (int *data_flow, __const char *__restrict nptr, char **__restrict endptr, int base)

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
  __attribute__ ((__nothrow__))  ;

extern __inline __attribute__ ((__gnu_inline__)) uintmax_t
__attribute__ ((__nothrow__)) strtoumax (int *data_flow, __const char *__restrict nptr, char **__restrict endptr, int base)

{
  return __strtoull_internal (nptr, endptr, base, 0);
}

__extension__
extern long long int __wcstoll_internal (__const __gwchar_t *
      __restrict __nptr,
      __gwchar_t **__restrict __endptr,
      int __base, int __group)
  __attribute__ ((__nothrow__))  ;

extern __inline __attribute__ ((__gnu_inline__)) intmax_t
__attribute__ ((__nothrow__)) wcstoimax (int *data_flow, __const __gwchar_t *__restrict nptr, __gwchar_t **__restrict endptr, int base)

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
  __attribute__ ((__nothrow__))  ;

extern __inline __attribute__ ((__gnu_inline__)) uintmax_t
__attribute__ ((__nothrow__)) wcstoumax (int *data_flow, __const __gwchar_t *__restrict nptr, __gwchar_t **__restrict endptr, int base)

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
       
       
       




       
struct flock
  {
    short int l_type;
    short int l_whence;

    __off_t l_start;
    __off_t l_len;




    __pid_t l_pid;
  };







       
struct stat
  {
    __dev_t st_dev;
    unsigned short int __pad1;

    __ino_t st_ino;



    __mode_t st_mode;
    __nlink_t st_nlink;
    __uid_t st_uid;
    __gid_t st_gid;
    __dev_t st_rdev;
    unsigned short int __pad2;

    __off_t st_size;



    __blksize_t st_blksize;


    __blkcnt_t st_blocks;
    struct timespec st_atim;
    struct timespec st_mtim;
    struct timespec st_ctim;
    unsigned long int __unused4;
    unsigned long int __unused5;



  };
extern int fcntl (int __fd, int __cmd, ...);
extern int open (__const char *__file, int __oflag, ...) ;
extern int openat (int __fd, __const char *__file, int __oflag, ...)
     ;
extern int creat (__const char *__file, __mode_t __mode) ;
extern int lockf (int __fd, int __cmd, __off_t __len);
extern int posix_fadvise (int __fd, __off_t __offset, __off_t __len,
     int __advise) __attribute__ ((__nothrow__));
extern int posix_fallocate (int __fd, __off_t __offset, __off_t __len);

extern int rpl_fcntl (int *data_flow, int fd, int action, ...);
extern int _gl_cxxalias_dummy;






extern int _gl_cxxalias_dummy;
extern int _gl_cxxalias_dummy;




extern int _gl_cxxalias_dummy;
extern int _gl_cxxalias_dummy
                                                                               ;

extern int _gl_cxxalias_dummy;
       




typedef __useconds_t useconds_t;
typedef __socklen_t socklen_t;
extern int access (__const char *__name, int __type) __attribute__ ((__nothrow__)) ;
extern int faccessat (int __fd, __const char *__file, int __type, int __flag)
     __attribute__ ((__nothrow__))  ;
extern __off_t lseek (int __fd, __off_t __offset, int __whence) __attribute__ ((__nothrow__));
extern int close (int __fd);






extern ssize_t read (int __fd, void *__buf, size_t __nbytes) ;





extern ssize_t write (int __fd, __const void *__buf, size_t __n) ;
extern ssize_t pread (int __fd, void *__buf, size_t __nbytes,
        __off_t __offset) ;






extern ssize_t pwrite (int __fd, __const void *__buf, size_t __n,
         __off_t __offset) ;
extern int pipe (int __pipedes[2]) __attribute__ ((__nothrow__)) ;
extern unsigned int alarm (unsigned int __seconds) __attribute__ ((__nothrow__));
extern unsigned int sleep (unsigned int __seconds);







extern __useconds_t ualarm (__useconds_t __value, __useconds_t __interval)
     __attribute__ ((__nothrow__));






extern int usleep (__useconds_t __useconds);
extern int pause (void);



extern int chown (__const char *__file, __uid_t __owner, __gid_t __group)
     __attribute__ ((__nothrow__))  ;



extern int fchown (int __fd, __uid_t __owner, __gid_t __group) __attribute__ ((__nothrow__)) ;




extern int lchown (__const char *__file, __uid_t __owner, __gid_t __group)
     __attribute__ ((__nothrow__))  ;






extern int fchownat (int __fd, __const char *__file, __uid_t __owner,
       __gid_t __group, int __flag)
     __attribute__ ((__nothrow__))  ;



extern int chdir (__const char *__path) __attribute__ ((__nothrow__))  ;



extern int fchdir (int __fd) __attribute__ ((__nothrow__)) ;
extern char *getcwd (char *__buf, size_t __size) __attribute__ ((__nothrow__)) ;
extern char *getwd (char *__buf)
     __attribute__ ((__nothrow__))  __attribute__ ((__deprecated__)) ;




extern int dup (int __fd) __attribute__ ((__nothrow__)) ;


extern int dup2 (int __fd, int __fd2) __attribute__ ((__nothrow__));
extern char **__environ;







extern int execve (__const char *__path, char *__const __argv[],
     char *__const __envp[]) __attribute__ ((__nothrow__)) ;




extern int fexecve (int __fd, char *__const __argv[], char *__const __envp[])
     __attribute__ ((__nothrow__)) ;




extern int execv (__const char *__path, char *__const __argv[])
     __attribute__ ((__nothrow__)) ;



extern int execle (__const char *__path, __const char *__arg, ...)
     __attribute__ ((__nothrow__)) ;



extern int execl (__const char *__path, __const char *__arg, ...)
     __attribute__ ((__nothrow__)) ;



extern int execvp (__const char *__file, char *__const __argv[])
     __attribute__ ((__nothrow__)) ;




extern int execlp (__const char *__file, __const char *__arg, ...)
     __attribute__ ((__nothrow__)) ;
extern int nice (int __inc) __attribute__ ((__nothrow__)) ;




extern void _exit (int __status) __attribute__ ((__noreturn__));





enum
  {
    _PC_LINK_MAX,

    _PC_MAX_CANON,

    _PC_MAX_INPUT,

    _PC_NAME_MAX,

    _PC_PATH_MAX,

    _PC_PIPE_BUF,

    _PC_CHOWN_RESTRICTED,

    _PC_NO_TRUNC,

    _PC_VDISABLE,

    _PC_SYNC_IO,

    _PC_ASYNC_IO,

    _PC_PRIO_IO,

    _PC_SOCK_MAXBUF,

    _PC_FILESIZEBITS,

    _PC_REC_INCR_XFER_SIZE,

    _PC_REC_MAX_XFER_SIZE,

    _PC_REC_MIN_XFER_SIZE,

    _PC_REC_XFER_ALIGN,

    _PC_ALLOC_SIZE_MIN,

    _PC_SYMLINK_MAX,

    _PC_2_SYMLINKS

  };


enum
  {
    _SC_ARG_MAX,

    _SC_CHILD_MAX,

    _SC_CLK_TCK,

    _SC_NGROUPS_MAX,

    _SC_OPEN_MAX,

    _SC_STREAM_MAX,

    _SC_TZNAME_MAX,

    _SC_JOB_CONTROL,

    _SC_SAVED_IDS,

    _SC_REALTIME_SIGNALS,

    _SC_PRIORITY_SCHEDULING,

    _SC_TIMERS,

    _SC_ASYNCHRONOUS_IO,

    _SC_PRIORITIZED_IO,

    _SC_SYNCHRONIZED_IO,

    _SC_FSYNC,

    _SC_MAPPED_FILES,

    _SC_MEMLOCK,

    _SC_MEMLOCK_RANGE,

    _SC_MEMORY_PROTECTION,

    _SC_MESSAGE_PASSING,

    _SC_SEMAPHORES,

    _SC_SHARED_MEMORY_OBJECTS,

    _SC_AIO_LISTIO_MAX,

    _SC_AIO_MAX,

    _SC_AIO_PRIO_DELTA_MAX,

    _SC_DELAYTIMER_MAX,

    _SC_MQ_OPEN_MAX,

    _SC_MQ_PRIO_MAX,

    _SC_VERSION,

    _SC_PAGESIZE,


    _SC_RTSIG_MAX,

    _SC_SEM_NSEMS_MAX,

    _SC_SEM_VALUE_MAX,

    _SC_SIGQUEUE_MAX,

    _SC_TIMER_MAX,




    _SC_BC_BASE_MAX,

    _SC_BC_DIM_MAX,

    _SC_BC_SCALE_MAX,

    _SC_BC_STRING_MAX,

    _SC_COLL_WEIGHTS_MAX,

    _SC_EQUIV_CLASS_MAX,

    _SC_EXPR_NEST_MAX,

    _SC_LINE_MAX,

    _SC_RE_DUP_MAX,

    _SC_CHARCLASS_NAME_MAX,


    _SC_2_VERSION,

    _SC_2_C_BIND,

    _SC_2_C_DEV,

    _SC_2_FORT_DEV,

    _SC_2_FORT_RUN,

    _SC_2_SW_DEV,

    _SC_2_LOCALEDEF,


    _SC_PII,

    _SC_PII_XTI,

    _SC_PII_SOCKET,

    _SC_PII_INTERNET,

    _SC_PII_OSI,

    _SC_POLL,

    _SC_SELECT,

    _SC_UIO_MAXIOV,

    _SC_IOV_MAX = _SC_UIO_MAXIOV,

    _SC_PII_INTERNET_STREAM,

    _SC_PII_INTERNET_DGRAM,

    _SC_PII_OSI_COTS,

    _SC_PII_OSI_CLTS,

    _SC_PII_OSI_M,

    _SC_T_IOV_MAX,



    _SC_THREADS,

    _SC_THREAD_SAFE_FUNCTIONS,

    _SC_GETGR_R_SIZE_MAX,

    _SC_GETPW_R_SIZE_MAX,

    _SC_LOGIN_NAME_MAX,

    _SC_TTY_NAME_MAX,

    _SC_THREAD_DESTRUCTOR_ITERATIONS,

    _SC_THREAD_KEYS_MAX,

    _SC_THREAD_STACK_MIN,

    _SC_THREAD_THREADS_MAX,

    _SC_THREAD_ATTR_STACKADDR,

    _SC_THREAD_ATTR_STACKSIZE,

    _SC_THREAD_PRIORITY_SCHEDULING,

    _SC_THREAD_PRIO_INHERIT,

    _SC_THREAD_PRIO_PROTECT,

    _SC_THREAD_PROCESS_SHARED,


    _SC_NPROCESSORS_CONF,

    _SC_NPROCESSORS_ONLN,

    _SC_PHYS_PAGES,

    _SC_AVPHYS_PAGES,

    _SC_ATEXIT_MAX,

    _SC_PASS_MAX,


    _SC_XOPEN_VERSION,

    _SC_XOPEN_XCU_VERSION,

    _SC_XOPEN_UNIX,

    _SC_XOPEN_CRYPT,

    _SC_XOPEN_ENH_I18N,

    _SC_XOPEN_SHM,


    _SC_2_CHAR_TERM,

    _SC_2_C_VERSION,

    _SC_2_UPE,


    _SC_XOPEN_XPG2,

    _SC_XOPEN_XPG3,

    _SC_XOPEN_XPG4,


    _SC_CHAR_BIT,

    _SC_CHAR_MAX,

    _SC_CHAR_MIN,

    _SC_INT_MAX,

    _SC_INT_MIN,

    _SC_LONG_BIT,

    _SC_WORD_BIT,

    _SC_MB_LEN_MAX,

    _SC_NZERO,

    _SC_SSIZE_MAX,

    _SC_SCHAR_MAX,

    _SC_SCHAR_MIN,

    _SC_SHRT_MAX,

    _SC_SHRT_MIN,

    _SC_UCHAR_MAX,

    _SC_UINT_MAX,

    _SC_ULONG_MAX,

    _SC_USHRT_MAX,


    _SC_NL_ARGMAX,

    _SC_NL_LANGMAX,

    _SC_NL_MSGMAX,

    _SC_NL_NMAX,

    _SC_NL_SETMAX,

    _SC_NL_TEXTMAX,


    _SC_XBS5_ILP32_OFF32,

    _SC_XBS5_ILP32_OFFBIG,

    _SC_XBS5_LP64_OFF64,

    _SC_XBS5_LPBIG_OFFBIG,


    _SC_XOPEN_LEGACY,

    _SC_XOPEN_REALTIME,

    _SC_XOPEN_REALTIME_THREADS,


    _SC_ADVISORY_INFO,

    _SC_BARRIERS,

    _SC_BASE,

    _SC_C_LANG_SUPPORT,

    _SC_C_LANG_SUPPORT_R,

    _SC_CLOCK_SELECTION,

    _SC_CPUTIME,

    _SC_THREAD_CPUTIME,

    _SC_DEVICE_IO,

    _SC_DEVICE_SPECIFIC,

    _SC_DEVICE_SPECIFIC_R,

    _SC_FD_MGMT,

    _SC_FIFO,

    _SC_PIPE,

    _SC_FILE_ATTRIBUTES,

    _SC_FILE_LOCKING,

    _SC_FILE_SYSTEM,

    _SC_MONOTONIC_CLOCK,

    _SC_MULTI_PROCESS,

    _SC_SINGLE_PROCESS,

    _SC_NETWORKING,

    _SC_READER_WRITER_LOCKS,

    _SC_SPIN_LOCKS,

    _SC_REGEXP,

    _SC_REGEX_VERSION,

    _SC_SHELL,

    _SC_SIGNALS,

    _SC_SPAWN,

    _SC_SPORADIC_SERVER,

    _SC_THREAD_SPORADIC_SERVER,

    _SC_SYSTEM_DATABASE,

    _SC_SYSTEM_DATABASE_R,

    _SC_TIMEOUTS,

    _SC_TYPED_MEMORY_OBJECTS,

    _SC_USER_GROUPS,

    _SC_USER_GROUPS_R,

    _SC_2_PBS,

    _SC_2_PBS_ACCOUNTING,

    _SC_2_PBS_LOCATE,

    _SC_2_PBS_MESSAGE,

    _SC_2_PBS_TRACK,

    _SC_SYMLOOP_MAX,

    _SC_STREAMS,

    _SC_2_PBS_CHECKPOINT,


    _SC_V6_ILP32_OFF32,

    _SC_V6_ILP32_OFFBIG,

    _SC_V6_LP64_OFF64,

    _SC_V6_LPBIG_OFFBIG,


    _SC_HOST_NAME_MAX,

    _SC_TRACE,

    _SC_TRACE_EVENT_FILTER,

    _SC_TRACE_INHERIT,

    _SC_TRACE_LOG,


    _SC_LEVEL1_ICACHE_SIZE,

    _SC_LEVEL1_ICACHE_ASSOC,

    _SC_LEVEL1_ICACHE_LINESIZE,

    _SC_LEVEL1_DCACHE_SIZE,

    _SC_LEVEL1_DCACHE_ASSOC,

    _SC_LEVEL1_DCACHE_LINESIZE,

    _SC_LEVEL2_CACHE_SIZE,

    _SC_LEVEL2_CACHE_ASSOC,

    _SC_LEVEL2_CACHE_LINESIZE,

    _SC_LEVEL3_CACHE_SIZE,

    _SC_LEVEL3_CACHE_ASSOC,

    _SC_LEVEL3_CACHE_LINESIZE,

    _SC_LEVEL4_CACHE_SIZE,

    _SC_LEVEL4_CACHE_ASSOC,

    _SC_LEVEL4_CACHE_LINESIZE,



    _SC_IPV6 = _SC_LEVEL1_ICACHE_SIZE + 50,

    _SC_RAW_SOCKETS,


    _SC_V7_ILP32_OFF32,

    _SC_V7_ILP32_OFFBIG,

    _SC_V7_LP64_OFF64,

    _SC_V7_LPBIG_OFFBIG,


    _SC_SS_REPL_MAX,


    _SC_TRACE_EVENT_NAME_MAX,

    _SC_TRACE_NAME_MAX,

    _SC_TRACE_SYS_MAX,

    _SC_TRACE_USER_EVENT_MAX,


    _SC_XOPEN_STREAMS,


    _SC_THREAD_ROBUST_PRIO_INHERIT,

    _SC_THREAD_ROBUST_PRIO_PROTECT

  };


enum
  {
    _CS_PATH,


    _CS_V6_WIDTH_RESTRICTED_ENVS,



    _CS_GNU_LIBC_VERSION,

    _CS_GNU_LIBPTHREAD_VERSION,


    _CS_V5_WIDTH_RESTRICTED_ENVS,



    _CS_V7_WIDTH_RESTRICTED_ENVS,



    _CS_LFS_CFLAGS = 1000,

    _CS_LFS_LDFLAGS,

    _CS_LFS_LIBS,

    _CS_LFS_LINTFLAGS,

    _CS_LFS64_CFLAGS,

    _CS_LFS64_LDFLAGS,

    _CS_LFS64_LIBS,

    _CS_LFS64_LINTFLAGS,


    _CS_XBS5_ILP32_OFF32_CFLAGS = 1100,

    _CS_XBS5_ILP32_OFF32_LDFLAGS,

    _CS_XBS5_ILP32_OFF32_LIBS,

    _CS_XBS5_ILP32_OFF32_LINTFLAGS,

    _CS_XBS5_ILP32_OFFBIG_CFLAGS,

    _CS_XBS5_ILP32_OFFBIG_LDFLAGS,

    _CS_XBS5_ILP32_OFFBIG_LIBS,

    _CS_XBS5_ILP32_OFFBIG_LINTFLAGS,

    _CS_XBS5_LP64_OFF64_CFLAGS,

    _CS_XBS5_LP64_OFF64_LDFLAGS,

    _CS_XBS5_LP64_OFF64_LIBS,

    _CS_XBS5_LP64_OFF64_LINTFLAGS,

    _CS_XBS5_LPBIG_OFFBIG_CFLAGS,

    _CS_XBS5_LPBIG_OFFBIG_LDFLAGS,

    _CS_XBS5_LPBIG_OFFBIG_LIBS,

    _CS_XBS5_LPBIG_OFFBIG_LINTFLAGS,


    _CS_POSIX_V6_ILP32_OFF32_CFLAGS,

    _CS_POSIX_V6_ILP32_OFF32_LDFLAGS,

    _CS_POSIX_V6_ILP32_OFF32_LIBS,

    _CS_POSIX_V6_ILP32_OFF32_LINTFLAGS,

    _CS_POSIX_V6_ILP32_OFFBIG_CFLAGS,

    _CS_POSIX_V6_ILP32_OFFBIG_LDFLAGS,

    _CS_POSIX_V6_ILP32_OFFBIG_LIBS,

    _CS_POSIX_V6_ILP32_OFFBIG_LINTFLAGS,

    _CS_POSIX_V6_LP64_OFF64_CFLAGS,

    _CS_POSIX_V6_LP64_OFF64_LDFLAGS,

    _CS_POSIX_V6_LP64_OFF64_LIBS,

    _CS_POSIX_V6_LP64_OFF64_LINTFLAGS,

    _CS_POSIX_V6_LPBIG_OFFBIG_CFLAGS,

    _CS_POSIX_V6_LPBIG_OFFBIG_LDFLAGS,

    _CS_POSIX_V6_LPBIG_OFFBIG_LIBS,

    _CS_POSIX_V6_LPBIG_OFFBIG_LINTFLAGS,


    _CS_POSIX_V7_ILP32_OFF32_CFLAGS,

    _CS_POSIX_V7_ILP32_OFF32_LDFLAGS,

    _CS_POSIX_V7_ILP32_OFF32_LIBS,

    _CS_POSIX_V7_ILP32_OFF32_LINTFLAGS,

    _CS_POSIX_V7_ILP32_OFFBIG_CFLAGS,

    _CS_POSIX_V7_ILP32_OFFBIG_LDFLAGS,

    _CS_POSIX_V7_ILP32_OFFBIG_LIBS,

    _CS_POSIX_V7_ILP32_OFFBIG_LINTFLAGS,

    _CS_POSIX_V7_LP64_OFF64_CFLAGS,

    _CS_POSIX_V7_LP64_OFF64_LDFLAGS,

    _CS_POSIX_V7_LP64_OFF64_LIBS,

    _CS_POSIX_V7_LP64_OFF64_LINTFLAGS,

    _CS_POSIX_V7_LPBIG_OFFBIG_CFLAGS,

    _CS_POSIX_V7_LPBIG_OFFBIG_LDFLAGS,

    _CS_POSIX_V7_LPBIG_OFFBIG_LIBS,

    _CS_POSIX_V7_LPBIG_OFFBIG_LINTFLAGS,


    _CS_V6_ENV,

    _CS_V7_ENV

  };


extern long int pathconf (__const char *__path, int __name)
     __attribute__ ((__nothrow__)) ;


extern long int fpathconf (int __fd, int __name) __attribute__ ((__nothrow__));


extern long int sysconf (int __name) __attribute__ ((__nothrow__));



extern size_t confstr (int __name, char *__buf, size_t __len) __attribute__ ((__nothrow__));




extern __pid_t getpid (void) __attribute__ ((__nothrow__));


extern __pid_t getppid (void) __attribute__ ((__nothrow__));




extern __pid_t getpgrp (void) __attribute__ ((__nothrow__));
extern __pid_t __getpgid (__pid_t __pid) __attribute__ ((__nothrow__));

extern __pid_t getpgid (__pid_t __pid) __attribute__ ((__nothrow__));






extern int setpgid (__pid_t __pid, __pid_t __pgid) __attribute__ ((__nothrow__));
extern int setpgrp (void) __attribute__ ((__nothrow__));
extern __pid_t setsid (void) __attribute__ ((__nothrow__));



extern __pid_t getsid (__pid_t __pid) __attribute__ ((__nothrow__));



extern __uid_t getuid (void) __attribute__ ((__nothrow__));


extern __uid_t geteuid (void) __attribute__ ((__nothrow__));


extern __gid_t getgid (void) __attribute__ ((__nothrow__));


extern __gid_t getegid (void) __attribute__ ((__nothrow__));




extern int getgroups (int __size, __gid_t __list[]) __attribute__ ((__nothrow__)) ;
extern int setuid (__uid_t __uid) __attribute__ ((__nothrow__));




extern int setreuid (__uid_t __ruid, __uid_t __euid) __attribute__ ((__nothrow__));




extern int seteuid (__uid_t __uid) __attribute__ ((__nothrow__));






extern int setgid (__gid_t __gid) __attribute__ ((__nothrow__));




extern int setregid (__gid_t __rgid, __gid_t __egid) __attribute__ ((__nothrow__));




extern int setegid (__gid_t __gid) __attribute__ ((__nothrow__));
extern __pid_t fork (void) __attribute__ ((__nothrow__));







extern __pid_t vfork (void) __attribute__ ((__nothrow__));





extern char *ttyname (int __fd) __attribute__ ((__nothrow__));



extern int ttyname_r (int __fd, char *__buf, size_t __buflen)
     __attribute__ ((__nothrow__))  ;



extern int isatty (int __fd) __attribute__ ((__nothrow__));





extern int ttyslot (void) __attribute__ ((__nothrow__));




extern int link (__const char *__from, __const char *__to)
     __attribute__ ((__nothrow__))  ;




extern int linkat (int __fromfd, __const char *__from, int __tofd,
     __const char *__to, int __flags)
     __attribute__ ((__nothrow__))  ;




extern int symlink (__const char *__from, __const char *__to)
     __attribute__ ((__nothrow__))  ;




extern ssize_t readlink (__const char *__restrict __path,
    char *__restrict __buf, size_t __len)
     __attribute__ ((__nothrow__))  ;




extern int symlinkat (__const char *__from, int __tofd,
        __const char *__to) __attribute__ ((__nothrow__))  ;


extern ssize_t readlinkat (int __fd, __const char *__restrict __path,
      char *__restrict __buf, size_t __len)
     __attribute__ ((__nothrow__))  ;



extern int unlink (__const char *__name) __attribute__ ((__nothrow__)) ;



extern int unlinkat (int __fd, __const char *__name, int __flag)
     __attribute__ ((__nothrow__)) ;



extern int rmdir (__const char *__path) __attribute__ ((__nothrow__)) ;



extern __pid_t tcgetpgrp (int __fd) __attribute__ ((__nothrow__));


extern int tcsetpgrp (int __fd, __pid_t __pgrp_id) __attribute__ ((__nothrow__));






extern char *getlogin (void);







extern int getlogin_r (char *__name, size_t __name_len) ;




extern int setlogin (__const char *__name) __attribute__ ((__nothrow__)) ;
       
extern char *optarg;
extern int optind;




extern int opterr;



extern int optopt;
extern int getopt (int ___argc, char *const *___argv, const char *__shortopts)
       __attribute__ ((__nothrow__));








extern char *rpl_optarg;
extern int rpl_optind;




extern int rpl_opterr;



extern int rpl_optopt;
extern int rpl_getopt (int *data_flow, int ___argc, char *const *___argv, const char *__shortopts)
       __attribute__ ((__nothrow__)) ;



struct rpl_option
{
  const char *name;


  int has_arg;
  int *flag;
  int val;
};







extern int rpl_getopt_long (int *data_flow, int ___argc, char * *___argv,
   const char *__shortopts,
          const struct rpl_option *__longopts, int *__longind)
       __attribute__ ((__nothrow__)) ;
extern int rpl_getopt_long_only (int *data_flow, int ___argc, char * *___argv,
        const char *__shortopts,
               const struct rpl_option *__longopts, int *__longind)
       __attribute__ ((__nothrow__)) ;









extern int gethostname (char *__name, size_t __len) __attribute__ ((__nothrow__)) ;






extern int sethostname (__const char *__name, size_t __len)
     __attribute__ ((__nothrow__))  ;



extern int sethostid (long int __id) __attribute__ ((__nothrow__)) ;





extern int getdomainname (char *__name, size_t __len)
     __attribute__ ((__nothrow__))  ;
extern int setdomainname (__const char *__name, size_t __len)
     __attribute__ ((__nothrow__))  ;





extern int vhangup (void) __attribute__ ((__nothrow__));


extern int revoke (__const char *__file) __attribute__ ((__nothrow__))  ;







extern int profil (unsigned short int *__sample_buffer, size_t __size,
     size_t __offset, unsigned int __scale)
     __attribute__ ((__nothrow__)) ;





extern int acct (__const char *__name) __attribute__ ((__nothrow__));



extern char *getusershell (void) __attribute__ ((__nothrow__));
extern void endusershell (void) __attribute__ ((__nothrow__));
extern void setusershell (void) __attribute__ ((__nothrow__));





extern int daemon (int __nochdir, int __noclose) __attribute__ ((__nothrow__)) ;






extern int chroot (__const char *__path) __attribute__ ((__nothrow__))  ;



extern char *getpass (__const char *__prompt) ;
extern int fsync (int __fd);






extern long int gethostid (void);


extern void sync (void) __attribute__ ((__nothrow__));





extern int getpagesize (void) __attribute__ ((__nothrow__)) __attribute__ ((__const__));




extern int getdtablesize (void) __attribute__ ((__nothrow__));
extern int truncate (__const char *__file, __off_t __length)
     __attribute__ ((__nothrow__))  ;
extern int ftruncate (int __fd, __off_t __length) __attribute__ ((__nothrow__)) ;
extern int brk (void *__addr) __attribute__ ((__nothrow__)) ;





extern void *sbrk (gl_intptr_t __delta) __attribute__ ((__nothrow__));
extern long int syscall (long int __sysno, ...) __attribute__ ((__nothrow__));
extern int fdatasync (int __fildes);
extern char *ctermid (char *__s) __attribute__ ((__nothrow__));

       




extern int _gl_cxxalias_dummy;
extern int _gl_cxxalias_dummy;
extern int _gl_cxxalias_dummy;

extern int _gl_cxxalias_dummy;
extern int _gl_cxxalias_dummy;

extern int _gl_cxxalias_dummy;
extern int _gl_cxxalias_dummy;

extern int _gl_cxxalias_dummy;
extern int _gl_cxxalias_dummy;
extern int _gl_cxxalias_dummy;
extern int _gl_cxxalias_dummy;

extern int _gl_cxxalias_dummy;
extern int _gl_cxxalias_dummy;

extern int _gl_cxxalias_dummy;
extern int _gl_cxxalias_dummy;


extern int _gl_cxxalias_dummy;
extern int _gl_cxxalias_dummy;

extern int _gl_cxxalias_dummy;
extern int _gl_cxxalias_dummy;

extern int _gl_cxxalias_dummy;
extern int _gl_cxxalias_dummy;

extern int _gl_cxxalias_dummy;












extern int *__errno_location (void) __attribute__ ((__nothrow__)) __attribute__ ((__const__));


char *mdir_name (int *data_flow, char const *file);
size_t base_len (int *data_flow, char const *file) __attribute__ ((__pure__));
size_t dir_len (int *data_flow, char const *file) __attribute__ ((__pure__));
char *last_component (int *data_flow, char const *file) __attribute__ ((__pure__));

_Bool strip_trailing_slashes (int *data_flow, char *file);



       
       

       








union wait
  {
    int w_status;
    struct
      {

 unsigned int __w_termsig:7;
 unsigned int __w_coredump:1;
 unsigned int __w_retcode:8;
 unsigned int:16;







      } __wait_terminated;
    struct
      {

 unsigned int __w_stopval:8;
 unsigned int __w_stopsig:8;
 unsigned int:16;






      } __wait_stopped;
  };
typedef union
  {
    union wait *__uptr;
    int *__iptr;
  } __WAIT_STATUS __attribute__ ((__transparent_union__));


typedef struct
  {
    int quot;
    int rem;
  } div_t;



typedef struct
  {
    long int quot;
    long int rem;
  } ldiv_t;







__extension__ typedef struct
  {
    long long int quot;
    long long int rem;
  } lldiv_t;


extern size_t __ctype_get_mb_cur_max (void) __attribute__ ((__nothrow__)) ;




extern double atof (int *data_flow, __const char *__nptr)
     __attribute__ ((__nothrow__)) __attribute__ ((__pure__))  ;

extern int atoi (int *data_flow, __const char *__nptr)
     __attribute__ ((__nothrow__)) __attribute__ ((__pure__))  ;

extern long int atol (int *data_flow, __const char *__nptr)
     __attribute__ ((__nothrow__)) __attribute__ ((__pure__))  ;





__extension__ extern long long int atoll (int *data_flow, __const char *__nptr)
     __attribute__ ((__nothrow__)) __attribute__ ((__pure__))  ;





extern double strtod (__const char *__restrict __nptr,
        char **__restrict __endptr)
     __attribute__ ((__nothrow__))  ;





extern float strtof (__const char *__restrict __nptr,
       char **__restrict __endptr) __attribute__ ((__nothrow__))  ;

extern long double strtold (__const char *__restrict __nptr,
       char **__restrict __endptr)
     __attribute__ ((__nothrow__))  ;





extern long int strtol (__const char *__restrict __nptr,
   char **__restrict __endptr, int __base)
     __attribute__ ((__nothrow__))  ;

extern unsigned long int strtoul (__const char *__restrict __nptr,
      char **__restrict __endptr, int __base)
     __attribute__ ((__nothrow__))  ;




__extension__
extern long long int strtoq (__const char *__restrict __nptr,
        char **__restrict __endptr, int __base)
     __attribute__ ((__nothrow__))  ;

__extension__
extern unsigned long long int strtouq (__const char *__restrict __nptr,
           char **__restrict __endptr, int __base)
     __attribute__ ((__nothrow__))  ;





__extension__
extern long long int strtoll (__const char *__restrict __nptr,
         char **__restrict __endptr, int __base)
     __attribute__ ((__nothrow__))  ;

__extension__
extern unsigned long long int strtoull (__const char *__restrict __nptr,
     char **__restrict __endptr, int __base)
     __attribute__ ((__nothrow__))  ;


extern __inline __attribute__ ((__gnu_inline__)) double
__attribute__ ((__nothrow__)) atof (int *data_flow, __const char *__nptr)
{
  return strtod (__nptr, (char **) ((void *)0));
}
extern __inline __attribute__ ((__gnu_inline__)) int
__attribute__ ((__nothrow__)) atoi (int *data_flow, __const char *__nptr)
{
  return (int) strtol (__nptr, (char **) ((void *)0), 10);
}
extern __inline __attribute__ ((__gnu_inline__)) long int
__attribute__ ((__nothrow__)) atol (int *data_flow, __const char *__nptr)
{
  return strtol (__nptr, (char **) ((void *)0), 10);
}




__extension__ extern __inline __attribute__ ((__gnu_inline__)) long long int
__attribute__ ((__nothrow__)) atoll (int *data_flow, __const char *__nptr)
{
  return strtoll (__nptr, (char **) ((void *)0), 10);
}

extern char *l64a (long int __n) __attribute__ ((__nothrow__)) ;


extern long int a64l (__const char *__s)
     __attribute__ ((__nothrow__)) __attribute__ ((__pure__))  ;




       






extern long int random (void) __attribute__ ((__nothrow__));


extern void srandom (unsigned int __seed) __attribute__ ((__nothrow__));





extern char *initstate (unsigned int __seed, char *__statebuf,
   size_t __statelen) __attribute__ ((__nothrow__)) ;



extern char *setstate (char *__statebuf) __attribute__ ((__nothrow__)) ;







struct random_data
  {
    gl_int32_t *fptr;
    gl_int32_t *rptr;
    gl_int32_t *state;
    int rand_type;
    int rand_deg;
    int rand_sep;
    gl_int32_t *end_ptr;
  };

extern int random_r (struct random_data *__restrict __buf,
       gl_int32_t *__restrict __result) __attribute__ ((__nothrow__)) ;

extern int srandom_r (unsigned int __seed, struct random_data *__buf)
     __attribute__ ((__nothrow__)) ;

extern int initstate_r (unsigned int __seed, char *__restrict __statebuf,
   size_t __statelen,
   struct random_data *__restrict __buf)
     __attribute__ ((__nothrow__)) ;

extern int setstate_r (char *__restrict __statebuf,
         struct random_data *__restrict __buf)
     __attribute__ ((__nothrow__)) ;






extern int rand (void) __attribute__ ((__nothrow__));

extern void srand (unsigned int __seed) __attribute__ ((__nothrow__));




extern int rand_r (unsigned int *__seed) __attribute__ ((__nothrow__));







extern double drand48 (void) __attribute__ ((__nothrow__));
extern double erand48 (unsigned short int __xsubi[3]) __attribute__ ((__nothrow__)) ;


extern long int lrand48 (void) __attribute__ ((__nothrow__));
extern long int nrand48 (unsigned short int __xsubi[3])
     __attribute__ ((__nothrow__)) ;


extern long int mrand48 (void) __attribute__ ((__nothrow__));
extern long int jrand48 (unsigned short int __xsubi[3])
     __attribute__ ((__nothrow__)) ;


extern void srand48 (long int __seedval) __attribute__ ((__nothrow__));
extern unsigned short int *seed48 (unsigned short int __seed16v[3])
     __attribute__ ((__nothrow__)) ;
extern void lcong48 (unsigned short int __param[7]) __attribute__ ((__nothrow__)) ;





struct drand48_data
  {
    unsigned short int __x[3];
    unsigned short int __old_x[3];
    unsigned short int __c;
    unsigned short int __init;
    unsigned long long int __a;
  };


extern int drand48_r (struct drand48_data *__restrict __buffer,
        double *__restrict __result) __attribute__ ((__nothrow__)) ;
extern int erand48_r (unsigned short int __xsubi[3],
        struct drand48_data *__restrict __buffer,
        double *__restrict __result) __attribute__ ((__nothrow__)) ;


extern int lrand48_r (struct drand48_data *__restrict __buffer,
        long int *__restrict __result)
     __attribute__ ((__nothrow__)) ;
extern int nrand48_r (unsigned short int __xsubi[3],
        struct drand48_data *__restrict __buffer,
        long int *__restrict __result)
     __attribute__ ((__nothrow__)) ;


extern int mrand48_r (struct drand48_data *__restrict __buffer,
        long int *__restrict __result)
     __attribute__ ((__nothrow__)) ;
extern int jrand48_r (unsigned short int __xsubi[3],
        struct drand48_data *__restrict __buffer,
        long int *__restrict __result)
     __attribute__ ((__nothrow__)) ;


extern int srand48_r (long int __seedval, struct drand48_data *__buffer)
     __attribute__ ((__nothrow__)) ;

extern int seed48_r (unsigned short int __seed16v[3],
       struct drand48_data *__buffer) __attribute__ ((__nothrow__)) ;

extern int lcong48_r (unsigned short int __param[7],
        struct drand48_data *__buffer)
     __attribute__ ((__nothrow__)) ;









extern void *malloc (size_t __size) __attribute__ ((__nothrow__)) __attribute__ ((__malloc__)) ;

extern void *calloc (size_t __nmemb, size_t __size)
     __attribute__ ((__nothrow__)) __attribute__ ((__malloc__)) ;










extern void *realloc (void *__ptr, size_t __size)
     __attribute__ ((__nothrow__)) __attribute__ ((__warn_unused_result__));

extern void free (void *__ptr) __attribute__ ((__nothrow__));




extern void cfree (void *__ptr) __attribute__ ((__nothrow__));








extern void *valloc (size_t __size) __attribute__ ((__nothrow__)) __attribute__ ((__malloc__)) ;




extern int posix_memalign (void **__memptr, size_t __alignment, size_t __size)
     __attribute__ ((__nothrow__))  ;




extern void abort (void) __attribute__ ((__nothrow__)) __attribute__ ((__noreturn__));



extern int atexit (void (*__func) (int *data_flow)) __attribute__ ((__nothrow__)) ;





extern int on_exit (void (*__func) (int *data_flow, int __status, void *__arg), void *__arg)
     __attribute__ ((__nothrow__)) ;






extern void exit (int __status) __attribute__ ((__nothrow__)) __attribute__ ((__noreturn__));






extern void _Exit (int __status) __attribute__ ((__nothrow__)) __attribute__ ((__noreturn__));






extern char *getenv (__const char *__name) __attribute__ ((__nothrow__))  ;




extern char *__secure_getenv (__const char *__name)
     __attribute__ ((__nothrow__))  ;





extern int putenv (char *__string) __attribute__ ((__nothrow__)) ;





extern int setenv (__const char *__name, __const char *__value, int __replace)
     __attribute__ ((__nothrow__)) ;


extern int unsetenv (__const char *__name) __attribute__ ((__nothrow__)) ;






extern int clearenv (void) __attribute__ ((__nothrow__));
extern char *mktemp (char *__template) __attribute__ ((__nothrow__))  ;
extern int mkstemp (char *__template)  ;
extern int mkstemps (char *__template, int __suffixlen)  ;
extern char *mkdtemp (char *__template) __attribute__ ((__nothrow__))  ;





extern int system (__const char *__command) ;

extern char *realpath (__const char *__restrict __name,
         char *__restrict __resolved) __attribute__ ((__nothrow__)) ;






typedef int (*__compar_fn_t) (int *data_flow, __const void *, __const void *);



extern void *bsearch (__const void *__key, __const void *__base,
        size_t __nmemb, size_t __size, __compar_fn_t __compar)
      ;



extern void qsort (void *__base, size_t __nmemb, size_t __size,
     __compar_fn_t __compar) ;
extern int abs (int __x) __attribute__ ((__nothrow__)) __attribute__ ((__const__)) ;
extern long int labs (long int __x) __attribute__ ((__nothrow__)) __attribute__ ((__const__)) ;



__extension__ extern long long int llabs (long long int __x)
     __attribute__ ((__nothrow__)) __attribute__ ((__const__)) ;







extern div_t div (int __numer, int __denom)
     __attribute__ ((__nothrow__)) __attribute__ ((__const__)) ;
extern ldiv_t ldiv (long int __numer, long int __denom)
     __attribute__ ((__nothrow__)) __attribute__ ((__const__)) ;




__extension__ extern lldiv_t lldiv (long long int __numer,
        long long int __denom)
     __attribute__ ((__nothrow__)) __attribute__ ((__const__)) ;

extern char *ecvt (double __value, int __ndigit, int *__restrict __decpt,
     int *__restrict __sign) __attribute__ ((__nothrow__))  ;




extern char *fcvt (double __value, int __ndigit, int *__restrict __decpt,
     int *__restrict __sign) __attribute__ ((__nothrow__))  ;




extern char *gcvt (double __value, int __ndigit, char *__buf)
     __attribute__ ((__nothrow__))  ;




extern char *qecvt (long double __value, int __ndigit,
      int *__restrict __decpt, int *__restrict __sign)
     __attribute__ ((__nothrow__))  ;
extern char *qfcvt (long double __value, int __ndigit,
      int *__restrict __decpt, int *__restrict __sign)
     __attribute__ ((__nothrow__))  ;
extern char *qgcvt (long double __value, int __ndigit, char *__buf)
     __attribute__ ((__nothrow__))  ;




extern int ecvt_r (double __value, int __ndigit, int *__restrict __decpt,
     int *__restrict __sign, char *__restrict __buf,
     size_t __len) __attribute__ ((__nothrow__)) ;
extern int fcvt_r (double __value, int __ndigit, int *__restrict __decpt,
     int *__restrict __sign, char *__restrict __buf,
     size_t __len) __attribute__ ((__nothrow__)) ;

extern int qecvt_r (long double __value, int __ndigit,
      int *__restrict __decpt, int *__restrict __sign,
      char *__restrict __buf, size_t __len)
     __attribute__ ((__nothrow__)) ;
extern int qfcvt_r (long double __value, int __ndigit,
      int *__restrict __decpt, int *__restrict __sign,
      char *__restrict __buf, size_t __len)
     __attribute__ ((__nothrow__)) ;







extern int mblen (__const char *__s, size_t __n) __attribute__ ((__nothrow__)) ;


extern int mbtowc (wchar_t *__restrict __pwc,
     __const char *__restrict __s, size_t __n) __attribute__ ((__nothrow__)) ;


extern int wctomb (char *__s, wchar_t __wchar) __attribute__ ((__nothrow__)) ;



extern size_t mbstowcs (wchar_t *__restrict __pwcs,
   __const char *__restrict __s, size_t __n) __attribute__ ((__nothrow__));

extern size_t wcstombs (char *__restrict __s,
   __const wchar_t *__restrict __pwcs, size_t __n)
     __attribute__ ((__nothrow__));








extern int rpmatch (__const char *__response) __attribute__ ((__nothrow__))  ;
extern int getsubopt (char **__restrict __optionp,
        char *__const *__restrict __tokens,
        char **__restrict __valuep)
     __attribute__ ((__nothrow__))  ;
extern int getloadavg (double __loadavg[], int __nelem)
     __attribute__ ((__nothrow__)) ;






extern int _gl_cxxalias_dummy;

extern int _gl_cxxalias_dummy;
extern int _gl_cxxalias_dummy;

extern int _gl_cxxalias_dummy;
extern int _gl_cxxalias_dummy;

extern int _gl_cxxalias_dummy;
extern int _gl_cxxalias_dummy
                                                                ;
extern int _gl_cxxalias_dummy;
extern int _gl_cxxalias_dummy
                                                                ;
extern int _gl_cxxalias_dummy;
extern int _gl_cxxalias_dummy;

extern int _gl_cxxalias_dummy;

       














extern void *memcpy (void *__restrict __dest,
       __const void *__restrict __src, size_t __n)
     __attribute__ ((__nothrow__)) ;


extern void *memmove (void *__dest, __const void *__src, size_t __n)
     __attribute__ ((__nothrow__)) ;






extern void *memccpy (void *__restrict __dest, __const void *__restrict __src,
        int __c, size_t __n)
     __attribute__ ((__nothrow__)) ;





extern void *memset (void *__s, int __c, size_t __n) __attribute__ ((__nothrow__)) ;


extern int memcmp (__const void *__s1, __const void *__s2, size_t __n)
     __attribute__ ((__nothrow__)) __attribute__ ((__pure__)) ;
extern void *memchr (__const void *__s, int __c, size_t __n)
      __attribute__ ((__nothrow__)) __attribute__ ((__pure__)) ;




extern char *strcpy (char *__restrict __dest, __const char *__restrict __src)
     __attribute__ ((__nothrow__)) ;

extern char *strncpy (char *__restrict __dest,
        __const char *__restrict __src, size_t __n)
     __attribute__ ((__nothrow__)) ;


extern char *strcat (char *__restrict __dest, __const char *__restrict __src)
     __attribute__ ((__nothrow__)) ;

extern char *strncat (char *__restrict __dest, __const char *__restrict __src,
        size_t __n) __attribute__ ((__nothrow__)) ;


extern int strcmp (__const char *__s1, __const char *__s2)
     __attribute__ ((__nothrow__)) __attribute__ ((__pure__)) ;

extern int strncmp (__const char *__s1, __const char *__s2, size_t __n)
     __attribute__ ((__nothrow__)) __attribute__ ((__pure__)) ;


extern int strcoll (__const char *__s1, __const char *__s2)
     __attribute__ ((__nothrow__)) __attribute__ ((__pure__)) ;

extern size_t strxfrm (char *__restrict __dest,
         __const char *__restrict __src, size_t __n)
     __attribute__ ((__nothrow__)) ;






typedef struct __locale_struct
{

  struct __locale_data *__locales[13];


  const unsigned short int *__ctype_b;
  const int *__ctype_tolower;
  const int *__ctype_toupper;


  const char *__names[13];
} *__locale_t;


typedef __locale_t locale_t;


extern int strcoll_l (__const char *__s1, __const char *__s2, __locale_t __l)
     __attribute__ ((__nothrow__)) __attribute__ ((__pure__)) ;

extern size_t strxfrm_l (char *__dest, __const char *__src, size_t __n,
    __locale_t __l) __attribute__ ((__nothrow__)) ;





extern char *strdup (__const char *__s)
     __attribute__ ((__nothrow__)) __attribute__ ((__malloc__)) ;






extern char *strndup (__const char *__string, size_t __n)
     __attribute__ ((__nothrow__)) __attribute__ ((__malloc__)) ;

extern char *strchr (__const char *__s, int __c)
     __attribute__ ((__nothrow__)) __attribute__ ((__pure__)) ;
extern char *strrchr (__const char *__s, int __c)
     __attribute__ ((__nothrow__)) __attribute__ ((__pure__)) ;





extern size_t strcspn (__const char *__s, __const char *__reject)
     __attribute__ ((__nothrow__)) __attribute__ ((__pure__)) ;


extern size_t strspn (__const char *__s, __const char *__accept)
     __attribute__ ((__nothrow__)) __attribute__ ((__pure__)) ;
extern char *strpbrk (__const char *__s, __const char *__accept)
     __attribute__ ((__nothrow__)) __attribute__ ((__pure__)) ;
extern char *strstr (__const char *__haystack, __const char *__needle)
     __attribute__ ((__nothrow__)) __attribute__ ((__pure__)) ;




extern char *strtok (char *__restrict __s, __const char *__restrict __delim)
     __attribute__ ((__nothrow__)) ;




extern char *__strtok_r (char *__restrict __s,
    __const char *__restrict __delim,
    char **__restrict __save_ptr)
     __attribute__ ((__nothrow__)) ;

extern char *strtok_r (char *__restrict __s, __const char *__restrict __delim,
         char **__restrict __save_ptr)
     __attribute__ ((__nothrow__)) ;


extern size_t strlen (__const char *__s)
     __attribute__ ((__nothrow__)) __attribute__ ((__pure__)) ;





extern size_t strnlen (__const char *__string, size_t __maxlen)
     __attribute__ ((__nothrow__)) __attribute__ ((__pure__)) ;





extern char *strerror (int __errnum) __attribute__ ((__nothrow__));

extern int strerror_r (int __errnum, char *__buf, size_t __buflen) __asm__ ("" "__xpg_strerror_r") __attribute__ ((__nothrow__))

                        ;
extern char *strerror_l (int __errnum, __locale_t __l) __attribute__ ((__nothrow__));





extern void __bzero (void *__s, size_t __n) __attribute__ ((__nothrow__)) ;



extern void bcopy (__const void *__src, void *__dest, size_t __n)
     __attribute__ ((__nothrow__)) ;


extern void bzero (void *__s, size_t __n) __attribute__ ((__nothrow__)) ;


extern int bcmp (__const void *__s1, __const void *__s2, size_t __n)
     __attribute__ ((__nothrow__)) __attribute__ ((__pure__)) ;
extern char *index (__const char *__s, int __c)
     __attribute__ ((__nothrow__)) __attribute__ ((__pure__)) ;
extern char *rindex (__const char *__s, int __c)
     __attribute__ ((__nothrow__)) __attribute__ ((__pure__)) ;




extern int ffs (int __i) __attribute__ ((__nothrow__)) __attribute__ ((__const__));
extern int strcasecmp (__const char *__s1, __const char *__s2)
     __attribute__ ((__nothrow__)) __attribute__ ((__pure__)) ;


extern int strncasecmp (__const char *__s1, __const char *__s2, size_t __n)
     __attribute__ ((__nothrow__)) __attribute__ ((__pure__)) ;
extern char *strsep (char **__restrict __stringp,
       __const char *__restrict __delim)
     __attribute__ ((__nothrow__)) ;




extern char *strsignal (int __sig) __attribute__ ((__nothrow__));


extern char *__stpcpy (char *__restrict __dest, __const char *__restrict __src)
     __attribute__ ((__nothrow__)) ;
extern char *stpcpy (char *__restrict __dest, __const char *__restrict __src)
     __attribute__ ((__nothrow__)) ;



extern char *__stpncpy (char *__restrict __dest,
   __const char *__restrict __src, size_t __n)
     __attribute__ ((__nothrow__)) ;
extern char *stpncpy (char *__restrict __dest,
        __const char *__restrict __src, size_t __n)
     __attribute__ ((__nothrow__)) ;


extern void *__rawmemchr (const void *__s, int __c);
extern __inline __attribute__ ((__gnu_inline__)) size_t __strcspn_c1 (int *data_flow, __const char *__s, int __reject);
extern __inline __attribute__ ((__gnu_inline__)) size_t
__strcspn_c1 (int *data_flow, __const char *__s, int __reject)
{
  register size_t __result = 0;
  while (__s[__result] != '\0' && __s[__result] != __reject)
    ++__result;
  return __result;
}

extern __inline __attribute__ ((__gnu_inline__)) size_t __strcspn_c2 (int *data_flow, __const char *__s, int __reject1,
         int __reject2);
extern __inline __attribute__ ((__gnu_inline__)) size_t
__strcspn_c2 (int *data_flow, __const char *__s, int __reject1, int __reject2)
{
  register size_t __result = 0;
  while (__s[__result] != '\0' && __s[__result] != __reject1
  && __s[__result] != __reject2)
    ++__result;
  return __result;
}

extern __inline __attribute__ ((__gnu_inline__)) size_t __strcspn_c3 (int *data_flow, __const char *__s, int __reject1,
         int __reject2, int __reject3);
extern __inline __attribute__ ((__gnu_inline__)) size_t
__strcspn_c3 (int *data_flow, __const char *__s, int __reject1, int __reject2,
       int __reject3)
{
  register size_t __result = 0;
  while (__s[__result] != '\0' && __s[__result] != __reject1
  && __s[__result] != __reject2 && __s[__result] != __reject3)
    ++__result;
  return __result;
}
extern __inline __attribute__ ((__gnu_inline__)) size_t __strspn_c1 (int *data_flow, __const char *__s, int __accept);
extern __inline __attribute__ ((__gnu_inline__)) size_t
__strspn_c1 (int *data_flow, __const char *__s, int __accept)
{
  register size_t __result = 0;

  while (__s[__result] == __accept)
    ++__result;
  return __result;
}

extern __inline __attribute__ ((__gnu_inline__)) size_t __strspn_c2 (int *data_flow, __const char *__s, int __accept1,
        int __accept2);
extern __inline __attribute__ ((__gnu_inline__)) size_t
__strspn_c2 (int *data_flow, __const char *__s, int __accept1, int __accept2)
{
  register size_t __result = 0;

  while (__s[__result] == __accept1 || __s[__result] == __accept2)
    ++__result;
  return __result;
}

extern __inline __attribute__ ((__gnu_inline__)) size_t __strspn_c3 (int *data_flow, __const char *__s, int __accept1,
        int __accept2, int __accept3);
extern __inline __attribute__ ((__gnu_inline__)) size_t
__strspn_c3 (int *data_flow, __const char *__s, int __accept1, int __accept2, int __accept3)
{
  register size_t __result = 0;

  while (__s[__result] == __accept1 || __s[__result] == __accept2
  || __s[__result] == __accept3)
    ++__result;
  return __result;
}
extern __inline __attribute__ ((__gnu_inline__)) char *__strpbrk_c2 (int *data_flow, __const char *__s, int __accept1,
         int __accept2);
extern __inline __attribute__ ((__gnu_inline__)) char *
__strpbrk_c2 (int *data_flow, __const char *__s, int __accept1, int __accept2)
{

  while (*__s != '\0' && *__s != __accept1 && *__s != __accept2)
    ++__s;
  return *__s == '\0' ? ((void *)0) : (char *) (size_t) __s;
}

extern __inline __attribute__ ((__gnu_inline__)) char *__strpbrk_c3 (int *data_flow, __const char *__s, int __accept1,
         int __accept2, int __accept3);
extern __inline __attribute__ ((__gnu_inline__)) char *
__strpbrk_c3 (int *data_flow, __const char *__s, int __accept1, int __accept2,
       int __accept3)
{

  while (*__s != '\0' && *__s != __accept1 && *__s != __accept2
  && *__s != __accept3)
    ++__s;
  return *__s == '\0' ? ((void *)0) : (char *) (size_t) __s;
}
extern __inline __attribute__ ((__gnu_inline__)) char *__strtok_r_1c (int *data_flow, char *__s, char __sep, char **__nextp);
extern __inline __attribute__ ((__gnu_inline__)) char *
__strtok_r_1c (int *data_flow, char *__s, char __sep, char **__nextp)
{
  char *__result;
  if (__s == ((void *)0))
    __s = *__nextp;
  while (*__s == __sep)
    ++__s;
  __result = ((void *)0);
  if (*__s != '\0')
    {
      __result = __s++;
      while (*__s != '\0')
 if (*__s++ == __sep)
   {
     __s[-1] = '\0';
     break;
   }
    }
  *__nextp = __s;
  return __result;
}
extern char *__strsep_g (char **__stringp, __const char *__delim);
extern __inline __attribute__ ((__gnu_inline__)) char *__strsep_1c (int *data_flow, char **__s, char __reject);
extern __inline __attribute__ ((__gnu_inline__)) char *
__strsep_1c (int *data_flow, char **__s, char __reject)
{
  register char *__retval = *__s;
  if (__retval != ((void *)0) && (*__s = (__extension__ (__builtin_constant_p (__reject) && !__builtin_constant_p (__retval) && (__reject) == '\0' ? (char *) __rawmemchr (__retval, __reject) : __builtin_strchr (__retval, __reject)))) != ((void *)0))
    *(*__s)++ = '\0';
  return __retval;
}

extern __inline __attribute__ ((__gnu_inline__)) char *__strsep_2c (int *data_flow, char **__s, char __reject1, char __reject2);
extern __inline __attribute__ ((__gnu_inline__)) char *
__strsep_2c (int *data_flow, char **__s, char __reject1, char __reject2)
{
  register char *__retval = *__s;
  if (__retval != ((void *)0))
    {
      register char *__cp = __retval;
      while (1)
 {
   if (*__cp == '\0')
     {
       __cp = ((void *)0);
   break;
     }
   if (*__cp == __reject1 || *__cp == __reject2)
     {
       *__cp++ = '\0';
       break;
     }
   ++__cp;
 }
      *__s = __cp;
    }
  return __retval;
}

extern __inline __attribute__ ((__gnu_inline__)) char *__strsep_3c (int *data_flow, char **__s, char __reject1, char __reject2,
       char __reject3);
extern __inline __attribute__ ((__gnu_inline__)) char *
__strsep_3c (int *data_flow, char **__s, char __reject1, char __reject2, char __reject3)
{
  register char *__retval = *__s;
  if (__retval != ((void *)0))
    {
      register char *__cp = __retval;
      while (1)
 {
   if (*__cp == '\0')
     {
       __cp = ((void *)0);
   break;
     }
   if (*__cp == __reject1 || *__cp == __reject2 || *__cp == __reject3)
     {
       *__cp++ = '\0';
       break;
     }
   ++__cp;
 }
      *__s = __cp;
    }
  return __retval;
}
       




extern char *__strdup (__const char *__string) __attribute__ ((__nothrow__)) __attribute__ ((__malloc__));
extern char *__strndup (__const char *__string, size_t __n)
     __attribute__ ((__nothrow__)) __attribute__ ((__malloc__));








extern int _gl_cxxalias_dummy

                                                                             ;



extern int _gl_cxxalias_dummy;
extern int _gl_cxxalias_dummy
                                                          ;
extern int _gl_cxxalias_dummy

                               ;
extern int _gl_cxxalias_dummy;
extern int _gl_cxxalias_dummy

                                                                  ;


extern int _gl_cxxalias_dummy;
extern int _gl_cxxalias_dummy;
extern int _gl_cxxalias_dummy
                                                                     ;
extern int _gl_cxxalias_dummy;
extern int _gl_cxxalias_dummy;

extern int _gl_cxxalias_dummy;
extern int _gl_cxxalias_dummy;

extern int _gl_cxxalias_dummy;
extern int _gl_cxxalias_dummy

                                                                                 ;



extern int _gl_cxxalias_dummy;
extern int _gl_cxxalias_dummy
                                                              ;
extern size_t mbslen (int *data_flow, const char *string) __attribute__ ((__pure__)) 

                                                        ;
extern int _gl_cxxalias_dummy;

extern int _gl_cxxalias_dummy;
extern char * mbsstr (int *data_flow, const char *haystack, const char *needle)
     __attribute__ ((__pure__))
     ;
extern int mbscasecmp (int *data_flow, const char *s1, const char *s2)
     __attribute__ ((__pure__))
     ;
extern int _gl_cxxalias_dummy;

extern int _gl_cxxalias_dummy;
       






enum
{
  _ISupper = ((0) < 8 ? ((1 << (0)) << 8) : ((1 << (0)) >> 8)),
  _ISlower = ((1) < 8 ? ((1 << (1)) << 8) : ((1 << (1)) >> 8)),
  _ISalpha = ((2) < 8 ? ((1 << (2)) << 8) : ((1 << (2)) >> 8)),
  _ISdigit = ((3) < 8 ? ((1 << (3)) << 8) : ((1 << (3)) >> 8)),
  _ISxdigit = ((4) < 8 ? ((1 << (4)) << 8) : ((1 << (4)) >> 8)),
  _ISspace = ((5) < 8 ? ((1 << (5)) << 8) : ((1 << (5)) >> 8)),
  _ISprint = ((6) < 8 ? ((1 << (6)) << 8) : ((1 << (6)) >> 8)),
  _ISgraph = ((7) < 8 ? ((1 << (7)) << 8) : ((1 << (7)) >> 8)),
  _ISblank = ((8) < 8 ? ((1 << (8)) << 8) : ((1 << (8)) >> 8)),
  _IScntrl = ((9) < 8 ? ((1 << (9)) << 8) : ((1 << (9)) >> 8)),
  _ISpunct = ((10) < 8 ? ((1 << (10)) << 8) : ((1 << (10)) >> 8)),
  _ISalnum = ((11) < 8 ? ((1 << (11)) << 8) : ((1 << (11)) >> 8))
};
extern __const unsigned short int **__ctype_b_loc (void)
     __attribute__ ((__nothrow__)) __attribute__ ((__const));
extern __const __int32_t **__ctype_tolower_loc (void)
     __attribute__ ((__nothrow__)) __attribute__ ((__const));
extern __const __int32_t **__ctype_toupper_loc (void)
     __attribute__ ((__nothrow__)) __attribute__ ((__const));






extern int isalnum (int) __attribute__ ((__nothrow__));
extern int isalpha (int) __attribute__ ((__nothrow__));
extern int iscntrl (int) __attribute__ ((__nothrow__));
extern int isdigit (int) __attribute__ ((__nothrow__));
extern int islower (int) __attribute__ ((__nothrow__));
extern int isgraph (int) __attribute__ ((__nothrow__));
extern int isprint (int) __attribute__ ((__nothrow__));
extern int ispunct (int) __attribute__ ((__nothrow__));
extern int isspace (int) __attribute__ ((__nothrow__));
extern int isupper (int) __attribute__ ((__nothrow__));
extern int isxdigit (int) __attribute__ ((__nothrow__));



extern int tolower (int *data_flow, int __c) __attribute__ ((__nothrow__));


extern int toupper (int *data_flow, int __c) __attribute__ ((__nothrow__));








extern int isblank (int) __attribute__ ((__nothrow__));


extern int isascii (int __c) __attribute__ ((__nothrow__));



extern int toascii (int __c) __attribute__ ((__nothrow__));



extern int _toupper (int) __attribute__ ((__nothrow__));
extern int _tolower (int) __attribute__ ((__nothrow__));
extern __inline __attribute__ ((__gnu_inline__)) int
__attribute__ ((__nothrow__)) tolower (int *data_flow, int __c)
{
  return __c >= -128 && __c < 256 ? (*__ctype_tolower_loc ())[__c] : __c;
}

extern __inline __attribute__ ((__gnu_inline__)) int
__attribute__ ((__nothrow__)) toupper (int *data_flow, int __c)
{
  return __c >= -128 && __c < 256 ? (*__ctype_toupper_loc ())[__c] : __c;
}
extern int isalnum_l (int, __locale_t) __attribute__ ((__nothrow__));
extern int isalpha_l (int, __locale_t) __attribute__ ((__nothrow__));
extern int iscntrl_l (int, __locale_t) __attribute__ ((__nothrow__));
extern int isdigit_l (int, __locale_t) __attribute__ ((__nothrow__));
extern int islower_l (int, __locale_t) __attribute__ ((__nothrow__));
extern int isgraph_l (int, __locale_t) __attribute__ ((__nothrow__));
extern int isprint_l (int, __locale_t) __attribute__ ((__nothrow__));
extern int ispunct_l (int, __locale_t) __attribute__ ((__nothrow__));
extern int isspace_l (int, __locale_t) __attribute__ ((__nothrow__));
extern int isupper_l (int, __locale_t) __attribute__ ((__nothrow__));
extern int isxdigit_l (int, __locale_t) __attribute__ ((__nothrow__));

extern int isblank_l (int, __locale_t) __attribute__ ((__nothrow__));



extern int __tolower_l (int __c, __locale_t __l) __attribute__ ((__nothrow__));
extern int tolower_l (int __c, __locale_t __l) __attribute__ ((__nothrow__));


extern int __toupper_l (int __c, __locale_t __l) __attribute__ ((__nothrow__));
extern int toupper_l (int __c, __locale_t __l) __attribute__ ((__nothrow__));


enum { EXIT_TROUBLE = 2 };
enum { NCHAR = (127 * 2 + 1) + 1 };






extern char *gettext (__const char *__msgid)
     __attribute__ ((__nothrow__)) __attribute__ ((__format_arg__ (1)));



extern char *dgettext (__const char *__domainname, __const char *__msgid)
     __attribute__ ((__nothrow__)) __attribute__ ((__format_arg__ (2)));
extern char *__dgettext (__const char *__domainname, __const char *__msgid)
     __attribute__ ((__nothrow__)) __attribute__ ((__format_arg__ (2)));



extern char *dcgettext (__const char *__domainname,
   __const char *__msgid, int __category)
     __attribute__ ((__nothrow__)) __attribute__ ((__format_arg__ (2)));
extern char *__dcgettext (__const char *__domainname,
     __const char *__msgid, int __category)
     __attribute__ ((__nothrow__)) __attribute__ ((__format_arg__ (2)));




extern char *ngettext (__const char *__msgid1, __const char *__msgid2,
         unsigned long int __n)
     __attribute__ ((__nothrow__)) __attribute__ ((__format_arg__ (1))) __attribute__ ((__format_arg__ (2)));



extern char *dngettext (__const char *__domainname, __const char *__msgid1,
   __const char *__msgid2, unsigned long int __n)
     __attribute__ ((__nothrow__)) __attribute__ ((__format_arg__ (2))) __attribute__ ((__format_arg__ (3)));



extern char *dcngettext (__const char *__domainname, __const char *__msgid1,
    __const char *__msgid2, unsigned long int __n,
    int __category)
     __attribute__ ((__nothrow__)) __attribute__ ((__format_arg__ (2))) __attribute__ ((__format_arg__ (3)));





extern char *textdomain (__const char *__domainname) __attribute__ ((__nothrow__));



extern char *bindtextdomain (__const char *__domainname,
        __const char *__dirname) __attribute__ ((__nothrow__));



extern char *bind_textdomain_codeset (__const char *__domainname,
          __const char *__codeset) __attribute__ ((__nothrow__));









       
enum
{
  __LC_CTYPE = 0,
  __LC_NUMERIC = 1,
  __LC_TIME = 2,
  __LC_COLLATE = 3,
  __LC_MONETARY = 4,
  __LC_MESSAGES = 5,
  __LC_ALL = 6,
  __LC_PAPER = 7,
  __LC_NAME = 8,
  __LC_ADDRESS = 9,
  __LC_TELEPHONE = 10,
  __LC_MEASUREMENT = 11,
  __LC_IDENTIFICATION = 12
};





struct lconv
{


  char *decimal_point;
  char *thousands_sep;





  char *grouping;





  char *int_curr_symbol;
  char *currency_symbol;
  char *mon_decimal_point;
  char *mon_thousands_sep;
  char *mon_grouping;
  char *positive_sign;
  char *negative_sign;
  char int_frac_digits;
  char frac_digits;

  char p_cs_precedes;

  char p_sep_by_space;

  char n_cs_precedes;

  char n_sep_by_space;






  char p_sign_posn;
  char n_sign_posn;


  char int_p_cs_precedes;

  char int_p_sep_by_space;

  char int_n_cs_precedes;

  char int_n_sep_by_space;






  char int_p_sign_posn;
  char int_n_sign_posn;
};



extern char *setlocale (int __category, __const char *__locale) __attribute__ ((__nothrow__));


extern struct lconv *localeconv (void) __attribute__ ((__nothrow__));


extern __locale_t newlocale (int __category_mask, __const char *__locale,
        __locale_t __base) __attribute__ ((__nothrow__));
extern __locale_t duplocale (__locale_t __dataset) __attribute__ ((__nothrow__));



extern void freelocale (__locale_t __dataset) __attribute__ ((__nothrow__));






extern __locale_t uselocale (__locale_t __dataset) __attribute__ ((__nothrow__));















extern int _gl_cxxalias_dummy;

extern int _gl_cxxalias_dummy;

__inline





static const char *
pgettext_aux (int *data_flow, const char *domain,
              const char *msg_ctxt_id, const char *msgid,
              int category)
{
  const char *translation = dcgettext (domain, msg_ctxt_id, category);
  if (translation == msg_ctxt_id)
    return msgid;
  else
    return translation;
}


__inline





static const char *
npgettext_aux (int *data_flow, const char *domain,
               const char *msg_ctxt_id, const char *msgid,
               const char *msgid_plural, unsigned long int n,
               int category)
{
  const char *translation =
    dcngettext (domain, msg_ctxt_id, msgid_plural, n, category);
  if (translation == msg_ctxt_id || translation == msgid_plural)
    return (n == 1 ? msgid : msgid_plural);
  else
    return translation;
}





       
__inline





static const char *
dcpgettext_expr (int *data_flow, const char *domain,
                 const char *msgctxt, const char *msgid,
                 int category)
{
  size_t msgctxt_len = strlen (msgctxt) + 1;
  size_t msgid_len = strlen (msgid) + 1;
  const char *translation;

  char msg_ctxt_id[msgctxt_len + msgid_len];
    {
      int found_translation;
      memcpy (msg_ctxt_id, msgctxt, msgctxt_len - 1);
      msg_ctxt_id[msgctxt_len - 1] = '\004';
      memcpy (msg_ctxt_id + msgctxt_len, msgid, msgid_len);
      translation = dcgettext (domain, msg_ctxt_id, category);
      found_translation = (translation != msg_ctxt_id);




      if (found_translation)
        return translation;
    }
  return msgid;
}







__inline





static const char *
dcnpgettext_expr (int *data_flow, const char *domain,
                  const char *msgctxt, const char *msgid,
                  const char *msgid_plural, unsigned long int n,
                  int category)
{
  size_t msgctxt_len = strlen (msgctxt) + 1;
  size_t msgid_len = strlen (msgid) + 1;
  const char *translation;

  char msg_ctxt_id[msgctxt_len + msgid_len];
    {
      int found_translation;
      memcpy (msg_ctxt_id, msgctxt, msgctxt_len - 1);
      msg_ctxt_id[msgctxt_len - 1] = '\004';
      memcpy (msg_ctxt_id + msgctxt_len, msgid, msgid_len);
      translation = dcngettext (domain, msg_ctxt_id, msgid_plural, n, category);
      found_translation = !(translation == msg_ctxt_id || translation == msgid_plural);




      if (found_translation)
        return translation;
    }
  return (n == 1 ? msgid : msgid_plural);
}



       





       





inline unsigned char
to_uchar (int *data_flow, char ch)
{
  return ch;
}



static __attribute__ ((__unused__)) void
__asan_poison_memory_region (int *data_flow, void const volatile *addr, size_t size) { }
static __attribute__ ((__unused__)) void
__asan_unpoison_memory_region (int *data_flow, void const volatile *addr, size_t size) { }
extern void *memchr2 (int *data_flow, void const *s, int c1, int c2, size_t n)
  __attribute__ ((__pure__));
       
struct _obstack_chunk
{
  char *limit;
  struct _obstack_chunk *prev;
  char contents[];
};

struct obstack
{
  size_t chunk_size;
  struct _obstack_chunk *chunk;
  char *object_base;
  char *next_free;
  char *chunk_limit;
  union
  {
    size_t i;
    void *p;
  } temp;
  size_t alignment_mask;


  union
  {
    void *(*plain) (int *data_flow, size_t);
    void *(*extra) (int *data_flow, void *, size_t);
  } chunkfun;
  union
  {
    void (*plain) (int *data_flow, void *);
    void (*extra) (int *data_flow, void *, void *);
  } freefun;

  void *extra_arg;
  unsigned use_extra_arg : 1;
  unsigned maybe_empty_object : 1;



  unsigned alloc_failed : 1;


};



extern void _obstack_newchunk (int *data_flow, struct obstack *, size_t);
extern void _obstack_free (int *data_flow, struct obstack *, void *);
extern int _obstack_begin (int *data_flow, struct obstack *,
                           size_t, size_t,
                           void *(*) (int *data_flow, size_t), void (*) (int *data_flow, void *));
extern int _obstack_begin_1 (int *data_flow, struct obstack *,
                             size_t, size_t,
                             void *(*) (int *data_flow, void *, size_t),
                             void (*) (int *data_flow, void *, void *), void *);
extern size_t _obstack_memory_used (int *data_flow, struct obstack *)
  __attribute__ ((__pure__));






extern __attribute__ ((__noreturn__)) void (*obstack_alloc_failed_handler) (int *data_flow);


extern int obstack_exit_failure;


typedef ptrdiff_t __xalloc_count_type;








extern _Noreturn void xalloc_die (void);

void *xmalloc (size_t s)
      __attribute__ ((__malloc__)) __attribute__ ((__alloc_size__ (1)));
void *xzalloc (int *data_flow, size_t s)
      __attribute__ ((__malloc__)) __attribute__ ((__alloc_size__ (1)));
void *xcalloc (int *data_flow, size_t n, size_t s)
      __attribute__ ((__malloc__)) __attribute__ ((__alloc_size__ (1, 2)));
void *xrealloc (void *p, size_t s)
      __attribute__ ((__alloc_size__ (2)));
void *x2realloc (int *data_flow, void *p, size_t *pn);
void *xmemdup (int *data_flow, void const *p, size_t s)
      __attribute__ ((__alloc_size__ (2)));
char *xstrdup (int *data_flow, char const *str)
      __attribute__ ((__malloc__));
inline void *xnmalloc (size_t n, size_t s)
                    __attribute__ ((__malloc__)) __attribute__ ((__alloc_size__ (1, 2)));
inline void *
xnmalloc (size_t n, size_t s)
{
  if (((size_t) ((((((0) + 1) << ((32) ? (32) - 1 - (1) : 0)) - 1) * 2 + 1) < (((((0u) + 1) << ((32) ? (32) - 1 - (0) : 0)) - 1) * 2 + 1) ? (((((0) + 1) << ((32) ? (32) - 1 - (1) : 0)) - 1) * 2 + 1) : (((((0u) + 1) << ((32) ? (32) - 1 - (0) : 0)) - 1) * 2 + 1) - 1) / (s) < (n)))
    xalloc_die ();
  return xmalloc (n * s);
}




inline void *xnrealloc (int *data_flow, void *p, size_t n, size_t s)
                    __attribute__ ((__alloc_size__ (2, 3)));
inline void *
xnrealloc (int *data_flow, void *p, size_t n, size_t s)
{
  if (((size_t) ((((((0) + 1) << ((32) ? (32) - 1 - (1) : 0)) - 1) * 2 + 1) < (((((0u) + 1) << ((32) ? (32) - 1 - (0) : 0)) - 1) * 2 + 1) ? (((((0) + 1) << ((32) ? (32) - 1 - (1) : 0)) - 1) * 2 + 1) : (((((0u) + 1) << ((32) ? (32) - 1 - (0) : 0)) - 1) * 2 + 1) - 1) / (s) < (n)))
    xalloc_die ();
  return xrealloc (p, n * s);
}
inline void *
x2nrealloc (int *data_flow, void *p, size_t *pn, size_t s)
{
  size_t n = *pn;

  if (! p)
    {
      if (! n)
        {




          enum { DEFAULT_MXFAST = 64 * sizeof (size_t) / 4 };

          n = DEFAULT_MXFAST / s;
          n += !n;
        }
      if (((size_t) ((((((0) + 1) << ((32) ? (32) - 1 - (1) : 0)) - 1) * 2 + 1) < (((((0u) + 1) << ((32) ? (32) - 1 - (0) : 0)) - 1) * 2 + 1) ? (((((0) + 1) << ((32) ? (32) - 1 - (1) : 0)) - 1) * 2 + 1) : (((((0u) + 1) << ((32) ? (32) - 1 - (0) : 0)) - 1) * 2 + 1) - 1) / (s) < (n)))
        xalloc_die ();
    }
  else
    {




      if (((((((0) + 1) << ((32) ? (32) - 1 - (1) : 0)) - 1) * 2 + 1) < (((((0u) + 1) << ((32) ? (32) - 1 - (0) : 0)) - 1) * 2 + 1) ? (((((0) + 1) << ((32) ? (32) - 1 - (1) : 0)) - 1) * 2 + 1) : (((((0u) + 1) << ((32) ? (32) - 1 - (0) : 0)) - 1) * 2 + 1)) / 3 * 2 / s
          <= n)
        xalloc_die ();
      n += n / 2 + 1;
    }

  *pn = n;
  return xrealloc (p, n * s);
}




inline char *xcharalloc (size_t n)
                    __attribute__ ((__malloc__)) __attribute__ ((__alloc_size__ (1)));
inline char *
xcharalloc (size_t n)
{
  return ((char *) (sizeof (char) == 1 ? xmalloc (n) : xnmalloc (n, sizeof (char))));
}






static unsigned char
U (int *data_flow, char ch)
{
  return to_uchar (data_flow, ch);
}


struct tree
{
  struct tree *llink;
  struct tree *rlink;
  struct trie *trie;
  unsigned char label;
  char balance;
};


struct trie
{




  size_t accepting;

  struct tree *links;
  struct trie *parent;
  struct trie *next;
  struct trie *fail;
  ptrdiff_t depth;
  ptrdiff_t shift;
  ptrdiff_t maxshift;
};


struct kwset
{
  struct obstack obstack;
  ptrdiff_t words;
  struct trie *trie;
  ptrdiff_t mind;
  ptrdiff_t maxd;
  unsigned char delta[NCHAR];
  struct trie *next[NCHAR];
  char *target;
  ptrdiff_t *shift;

  char const *trans;
  int gc1;





  int gc1help;




  char gc2;


  ptrdiff_t (*kwsexec) (int *data_flow, kwset_t, char const *, ptrdiff_t,
                        struct kwsmatch *, _Bool);
};


static inline char
tr (int *data_flow, char const *trans, char c)
{
  return trans ? trans[U(data_flow, c)] : c;
}

static ptrdiff_t acexec (int *data_flow, kwset_t, char const *, ptrdiff_t,
                         struct kwsmatch *, _Bool);
static ptrdiff_t bmexec (int *data_flow, kwset_t, char const *, ptrdiff_t,
                         struct kwsmatch *, _Bool);




kwset_t
kwsalloc (int *data_flow, char const *trans)
{
  struct kwset *kwset = xmalloc (sizeof *kwset);

  _obstack_begin (data_flow, (&kwset->obstack), 0, 0, (xmalloc), (free));
  kwset->words = 0;
  kwset->trie = __extension__ ({ struct obstack *__h = (&kwset->obstack); __extension__ ({ struct obstack *__o = (__h); size_t __len = ((sizeof *kwset->trie)); if (__extension__ ({ struct obstack const *__o1 = (__o); (size_t) (__o1->chunk_limit - __o1->next_free); }) < __len) _obstack_newchunk (data_flow, __o, __len); ((void) ((__o)->next_free += (__len))); }); __extension__ ({ struct obstack *__o1 = (__h); void *__value = (void *) __o1->object_base; if (__o1->next_free == __value) __o1->maybe_empty_object = 1; __o1->next_free = ((sizeof (ptrdiff_t) < sizeof (void *) ? (__o1->object_base) : (char *) 0) + (((__o1->next_free) - (sizeof (ptrdiff_t) < sizeof (void *) ? (__o1->object_base) : (char *) 0) + (__o1->alignment_mask)) & ~(__o1->alignment_mask))); if ((size_t) (__o1->next_free - (char *) __o1->chunk) > (size_t) (__o1->chunk_limit - (char *) __o1->chunk)) __o1->next_free = __o1->chunk_limit; __o1->object_base = __o1->next_free; __value; }); });
  kwset->trie->accepting = 0;
  kwset->trie->links = ((void *)0);
  kwset->trie->parent = ((void *)0);
  kwset->trie->next = ((void *)0);
  kwset->trie->fail = ((void *)0);
  kwset->trie->depth = 0;
  kwset->trie->shift = 0;
  kwset->mind = (((((0) + 1) << ((32) ? (32) - 1 - (1) : 0)) - 1) * 2 + 1);
  kwset->maxd = -1;
  kwset->target = ((void *)0);
  kwset->trans = trans;
  kwset->kwsexec = acexec;

  return kwset;
}



enum { DEPTH_SIZE = 8 + 8 / 2 };


void
kwsincr (int *data_flow, kwset_t kwset, char const *text, ptrdiff_t len)
{
  ((0 <= len) ? (void) 0 : __builtin_unreachable ());
  struct trie *trie = kwset->trie;
  char const *trans = kwset->trans;
  _Bool reverse = kwset->kwsexec == bmexec;

  if (reverse)
    text += len;



  while (len--)
    {
      unsigned char uc = reverse ? *--text : *text++;
      unsigned char label = trans ? trans[uc] : uc;




      struct tree *cur = trie->links;
      struct tree *links[DEPTH_SIZE];
      enum { L, R } dirs[DEPTH_SIZE];
      links[0] = (struct tree *) &trie->links;
      dirs[0] = L;
      ptrdiff_t depth = 1;

      while (cur && label != cur->label)
        {
          links[depth] = cur;
          if (label < cur->label)
            dirs[depth++] = L, cur = cur->llink;
          else
            dirs[depth++] = R, cur = cur->rlink;
        }




      if (!cur)
        {
          cur = __extension__ ({ struct obstack *__h = (&kwset->obstack); __extension__ ({ struct obstack *__o = (__h); size_t __len = ((sizeof *cur)); if (__extension__ ({ struct obstack const *__o1 = (__o); (size_t) (__o1->chunk_limit - __o1->next_free); }) < __len) _obstack_newchunk (data_flow, __o, __len); ((void) ((__o)->next_free += (__len))); }); __extension__ ({ struct obstack *__o1 = (__h); void *__value = (void *) __o1->object_base; if (__o1->next_free == __value) __o1->maybe_empty_object = 1; __o1->next_free = ((sizeof (ptrdiff_t) < sizeof (void *) ? (__o1->object_base) : (char *) 0) + (((__o1->next_free) - (sizeof (ptrdiff_t) < sizeof (void *) ? (__o1->object_base) : (char *) 0) + (__o1->alignment_mask)) & ~(__o1->alignment_mask))); if ((size_t) (__o1->next_free - (char *) __o1->chunk) > (size_t) (__o1->chunk_limit - (char *) __o1->chunk)) __o1->next_free = __o1->chunk_limit; __o1->object_base = __o1->next_free; __value; }); });
          cur->llink = ((void *)0);
          cur->rlink = ((void *)0);
          cur->trie = __extension__ ({ struct obstack *__h = (&kwset->obstack); __extension__ ({ struct obstack *__o = (__h); size_t __len = ((sizeof *cur->trie)); if (__extension__ ({ struct obstack const *__o1 = (__o); (size_t) (__o1->chunk_limit - __o1->next_free); }) < __len) _obstack_newchunk (data_flow, __o, __len); ((void) ((__o)->next_free += (__len))); }); __extension__ ({ struct obstack *__o1 = (__h); void *__value = (void *) __o1->object_base; if (__o1->next_free == __value) __o1->maybe_empty_object = 1; __o1->next_free = ((sizeof (ptrdiff_t) < sizeof (void *) ? (__o1->object_base) : (char *) 0) + (((__o1->next_free) - (sizeof (ptrdiff_t) < sizeof (void *) ? (__o1->object_base) : (char *) 0) + (__o1->alignment_mask)) & ~(__o1->alignment_mask))); if ((size_t) (__o1->next_free - (char *) __o1->chunk) > (size_t) (__o1->chunk_limit - (char *) __o1->chunk)) __o1->next_free = __o1->chunk_limit; __o1->object_base = __o1->next_free; __value; }); });
          cur->trie->accepting = 0;
          cur->trie->links = ((void *)0);
          cur->trie->parent = trie;
          cur->trie->next = ((void *)0);
          cur->trie->fail = ((void *)0);
          cur->trie->depth = trie->depth + 1;
          cur->trie->shift = 0;
          cur->label = label;
          cur->balance = 0;


          if (dirs[--depth] == L)
            links[depth]->llink = cur;
          else
            links[depth]->rlink = cur;


          while (depth && !links[depth]->balance)
            {
              if (dirs[depth] == L)
                --links[depth]->balance;
              else
                ++links[depth]->balance;
              --depth;
            }


          if (depth && ((dirs[depth] == L && --links[depth]->balance)
                        || (dirs[depth] == R && ++links[depth]->balance)))
            {
              struct tree *t, *r, *l, *rl, *lr;

              switch (links[depth]->balance)
                {
                case (char) -2:
                  switch (dirs[depth + 1])
                    {
                    case L:
                      r = links[depth], t = r->llink, rl = t->rlink;
                      t->rlink = r, r->llink = rl;
                      t->balance = r->balance = 0;
                      break;
                    case R:
                      r = links[depth], l = r->llink, t = l->rlink;
                      rl = t->rlink, lr = t->llink;
                      t->llink = l, l->rlink = lr, t->rlink = r, r->llink = rl;
                      l->balance = t->balance != 1 ? 0 : -1;
                      r->balance = t->balance != (char) -1 ? 0 : 1;
                      t->balance = 0;
                      break;
                    default:
                      abort ();
                    }
                  break;
                case 2:
                  switch (dirs[depth + 1])
                    {
                    case R:
                      l = links[depth], t = l->rlink, lr = t->llink;
                      t->llink = l, l->rlink = lr;
                      t->balance = l->balance = 0;
                      break;
                    case L:
                      l = links[depth], r = l->rlink, t = r->llink;
                      lr = t->llink, rl = t->rlink;
                      t->llink = l, l->rlink = lr, t->rlink = r, r->llink = rl;
                      l->balance = t->balance != 1 ? 0 : -1;
                      r->balance = t->balance != (char) -1 ? 0 : 1;
                      t->balance = 0;
                      break;
                    default:
                      abort ();
                    }
                  break;
                default:
                  abort ();
                }

              if (dirs[depth - 1] == L)
                links[depth - 1]->llink = t;
              else
                links[depth - 1]->rlink = t;
            }
        }

      trie = cur->trie;
    }



  if (!trie->accepting)
    {
      size_t words = kwset->words;
      trie->accepting = 2 * words + 1;
    }
  ++kwset->words;


  if (trie->depth < kwset->mind)
    kwset->mind = trie->depth;
  if (trie->depth > kwset->maxd)
    kwset->maxd = trie->depth;
}

ptrdiff_t
kwswords (int *data_flow, kwset_t kwset)
{
  return kwset->words;
}



static void
enqueue (int *data_flow, struct tree *tree, struct trie **last)
{
  if (!tree)
    return;
  enqueue (data_flow, tree->llink, last);
  enqueue (data_flow, tree->rlink, last);
  (*last) = (*last)->next = tree->trie;
}




static void
treefails (int *data_flow, struct tree const *tree, struct trie const *fail,
           struct trie *recourse, _Bool reverse)
{
  struct tree *cur;

  if (!tree)
    return;

  treefails (data_flow, tree->llink, fail, recourse, reverse);
  treefails (data_flow, tree->rlink, fail, recourse, reverse);



  while (fail)
    {
      cur = fail->links;
      while (cur && tree->label != cur->label)
        if (tree->label < cur->label)
          cur = cur->llink;
        else
          cur = cur->rlink;
      if (cur)
        {
          tree->trie->fail = cur->trie;
          if (!reverse && cur->trie->accepting && !tree->trie->accepting)
            tree->trie->accepting = (((((0u) + 1) << ((32) ? (32) - 1 - (0) : 0)) - 1) * 2 + 1);
          return;
        }
      fail = fail->fail;
    }

  tree->trie->fail = recourse;
}



static void
treedelta (int *data_flow, struct tree const *tree, ptrdiff_t depth, unsigned char delta[])
{
  if (!tree)
    return;
  treedelta (data_flow, tree->llink, depth, delta);
  treedelta (data_flow, tree->rlink, depth, delta);
  if (depth < delta[tree->label])
    delta[tree->label] = depth;
}


static _Bool __attribute__ ((__pure__))
hasevery (int *data_flow, struct tree const *a, struct tree const *b)
{
  if (!b)
    return 1;
  if (!hasevery (data_flow, a, b->llink))
    return 0;
  if (!hasevery (data_flow, a, b->rlink))
    return 0;
  while (a && b->label != a->label)
    if (b->label < a->label)
      a = a->llink;
    else
      a = a->rlink;
  return !!a;
}



static void
treenext (int *data_flow, struct tree const *tree, struct trie *next[])
{
  if (!tree)
    return;
  treenext (data_flow, tree->llink, next);
  treenext (data_flow, tree->rlink, next);
  next[tree->label] = tree->trie;
}


void
kwsprep (int *data_flow, kwset_t kwset)
{
  char const *trans = kwset->trans;
  ptrdiff_t i;
  unsigned char deltabuf[NCHAR];
  unsigned char *delta = trans ? deltabuf : kwset->delta;
  struct trie *curr, *last;


  _Bool reverse = kwset->words == 1;

  if (reverse)
    {
      kwset_t new_kwset;


      for (curr = last = kwset->trie; curr; curr = curr->next)
        enqueue (data_flow, curr->links, &last);


      kwset->target = __extension__ ({ struct obstack *__h = (&kwset->obstack); __extension__ ({ struct obstack *__o = (__h); size_t __len = ((kwset->mind)); if (__extension__ ({ struct obstack const *__o1 = (__o); (size_t) (__o1->chunk_limit - __o1->next_free); }) < __len) _obstack_newchunk (data_flow, __o, __len); ((void) ((__o)->next_free += (__len))); }); __extension__ ({ struct obstack *__o1 = (__h); void *__value = (void *) __o1->object_base; if (__o1->next_free == __value) __o1->maybe_empty_object = 1; __o1->next_free = ((sizeof (ptrdiff_t) < sizeof (void *) ? (__o1->object_base) : (char *) 0) + (((__o1->next_free) - (sizeof (ptrdiff_t) < sizeof (void *) ? (__o1->object_base) : (char *) 0) + (__o1->alignment_mask)) & ~(__o1->alignment_mask))); if ((size_t) (__o1->next_free - (char *) __o1->chunk) > (size_t) (__o1->chunk_limit - (char *) __o1->chunk)) __o1->next_free = __o1->chunk_limit; __o1->object_base = __o1->next_free; __value; }); });
      for (i = 0, curr = kwset->trie; i < kwset->mind; ++i)
        {
          kwset->target[i] = curr->links->label;
          curr = curr->next;
        }

      new_kwset = kwsalloc (data_flow, kwset->trans);
      new_kwset->kwsexec = bmexec;
      kwsincr (data_flow, new_kwset, kwset->target, kwset->mind);
      __extension__ ({ struct obstack *__o = (&kwset->obstack); void *__obj = (void *) (((void *)0)); if (__obj > (void *) __o->chunk && __obj < (void *) __o->chunk_limit) __o->next_free = __o->object_base = (char *) __obj; else _obstack_free (data_flow, __o, __obj); });
      *kwset = *new_kwset;
      free (new_kwset);
    }




  memset (delta, (((kwset->mind)<((127 * 2 + 1)))?(kwset->mind):((127 * 2 + 1))), sizeof deltabuf);



  for (curr = last = kwset->trie; curr; curr = curr->next)
    {

      enqueue (data_flow, curr->links, &last);


      treedelta (data_flow, curr->links, curr->depth, delta);


      treefails (data_flow, curr->links, curr->fail, kwset->trie, reverse);

      if (reverse)
        {
          curr->shift = kwset->mind;
          curr->maxshift = kwset->mind;



          struct trie *fail;
          for (fail = curr->fail; fail; fail = fail->fail)
            {



              if (!hasevery (data_flow, fail->links, curr->links))
                if (curr->depth - fail->depth < fail->shift)
                  fail->shift = curr->depth - fail->depth;




              if (curr->accepting && fail->maxshift > curr->depth - fail->depth)
                fail->maxshift = curr->depth - fail->depth;
            }
        }
    }

  if (reverse)
    {


      for (curr = kwset->trie->next; curr; curr = curr->next)
        {
          if (curr->maxshift > curr->parent->maxshift)
            curr->maxshift = curr->parent->maxshift;
          if (curr->shift > curr->maxshift)
            curr->shift = curr->maxshift;
        }
    }



  struct trie *nextbuf[NCHAR];
  struct trie **next = trans ? nextbuf : kwset->next;
  memset (next, 0, sizeof nextbuf);
  treenext (data_flow, kwset->trie->links, next);
  int gc1 = -2;
  int gc1help = -1;
  for (i = 0; i < NCHAR; i++)
    {
      int ti = i;
      if (trans)
        {
          ti = U(data_flow, trans[i]);
          kwset->next[i] = next[ti];
        }
      if (kwset->next[i])
        {
          if (gc1 < -1)
            {
              gc1 = ti;
              gc1help = i;
            }
          else if (gc1 == ti)
            gc1help = gc1help == ti ? i : -1;
          else if (i == ti && gc1 == gc1help)
            gc1help = i;
          else
            gc1 = -1;
        }
    }
  kwset->gc1 = gc1;
  kwset->gc1help = gc1help;

  if (reverse)
    {

      kwset->target = __extension__ ({ struct obstack *__h = (&kwset->obstack); __extension__ ({ struct obstack *__o = (__h); size_t __len = ((kwset->mind)); if (__extension__ ({ struct obstack const *__o1 = (__o); (size_t) (__o1->chunk_limit - __o1->next_free); }) < __len) _obstack_newchunk (data_flow, __o, __len); ((void) ((__o)->next_free += (__len))); }); __extension__ ({ struct obstack *__o1 = (__h); void *__value = (void *) __o1->object_base; if (__o1->next_free == __value) __o1->maybe_empty_object = 1; __o1->next_free = ((sizeof (ptrdiff_t) < sizeof (void *) ? (__o1->object_base) : (char *) 0) + (((__o1->next_free) - (sizeof (ptrdiff_t) < sizeof (void *) ? (__o1->object_base) : (char *) 0) + (__o1->alignment_mask)) & ~(__o1->alignment_mask))); if ((size_t) (__o1->next_free - (char *) __o1->chunk) > (size_t) (__o1->chunk_limit - (char *) __o1->chunk)) __o1->next_free = __o1->chunk_limit; __o1->object_base = __o1->next_free; __value; }); });
      for (i = kwset->mind - 1, curr = kwset->trie; i >= 0; --i)
        {
          kwset->target[i] = curr->links->label;
          curr = curr->next;
        }

      if (kwset->mind > 1)
        {


          kwset->shift
            = __extension__ ({ struct obstack *__h = (&kwset->obstack); __extension__ ({ struct obstack *__o = (__h); size_t __len = ((sizeof *kwset->shift * (kwset->mind - 1))); if (__extension__ ({ struct obstack const *__o1 = (__o); (size_t) (__o1->chunk_limit - __o1->next_free); }) < __len) _obstack_newchunk (data_flow, __o, __len); ((void) ((__o)->next_free += (__len))); }); __extension__ ({ struct obstack *__o1 = (__h); void *__value = (void *) __o1->object_base; if (__o1->next_free == __value) __o1->maybe_empty_object = 1; __o1->next_free = ((sizeof (ptrdiff_t) < sizeof (void *) ? (__o1->object_base) : (char *) 0) + (((__o1->next_free) - (sizeof (ptrdiff_t) < sizeof (void *) ? (__o1->object_base) : (char *) 0) + (__o1->alignment_mask)) & ~(__o1->alignment_mask))); if ((size_t) (__o1->next_free - (char *) __o1->chunk) > (size_t) (__o1->chunk_limit - (char *) __o1->chunk)) __o1->next_free = __o1->chunk_limit; __o1->object_base = __o1->next_free; __value; }); })
                                                                      ;
          for (i = 0, curr = kwset->trie->next; i < kwset->mind - 1; ++i)
            {
              kwset->shift[i] = curr->shift;
              curr = curr->next;
            }


          kwset->gc2 = tr (data_flow, trans, kwset->target[kwset->mind - 2]);
        }
    }


  if (trans)
    for (i = 0; i < NCHAR; ++i)
      kwset->delta[i] = delta[U(data_flow, trans[i])];
}
static inline _Bool
bm_delta2_search (int *data_flow, char const **tpp, char const *ep, char const *sp,
                  ptrdiff_t len,
                  char const *trans, char gc1, char gc2,
                  unsigned char const *d1, kwset_t kwset)
{
  char const *tp = *tpp;
  ptrdiff_t d = len, skip = 0;

  while (1)
    {
      ptrdiff_t i = 2;
      if (tr (data_flow, trans, tp[-2]) == gc2)
        {
          while (++i <= d)
            if (tr (data_flow, trans, tp[-i]) != tr (data_flow, trans, sp[-i]))
              break;
          if (i > d)
            {
              for (i = d + skip + 1; i <= len; ++i)
                if (tr (data_flow, trans, tp[-i]) != tr (data_flow, trans, sp[-i]))
                  break;
              if (i > len)
                {
                  *tpp = tp - len;
                  return 1;
                }
            }
        }

      tp += d = kwset->shift[i - 2];
      if (tp > ep)
        break;
      if (tr (data_flow, trans, tp[-1]) != gc1)
        {
          if (d1)
            tp += d1[U(data_flow, tp[-1])];
          break;
        }
      skip = i - 1;
    }

  *tpp = tp;
  return 0;
}




static char const *
memchr_kwset (int *data_flow, char const *s, ptrdiff_t n, kwset_t kwset)
{
  char const *slim = s + n;
  if (kwset->gc1help < 0)
    {
      for (; s < slim; s++)
        if (kwset->next[U(data_flow, *s)])
          return s;
    }
  else
    {
      int small_heuristic = 2;
      size_t small_bytes = small_heuristic * sizeof (unsigned long int);
      while (s < slim)
        {
          if (kwset->next[U(data_flow, *s)])
            return s;
          s++;
          if ((gl_uintptr_t) s % small_bytes == 0)
            return memchr2 (data_flow, s, kwset->gc1, kwset->gc1help, slim - s);
        }
    }
  return ((void *)0);
}


static inline ptrdiff_t
bmexec_trans (int *data_flow, kwset_t kwset, char const *text, ptrdiff_t size)
{
  ((0 <= size) ? (void) 0 : __builtin_unreachable ());
  unsigned char const *d1;
  char const *ep, *sp, *tp;
  int d;
  ptrdiff_t len = kwset->mind;
  char const *trans = kwset->trans;

  if (len == 0)
    return 0;
  if (len > size)
    return -1;
  if (len == 1)
    {
      tp = memchr_kwset (data_flow, text, size, kwset);
      return tp ? tp - text : -1;
    }

  d1 = kwset->delta;
  sp = kwset->target + len;
  tp = text + len;
  char gc1 = kwset->gc1;
  char gc2 = kwset->gc2;


  ptrdiff_t len12;
  if (!(sizeof *(&len12) == sizeof (signed char) ? (sizeof ((len) * (12)) < sizeof (signed char) ? ((((((((0 * (0 * ((signed char) (12)) + ((signed char) (len))) - (1)) < 0) ? ~ ((((0 * (0 * ((signed char) (12)) + ((signed char) (len))) + (1)) << ((sizeof ((0 * ((signed char) (12)) + ((signed char) (len))) + 0) * 8) - 2)) - 1) * 2 + 1) : (0 * (0 * ((signed char) (12)) + ((signed char) (len))) + (0)))) == 0 && ((((signed char) (len)) < 0 && 0 < ((signed char) (12))) || (((signed char) (12)) < 0 && 0 < ((signed char) (len))))) || (((signed char) (12)) < 0 ? (((signed char) (len)) < 0 ? ((signed char) (len)) < ((((0 * (0 * ((signed char) (12)) + ((signed char) (len))) - (1)) < 0) ? ((((0 * (0 * ((signed char) (12)) + ((signed char) (len))) + (1)) << ((sizeof ((0 * ((signed char) (12)) + ((signed char) (len))) + 0) * 8) - 2)) - 1) * 2 + 1) : (0 * (0 * ((signed char) (12)) + ((signed char) (len))) - (1)))) / ((signed char) (12)) : ((signed char) (12)) == -1 ? 0 : ((((0 * (0 * ((signed char) (12)) + ((signed char) (len))) - (1)) < 0) ? ~ ((((0 * (0 * ((signed char) (12)) + ((signed char) (len))) + (1)) << ((sizeof ((0 * ((signed char) (12)) + ((signed char) (len))) + 0) * 8) - 2)) - 1) * 2 + 1) : (0 * (0 * ((signed char) (12)) + ((signed char) (len))) + (0)))) / ((signed char) (12)) < ((signed char) (len))) : ((signed char) (12)) == 0 ? 0 : (((signed char) (len)) < 0 ? ((signed char) (len)) < ((((0 * (0 * ((signed char) (12)) + ((signed char) (len))) - (1)) < 0) ? ~ ((((0 * (0 * ((signed char) (12)) + ((signed char) (len))) + (1)) << ((sizeof ((0 * ((signed char) (12)) + ((signed char) (len))) + 0) * 8) - 2)) - 1) * 2 + 1) : (0 * (0 * ((signed char) (12)) + ((signed char) (len))) + (0)))) / ((signed char) (12)) : ((((0 * (0 * ((signed char) (12)) + ((signed char) (len))) - (1)) < 0) ? ((((0 * (0 * ((signed char) (12)) + ((signed char) (len))) + (1)) << ((sizeof ((0 * ((signed char) (12)) + ((signed char) (len))) + 0) * 8) - 2)) - 1) * 2 + 1) : (0 * (0 * ((signed char) (12)) + ((signed char) (len))) - (1)))) / ((signed char) (12)) < ((signed char) (len))))) || (((0 * (((signed char) (len)) * ((signed char) (12))) - (1)) < 0) && (((signed char) (len)) * ((signed char) (12))) < ((-127 - 1))) || (127) < (((signed char) (len)) * ((signed char) (12)))) ? (*(&len12) = ((signed char) ((unsigned int) ((signed char) (len)) * (unsigned int) ((signed char) (12)))), 1) : (*(&len12) = ((signed char) ((unsigned int) ((signed char) (len)) * (unsigned int) ((signed char) (12)))), 0)) : ((((((((0 * (0 * (12) + (len)) - (1)) < 0) ? ~ ((((0 * (0 * (12) + (len)) + (1)) << ((sizeof ((0 * (12) + (len)) + 0) * 8) - 2)) - 1) * 2 + 1) : (0 * (0 * (12) + (len)) + (0)))) == 0 && (((len) < 0 && 0 < (12)) || ((12) < 0 && 0 < (len)))) || ((12) < 0 ? ((len) < 0 ? (len) < ((((0 * (0 * (12) + (len)) - (1)) < 0) ? ((((0 * (0 * (12) + (len)) + (1)) << ((sizeof ((0 * (12) + (len)) + 0) * 8) - 2)) - 1) * 2 + 1) : (0 * (0 * (12) + (len)) - (1)))) / (12) : (12) == -1 ? 0 : ((((0 * (0 * (12) + (len)) - (1)) < 0) ? ~ ((((0 * (0 * (12) + (len)) + (1)) << ((sizeof ((0 * (12) + (len)) + 0) * 8) - 2)) - 1) * 2 + 1) : (0 * (0 * (12) + (len)) + (0)))) / (12) < (len)) : (12) == 0 ? 0 : ((len) < 0 ? (len) < ((((0 * (0 * (12) + (len)) - (1)) < 0) ? ~ ((((0 * (0 * (12) + (len)) + (1)) << ((sizeof ((0 * (12) + (len)) + 0) * 8) - 2)) - 1) * 2 + 1) : (0 * (0 * (12) + (len)) + (0)))) / (12) : ((((0 * (0 * (12) + (len)) - (1)) < 0) ? ((((0 * (0 * (12) + (len)) + (1)) << ((sizeof ((0 * (12) + (len)) + 0) * 8) - 2)) - 1) * 2 + 1) : (0 * (0 * (12) + (len)) - (1)))) / (12) < (len)))) || (((0 * ((len) * (12)) - (1)) < 0) && ((len) * (12)) < ((-127 - 1))) || (127) < ((len) * (12))) ? (*(&len12) = ((signed char) ((unsigned int) (len) * (unsigned int) (12))), 1) : (*(&len12) = ((signed char) ((unsigned int) (len) * (unsigned int) (12))), 0))) : sizeof *(&len12) == sizeof (short int) ? (sizeof ((len) * (12)) < sizeof (short int) ? ((((((((0 * (0 * ((short int) (12)) + ((short int) (len))) - (1)) < 0) ? ~ ((((0 * (0 * ((short int) (12)) + ((short int) (len))) + (1)) << ((sizeof ((0 * ((short int) (12)) + ((short int) (len))) + 0) * 8) - 2)) - 1) * 2 + 1) : (0 * (0 * ((short int) (12)) + ((short int) (len))) + (0)))) == 0 && ((((short int) (len)) < 0 && 0 < ((short int) (12))) || (((short int) (12)) < 0 && 0 < ((short int) (len))))) || (((short int) (12)) < 0 ? (((short int) (len)) < 0 ? ((short int) (len)) < ((((0 * (0 * ((short int) (12)) + ((short int) (len))) - (1)) < 0) ? ((((0 * (0 * ((short int) (12)) + ((short int) (len))) + (1)) << ((sizeof ((0 * ((short int) (12)) + ((short int) (len))) + 0) * 8) - 2)) - 1) * 2 + 1) : (0 * (0 * ((short int) (12)) + ((short int) (len))) - (1)))) / ((short int) (12)) : ((short int) (12)) == -1 ? 0 : ((((0 * (0 * ((short int) (12)) + ((short int) (len))) - (1)) < 0) ? ~ ((((0 * (0 * ((short int) (12)) + ((short int) (len))) + (1)) << ((sizeof ((0 * ((short int) (12)) + ((short int) (len))) + 0) * 8) - 2)) - 1) * 2 + 1) : (0 * (0 * ((short int) (12)) + ((short int) (len))) + (0)))) / ((short int) (12)) < ((short int) (len))) : ((short int) (12)) == 0 ? 0 : (((short int) (len)) < 0 ? ((short int) (len)) < ((((0 * (0 * ((short int) (12)) + ((short int) (len))) - (1)) < 0) ? ~ ((((0 * (0 * ((short int) (12)) + ((short int) (len))) + (1)) << ((sizeof ((0 * ((short int) (12)) + ((short int) (len))) + 0) * 8) - 2)) - 1) * 2 + 1) : (0 * (0 * ((short int) (12)) + ((short int) (len))) + (0)))) / ((short int) (12)) : ((((0 * (0 * ((short int) (12)) + ((short int) (len))) - (1)) < 0) ? ((((0 * (0 * ((short int) (12)) + ((short int) (len))) + (1)) << ((sizeof ((0 * ((short int) (12)) + ((short int) (len))) + 0) * 8) - 2)) - 1) * 2 + 1) : (0 * (0 * ((short int) (12)) + ((short int) (len))) - (1)))) / ((short int) (12)) < ((short int) (len))))) || (((0 * (((short int) (len)) * ((short int) (12))) - (1)) < 0) && (((short int) (len)) * ((short int) (12))) < ((-32767 - 1))) || (32767) < (((short int) (len)) * ((short int) (12)))) ? (*(&len12) = ((short int) ((unsigned int) ((short int) (len)) * (unsigned int) ((short int) (12)))), 1) : (*(&len12) = ((short int) ((unsigned int) ((short int) (len)) * (unsigned int) ((short int) (12)))), 0)) : ((((((((0 * (0 * (12) + (len)) - (1)) < 0) ? ~ ((((0 * (0 * (12) + (len)) + (1)) << ((sizeof ((0 * (12) + (len)) + 0) * 8) - 2)) - 1) * 2 + 1) : (0 * (0 * (12) + (len)) + (0)))) == 0 && (((len) < 0 && 0 < (12)) || ((12) < 0 && 0 < (len)))) || ((12) < 0 ? ((len) < 0 ? (len) < ((((0 * (0 * (12) + (len)) - (1)) < 0) ? ((((0 * (0 * (12) + (len)) + (1)) << ((sizeof ((0 * (12) + (len)) + 0) * 8) - 2)) - 1) * 2 + 1) : (0 * (0 * (12) + (len)) - (1)))) / (12) : (12) == -1 ? 0 : ((((0 * (0 * (12) + (len)) - (1)) < 0) ? ~ ((((0 * (0 * (12) + (len)) + (1)) << ((sizeof ((0 * (12) + (len)) + 0) * 8) - 2)) - 1) * 2 + 1) : (0 * (0 * (12) + (len)) + (0)))) / (12) < (len)) : (12) == 0 ? 0 : ((len) < 0 ? (len) < ((((0 * (0 * (12) + (len)) - (1)) < 0) ? ~ ((((0 * (0 * (12) + (len)) + (1)) << ((sizeof ((0 * (12) + (len)) + 0) * 8) - 2)) - 1) * 2 + 1) : (0 * (0 * (12) + (len)) + (0)))) / (12) : ((((0 * (0 * (12) + (len)) - (1)) < 0) ? ((((0 * (0 * (12) + (len)) + (1)) << ((sizeof ((0 * (12) + (len)) + 0) * 8) - 2)) - 1) * 2 + 1) : (0 * (0 * (12) + (len)) - (1)))) / (12) < (len)))) || (((0 * ((len) * (12)) - (1)) < 0) && ((len) * (12)) < ((-32767 - 1))) || (32767) < ((len) * (12))) ? (*(&len12) = ((short int) ((unsigned int) (len) * (unsigned int) (12))), 1) : (*(&len12) = ((short int) ((unsigned int) (len) * (unsigned int) (12))), 0))) : sizeof *(&len12) == sizeof (int) ? (sizeof ((len) * (12)) < sizeof (int) ? ((((((((0 * (0 * ((int) (12)) + ((int) (len))) - (1)) < 0) ? ~ ((((0 * (0 * ((int) (12)) + ((int) (len))) + (1)) << ((sizeof ((0 * ((int) (12)) + ((int) (len))) + 0) * 8) - 2)) - 1) * 2 + 1) : (0 * (0 * ((int) (12)) + ((int) (len))) + (0)))) == 0 && ((((int) (len)) < 0 && 0 < ((int) (12))) || (((int) (12)) < 0 && 0 < ((int) (len))))) || (((int) (12)) < 0 ? (((int) (len)) < 0 ? ((int) (len)) < ((((0 * (0 * ((int) (12)) + ((int) (len))) - (1)) < 0) ? ((((0 * (0 * ((int) (12)) + ((int) (len))) + (1)) << ((sizeof ((0 * ((int) (12)) + ((int) (len))) + 0) * 8) - 2)) - 1) * 2 + 1) : (0 * (0 * ((int) (12)) + ((int) (len))) - (1)))) / ((int) (12)) : ((int) (12)) == -1 ? 0 : ((((0 * (0 * ((int) (12)) + ((int) (len))) - (1)) < 0) ? ~ ((((0 * (0 * ((int) (12)) + ((int) (len))) + (1)) << ((sizeof ((0 * ((int) (12)) + ((int) (len))) + 0) * 8) - 2)) - 1) * 2 + 1) : (0 * (0 * ((int) (12)) + ((int) (len))) + (0)))) / ((int) (12)) < ((int) (len))) : ((int) (12)) == 0 ? 0 : (((int) (len)) < 0 ? ((int) (len)) < ((((0 * (0 * ((int) (12)) + ((int) (len))) - (1)) < 0) ? ~ ((((0 * (0 * ((int) (12)) + ((int) (len))) + (1)) << ((sizeof ((0 * ((int) (12)) + ((int) (len))) + 0) * 8) - 2)) - 1) * 2 + 1) : (0 * (0 * ((int) (12)) + ((int) (len))) + (0)))) / ((int) (12)) : ((((0 * (0 * ((int) (12)) + ((int) (len))) - (1)) < 0) ? ((((0 * (0 * ((int) (12)) + ((int) (len))) + (1)) << ((sizeof ((0 * ((int) (12)) + ((int) (len))) + 0) * 8) - 2)) - 1) * 2 + 1) : (0 * (0 * ((int) (12)) + ((int) (len))) - (1)))) / ((int) (12)) < ((int) (len))))) || (((0 * (((int) (len)) * ((int) (12))) - (1)) < 0) && (((int) (len)) * ((int) (12))) < ((-2147483647 - 1))) || (2147483647) < (((int) (len)) * ((int) (12)))) ? (*(&len12) = ((int) ((unsigned int) ((int) (len)) * (unsigned int) ((int) (12)))), 1) : (*(&len12) = ((int) ((unsigned int) ((int) (len)) * (unsigned int) ((int) (12)))), 0)) : ((((((((0 * (0 * (12) + (len)) - (1)) < 0) ? ~ ((((0 * (0 * (12) + (len)) + (1)) << ((sizeof ((0 * (12) + (len)) + 0) * 8) - 2)) - 1) * 2 + 1) : (0 * (0 * (12) + (len)) + (0)))) == 0 && (((len) < 0 && 0 < (12)) || ((12) < 0 && 0 < (len)))) || ((12) < 0 ? ((len) < 0 ? (len) < ((((0 * (0 * (12) + (len)) - (1)) < 0) ? ((((0 * (0 * (12) + (len)) + (1)) << ((sizeof ((0 * (12) + (len)) + 0) * 8) - 2)) - 1) * 2 + 1) : (0 * (0 * (12) + (len)) - (1)))) / (12) : (12) == -1 ? 0 : ((((0 * (0 * (12) + (len)) - (1)) < 0) ? ~ ((((0 * (0 * (12) + (len)) + (1)) << ((sizeof ((0 * (12) + (len)) + 0) * 8) - 2)) - 1) * 2 + 1) : (0 * (0 * (12) + (len)) + (0)))) / (12) < (len)) : (12) == 0 ? 0 : ((len) < 0 ? (len) < ((((0 * (0 * (12) + (len)) - (1)) < 0) ? ~ ((((0 * (0 * (12) + (len)) + (1)) << ((sizeof ((0 * (12) + (len)) + 0) * 8) - 2)) - 1) * 2 + 1) : (0 * (0 * (12) + (len)) + (0)))) / (12) : ((((0 * (0 * (12) + (len)) - (1)) < 0) ? ((((0 * (0 * (12) + (len)) + (1)) << ((sizeof ((0 * (12) + (len)) + 0) * 8) - 2)) - 1) * 2 + 1) : (0 * (0 * (12) + (len)) - (1)))) / (12) < (len)))) || (((0 * ((len) * (12)) - (1)) < 0) && ((len) * (12)) < ((-2147483647 - 1))) || (2147483647) < ((len) * (12))) ? (*(&len12) = ((int) ((unsigned int) (len) * (unsigned int) (12))), 1) : (*(&len12) = ((int) ((unsigned int) (len) * (unsigned int) (12))), 0))) : (sizeof *(&len12) == sizeof (long int) ? (sizeof ((len) * (12)) < sizeof (long int) ? ((((((((0 * (0 * ((long int) (12)) + ((long int) (len))) - (1)) < 0) ? ~ ((((0 * (0 * ((long int) (12)) + ((long int) (len))) + (1)) << ((sizeof ((0 * ((long int) (12)) + ((long int) (len))) + 0) * 8) - 2)) - 1) * 2 + 1) : (0 * (0 * ((long int) (12)) + ((long int) (len))) + (0)))) == 0 && ((((long int) (len)) < 0 && 0 < ((long int) (12))) || (((long int) (12)) < 0 && 0 < ((long int) (len))))) || (((long int) (12)) < 0 ? (((long int) (len)) < 0 ? ((long int) (len)) < ((((0 * (0 * ((long int) (12)) + ((long int) (len))) - (1)) < 0) ? ((((0 * (0 * ((long int) (12)) + ((long int) (len))) + (1)) << ((sizeof ((0 * ((long int) (12)) + ((long int) (len))) + 0) * 8) - 2)) - 1) * 2 + 1) : (0 * (0 * ((long int) (12)) + ((long int) (len))) - (1)))) / ((long int) (12)) : ((long int) (12)) == -1 ? 0 : ((((0 * (0 * ((long int) (12)) + ((long int) (len))) - (1)) < 0) ? ~ ((((0 * (0 * ((long int) (12)) + ((long int) (len))) + (1)) << ((sizeof ((0 * ((long int) (12)) + ((long int) (len))) + 0) * 8) - 2)) - 1) * 2 + 1) : (0 * (0 * ((long int) (12)) + ((long int) (len))) + (0)))) / ((long int) (12)) < ((long int) (len))) : ((long int) (12)) == 0 ? 0 : (((long int) (len)) < 0 ? ((long int) (len)) < ((((0 * (0 * ((long int) (12)) + ((long int) (len))) - (1)) < 0) ? ~ ((((0 * (0 * ((long int) (12)) + ((long int) (len))) + (1)) << ((sizeof ((0 * ((long int) (12)) + ((long int) (len))) + 0) * 8) - 2)) - 1) * 2 + 1) : (0 * (0 * ((long int) (12)) + ((long int) (len))) + (0)))) / ((long int) (12)) : ((((0 * (0 * ((long int) (12)) + ((long int) (len))) - (1)) < 0) ? ((((0 * (0 * ((long int) (12)) + ((long int) (len))) + (1)) << ((sizeof ((0 * ((long int) (12)) + ((long int) (len))) + 0) * 8) - 2)) - 1) * 2 + 1) : (0 * (0 * ((long int) (12)) + ((long int) (len))) - (1)))) / ((long int) (12)) < ((long int) (len))))) || (((0 * (((long int) (len)) * ((long int) (12))) - (1)) < 0) && (((long int) (len)) * ((long int) (12))) < ((-2147483647L - 1L))) || (2147483647L) < (((long int) (len)) * ((long int) (12)))) ? (*(&len12) = ((long int) ((unsigned long int) ((long int) (len)) * (unsigned long int) ((long int) (12)))), 1) : (*(&len12) = ((long int) ((unsigned long int) ((long int) (len)) * (unsigned long int) ((long int) (12)))), 0)) : ((((((((0 * (0 * (12) + (len)) - (1)) < 0) ? ~ ((((0 * (0 * (12) + (len)) + (1)) << ((sizeof ((0 * (12) + (len)) + 0) * 8) - 2)) - 1) * 2 + 1) : (0 * (0 * (12) + (len)) + (0)))) == 0 && (((len) < 0 && 0 < (12)) || ((12) < 0 && 0 < (len)))) || ((12) < 0 ? ((len) < 0 ? (len) < ((((0 * (0 * (12) + (len)) - (1)) < 0) ? ((((0 * (0 * (12) + (len)) + (1)) << ((sizeof ((0 * (12) + (len)) + 0) * 8) - 2)) - 1) * 2 + 1) : (0 * (0 * (12) + (len)) - (1)))) / (12) : (12) == -1 ? 0 : ((((0 * (0 * (12) + (len)) - (1)) < 0) ? ~ ((((0 * (0 * (12) + (len)) + (1)) << ((sizeof ((0 * (12) + (len)) + 0) * 8) - 2)) - 1) * 2 + 1) : (0 * (0 * (12) + (len)) + (0)))) / (12) < (len)) : (12) == 0 ? 0 : ((len) < 0 ? (len) < ((((0 * (0 * (12) + (len)) - (1)) < 0) ? ~ ((((0 * (0 * (12) + (len)) + (1)) << ((sizeof ((0 * (12) + (len)) + 0) * 8) - 2)) - 1) * 2 + 1) : (0 * (0 * (12) + (len)) + (0)))) / (12) : ((((0 * (0 * (12) + (len)) - (1)) < 0) ? ((((0 * (0 * (12) + (len)) + (1)) << ((sizeof ((0 * (12) + (len)) + 0) * 8) - 2)) - 1) * 2 + 1) : (0 * (0 * (12) + (len)) - (1)))) / (12) < (len)))) || (((0 * ((len) * (12)) - (1)) < 0) && ((len) * (12)) < ((-2147483647L - 1L))) || (2147483647L) < ((len) * (12))) ? (*(&len12) = ((long int) ((unsigned long int) (len) * (unsigned long int) (12))), 1) : (*(&len12) = ((long int) ((unsigned long int) (len) * (unsigned long int) (12))), 0))) : (sizeof ((len) * (12)) < sizeof (long long int) ? ((((((((0 * (0 * ((long long int) (12)) + ((long long int) (len))) - (1)) < 0) ? ~ ((((0 * (0 * ((long long int) (12)) + ((long long int) (len))) + (1)) << ((sizeof ((0 * ((long long int) (12)) + ((long long int) (len))) + 0) * 8) - 2)) - 1) * 2 + 1) : (0 * (0 * ((long long int) (12)) + ((long long int) (len))) + (0)))) == 0 && ((((long long int) (len)) < 0 && 0 < ((long long int) (12))) || (((long long int) (12)) < 0 && 0 < ((long long int) (len))))) || (((long long int) (12)) < 0 ? (((long long int) (len)) < 0 ? ((long long int) (len)) < ((((0 * (0 * ((long long int) (12)) + ((long long int) (len))) - (1)) < 0) ? ((((0 * (0 * ((long long int) (12)) + ((long long int) (len))) + (1)) << ((sizeof ((0 * ((long long int) (12)) + ((long long int) (len))) + 0) * 8) - 2)) - 1) * 2 + 1) : (0 * (0 * ((long long int) (12)) + ((long long int) (len))) - (1)))) / ((long long int) (12)) : ((long long int) (12)) == -1 ? 0 : ((((0 * (0 * ((long long int) (12)) + ((long long int) (len))) - (1)) < 0) ? ~ ((((0 * (0 * ((long long int) (12)) + ((long long int) (len))) + (1)) << ((sizeof ((0 * ((long long int) (12)) + ((long long int) (len))) + 0) * 8) - 2)) - 1) * 2 + 1) : (0 * (0 * ((long long int) (12)) + ((long long int) (len))) + (0)))) / ((long long int) (12)) < ((long long int) (len))) : ((long long int) (12)) == 0 ? 0 : (((long long int) (len)) < 0 ? ((long long int) (len)) < ((((0 * (0 * ((long long int) (12)) + ((long long int) (len))) - (1)) < 0) ? ~ ((((0 * (0 * ((long long int) (12)) + ((long long int) (len))) + (1)) << ((sizeof ((0 * ((long long int) (12)) + ((long long int) (len))) + 0) * 8) - 2)) - 1) * 2 + 1) : (0 * (0 * ((long long int) (12)) + ((long long int) (len))) + (0)))) / ((long long int) (12)) : ((((0 * (0 * ((long long int) (12)) + ((long long int) (len))) - (1)) < 0) ? ((((0 * (0 * ((long long int) (12)) + ((long long int) (len))) + (1)) << ((sizeof ((0 * ((long long int) (12)) + ((long long int) (len))) + 0) * 8) - 2)) - 1) * 2 + 1) : (0 * (0 * ((long long int) (12)) + ((long long int) (len))) - (1)))) / ((long long int) (12)) < ((long long int) (len))))) || (((0 * (((long long int) (len)) * ((long long int) (12))) - (1)) < 0) && (((long long int) (len)) * ((long long int) (12))) < ((-9223372036854775807LL - 1LL))) || (9223372036854775807LL) < (((long long int) (len)) * ((long long int) (12)))) ? (*(&len12) = ((long long int) ((unsigned long long int) ((long long int) (len)) * (unsigned long long int) ((long long int) (12)))), 1) : (*(&len12) = ((long long int) ((unsigned long long int) ((long long int) (len)) * (unsigned long long int) ((long long int) (12)))), 0)) : ((((((((0 * (0 * (12) + (len)) - (1)) < 0) ? ~ ((((0 * (0 * (12) + (len)) + (1)) << ((sizeof ((0 * (12) + (len)) + 0) * 8) - 2)) - 1) * 2 + 1) : (0 * (0 * (12) + (len)) + (0)))) == 0 && (((len) < 0 && 0 < (12)) || ((12) < 0 && 0 < (len)))) || ((12) < 0 ? ((len) < 0 ? (len) < ((((0 * (0 * (12) + (len)) - (1)) < 0) ? ((((0 * (0 * (12) + (len)) + (1)) << ((sizeof ((0 * (12) + (len)) + 0) * 8) - 2)) - 1) * 2 + 1) : (0 * (0 * (12) + (len)) - (1)))) / (12) : (12) == -1 ? 0 : ((((0 * (0 * (12) + (len)) - (1)) < 0) ? ~ ((((0 * (0 * (12) + (len)) + (1)) << ((sizeof ((0 * (12) + (len)) + 0) * 8) - 2)) - 1) * 2 + 1) : (0 * (0 * (12) + (len)) + (0)))) / (12) < (len)) : (12) == 0 ? 0 : ((len) < 0 ? (len) < ((((0 * (0 * (12) + (len)) - (1)) < 0) ? ~ ((((0 * (0 * (12) + (len)) + (1)) << ((sizeof ((0 * (12) + (len)) + 0) * 8) - 2)) - 1) * 2 + 1) : (0 * (0 * (12) + (len)) + (0)))) / (12) : ((((0 * (0 * (12) + (len)) - (1)) < 0) ? ((((0 * (0 * (12) + (len)) + (1)) << ((sizeof ((0 * (12) + (len)) + 0) * 8) - 2)) - 1) * 2 + 1) : (0 * (0 * (12) + (len)) - (1)))) / (12) < (len)))) || (((0 * ((len) * (12)) - (1)) < 0) && ((len) * (12)) < ((-9223372036854775807LL - 1LL))) || (9223372036854775807LL) < ((len) * (12))) ? (*(&len12) = ((long long int) ((unsigned long long int) (len) * (unsigned long long int) (12))), 1) : (*(&len12) = ((long long int) ((unsigned long long int) (len) * (unsigned long long int) (12))), 0))))) && len12 < size)

    for (ep = text + size - 11 * len; tp <= ep; )
      {
        char const *tp0 = tp;
        d = d1[U(data_flow, tp[-1])], tp += d;
        d = d1[U(data_flow, tp[-1])], tp += d;
        if (d != 0)
          {
            d = d1[U(data_flow, tp[-1])], tp += d;
            d = d1[U(data_flow, tp[-1])], tp += d;
            d = d1[U(data_flow, tp[-1])], tp += d;
            if (d != 0)
              {
                d = d1[U(data_flow, tp[-1])], tp += d;
                d = d1[U(data_flow, tp[-1])], tp += d;
                d = d1[U(data_flow, tp[-1])], tp += d;
                if (d != 0)
                  {
                    d = d1[U(data_flow, tp[-1])], tp += d;
                    d = d1[U(data_flow, tp[-1])], tp += d;



                    int advance_heuristic = 16 * sizeof (long);
                    if (advance_heuristic <= tp - tp0)
                      continue;
                    tp--;
                    tp = memchr_kwset (data_flow, tp, text + size - tp, kwset);
                    if (! tp)
                      return -1;
                    tp++;
                    if (ep <= tp)
                      break;
                  }
              }
          }
        if (bm_delta2_search (data_flow, &tp, ep, sp, len, trans, gc1, gc2, d1, kwset))
          return tp - text;
      }



  ep = text + size;
  d = d1[U(data_flow, tp[-1])];
  while (d <= ep - tp)
    {
      d = d1[U(data_flow, (tp += d)[-1])];
      if (d != 0)
        continue;
      if (bm_delta2_search (data_flow, &tp, ep, sp, len, trans, gc1, gc2, ((void *)0), kwset))
        return tp - text;
    }

  return -1;
}


static ptrdiff_t
bmexec (int *data_flow, kwset_t kwset, char const *text, ptrdiff_t size,
        struct kwsmatch *kwsmatch, _Bool longest)
{


  ptrdiff_t ret = (kwset->trans ? bmexec_trans (data_flow, kwset, text, size) : bmexec_trans (data_flow, kwset, text, size)


                                                       );
  if (0 <= ret)
    {
       kwsmatch->index = 0;
       kwsmatch->offset[0] = ret;
       kwsmatch->size[0] = kwset->mind;
    }

  return ret;
}



static inline ptrdiff_t
acexec_trans (int *data_flow, kwset_t kwset, char const *text, ptrdiff_t len,
              struct kwsmatch *kwsmatch, _Bool longest)
{
  struct trie const *trie, *accept;
  char const *tp, *left, *lim;
  struct tree const *tree;
  char const *trans;


  if (len < kwset->mind)
    return -1;
  trans = kwset->trans;
  trie = kwset->trie;
  lim = text + len;
  tp = text;

  if (!trie->accepting)
    {
      unsigned char c;
      int gc1 = kwset->gc1;

      while (1)
        {
          if (gc1 < 0)
            {
              while (! (trie = kwset->next[c = tr (data_flow, trans, *tp++)]))
                if (tp >= lim)
                  return -1;
            }
          else
            {
              tp = memchr_kwset (data_flow, tp, lim - tp, kwset);
              if (!tp)
                return -1;
              c = tr (data_flow, trans, *tp++);
              trie = kwset->next[c];
            }

          while (1)
            {
              if (trie->accepting)
                goto match;
              if (tp >= lim)
                return -1;
              c = tr (data_flow, trans, *tp++);

              for (tree = trie->links; c != tree->label; )
                {
                  tree = c < tree->label ? tree->llink : tree->rlink;
                  if (! tree)
                    {
                      trie = trie->fail;
                      if (!trie)
                        {
                          trie = kwset->next[c];
                          if (trie)
                            goto have_trie;
                          if (tp >= lim)
                            return -1;
                          goto next_c;
                        }
                      if (trie->accepting)
                        {
                          --tp;
                          goto match;
                        }
                      tree = trie->links;
                    }
                }
              trie = tree->trie;
            have_trie:;
            }
        next_c:;
        }
    }

 match:
  accept = trie;
  while (accept->accepting == (((((0u) + 1) << ((32) ? (32) - 1 - (0) : 0)) - 1) * 2 + 1))
    accept = accept->fail;
  left = tp - accept->depth;


  if (longest)
    {
      while (tp < lim)
        {
          struct trie const *accept1;
          char const *left1;
          unsigned char c = tr (data_flow, trans, *tp++);

          do
            {
              tree = trie->links;
              while (tree && c != tree->label)
                tree = c < tree->label ? tree->llink : tree->rlink;
            }
          while (!tree && (trie = trie->fail) && accept->depth <= trie->depth);

          if (!tree)
            break;
          trie = tree->trie;
          if (trie->accepting)
            {
              accept1 = trie;
              while (accept1->accepting == (((((0u) + 1) << ((32) ? (32) - 1 - (0) : 0)) - 1) * 2 + 1))
                accept1 = accept1->fail;
              left1 = tp - accept1->depth;
              if (left1 <= left)
                {
                  left = left1;
                  accept = accept1;
                }
            }
        }
    }

  kwsmatch->index = accept->accepting / 2;
  kwsmatch->offset[0] = left - text;
  kwsmatch->size[0] = accept->depth;

  return left - text;
}


static ptrdiff_t
acexec (int *data_flow, kwset_t kwset, char const *text, ptrdiff_t size,
        struct kwsmatch *kwsmatch, _Bool longest)
{
  ((0 <= size) ? (void) 0 : __builtin_unreachable ());


  return (kwset->trans ? acexec_trans (data_flow, kwset, text, size, kwsmatch, longest) : acexec_trans (data_flow, kwset, text, size, kwsmatch, longest)


                                                                 );
}







ptrdiff_t
kwsexec (int *data_flow, kwset_t kwset, char const *text, ptrdiff_t size,
         struct kwsmatch *kwsmatch, _Bool longest)
{
  return kwset->kwsexec (data_flow, kwset, text, size, kwsmatch, longest);
}


void
kwsfree (int *data_flow, kwset_t kwset)
{
  __extension__ ({ struct obstack *__o = (&kwset->obstack); void *__obj = (void *) (((void *)0)); if (__obj > (void *) __o->chunk && __obj < (void *) __o->chunk_limit) __o->next_free = __o->object_base = (char *) __obj; else _obstack_free (data_flow, __o, __obj); });
  free (kwset);
}
