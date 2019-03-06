       
       










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





       
       






struct dirent
  {

    __ino_t d_ino;
    __off_t d_off;




    unsigned short int d_reclen;
    unsigned char d_type;
    char d_name[256];
  };
enum
  {
    DT_UNKNOWN = 0,

    DT_FIFO = 1,

    DT_CHR = 2,

    DT_DIR = 4,

    DT_BLK = 6,

    DT_REG = 8,

    DT_LNK = 10,

    DT_SOCK = 12,

    DT_WHT = 14

  };
typedef struct __dirstream DIR;






extern DIR *opendir (__const char *__name) ;






extern DIR *fdopendir (int __fd);







extern int closedir (DIR *__dirp) ;
extern struct dirent *readdir (DIR *__dirp) ;
extern int readdir_r (DIR *__restrict __dirp,
        struct dirent *__restrict __entry,
        struct dirent **__restrict __result)
     ;
extern void rewinddir (DIR *__dirp) ;





extern void seekdir (DIR *__dirp, long int __pos) ;


extern long int telldir (DIR *__dirp) ;





extern int dirfd (DIR *__dirp) ;






extern int scandir (__const char *__restrict __dir,
      struct dirent ***__restrict __namelist,
      int (*__selector) (int *data_flow, __const struct dirent *),
      int (*__cmp) (int *data_flow, __const struct dirent **,
      __const struct dirent **))
     ;
extern int alphasort (__const struct dirent **__e1,
        __const struct dirent **__e2)
     __attribute__ ((__pure__)) ;
extern __ssize_t getdirentries (int __fd, char *__restrict __buf,
    size_t __nbytes,
    __off_t *__restrict __basep)
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
       
       


       













struct tm
{
  int tm_sec;
  int tm_min;
  int tm_hour;
  int tm_mday;
  int tm_mon;
  int tm_year;
  int tm_wday;
  int tm_yday;
  int tm_isdst;


  long int tm_gmtoff;
  __const char *tm_zone;




};








struct itimerspec
  {
    struct timespec it_interval;
    struct timespec it_value;
  };


struct sigevent;



extern clock_t clock (void) ;


extern time_t time (time_t *__timer) ;


extern double difftime (time_t __time1, time_t __time0)
     __attribute__ ((__const__));


extern time_t mktime (struct tm *__tp) ;





extern size_t strftime (char *__restrict __s, size_t __maxsize,
   __const char *__restrict __format,
   __const struct tm *__restrict __tp) ;

typedef struct __locale_struct
{

  struct __locale_data *__locales[13];


  const unsigned short int *__ctype_b;
  const int *__ctype_tolower;
  const int *__ctype_toupper;


  const char *__names[13];
} *__locale_t;


typedef __locale_t locale_t;

extern size_t strftime_l (char *__restrict __s, size_t __maxsize,
     __const char *__restrict __format,
     __const struct tm *__restrict __tp,
     __locale_t __loc) ;



extern struct tm *gmtime (__const time_t *__timer) ;



extern struct tm *localtime (__const time_t *__timer) ;





extern struct tm *gmtime_r (__const time_t *__restrict __timer,
       struct tm *__restrict __tp) ;



extern struct tm *localtime_r (__const time_t *__restrict __timer,
          struct tm *__restrict __tp) ;





extern char *asctime (__const struct tm *__tp) ;


extern char *ctime (__const time_t *__timer) ;







extern char *asctime_r (__const struct tm *__restrict __tp,
   char *__restrict __buf) ;


extern char *ctime_r (__const time_t *__restrict __timer,
        char *__restrict __buf) ;




extern char *__tzname[2];
extern int __daylight;
extern long int __timezone;




extern char *tzname[2];



extern void tzset (void) ;



extern int daylight;
extern long int timezone;





extern int stime (__const time_t *__when) ;
extern time_t timegm (struct tm *__tp) ;


extern time_t timelocal (struct tm *__tp) ;


extern int dysize (int __year) __attribute__ ((__const__));
extern int nanosleep (__const struct timespec *__requested_time,
        struct timespec *__remaining);



extern int clock_getres (clockid_t __clock_id, struct timespec *__res) ;


extern int clock_gettime (clockid_t __clock_id, struct timespec *__tp) ;


extern int clock_settime (clockid_t __clock_id, __const struct timespec *__tp)
     ;






extern int clock_nanosleep (clockid_t __clock_id, int __flags,
       __const struct timespec *__req,
       struct timespec *__rem);


extern int clock_getcpuclockid (pid_t __pid, clockid_t *__clock_id) ;




extern int timer_create (clockid_t __clock_id,
    struct sigevent *__restrict __evp,
    timer_t *__restrict __timerid) ;


extern int timer_delete (timer_t __timerid) ;


extern int timer_settime (timer_t __timerid, int __flags,
     __const struct itimerspec *__restrict __value,
     struct itimerspec *__restrict __ovalue) ;


extern int timer_gettime (timer_t __timerid, struct itimerspec *__value)
     ;


extern int timer_getoverrun (timer_t __timerid) ;



struct __time_t_must_be_integral {
  unsigned int __floating_time_t_unsupported : (time_t) 1;
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
extern int stat (int *data_flow, __const char *__restrict __file,
   struct stat *__restrict __buf) ;



extern int fstat (int *data_flow, int __fd, struct stat *__buf) ;
extern int fstatat (int *data_flow, int __fd, __const char *__restrict __file,
      struct stat *__restrict __buf, int __flag)
     ;
extern int lstat (int *data_flow, __const char *__restrict __file,
    struct stat *__restrict __buf) ;
extern int chmod (__const char *__file, __mode_t __mode)
     ;





extern int lchmod (__const char *__file, __mode_t __mode)
     ;




extern int fchmod (int __fd, __mode_t __mode) ;





extern int fchmodat (int __fd, __const char *__file, __mode_t __mode,
       int __flag)
      ;






extern __mode_t umask (__mode_t __mask) ;
extern int mkdir (__const char *__path, __mode_t __mode)
     ;





extern int mkdirat (int __fd, __const char *__path, __mode_t __mode)
     ;






extern int mknod (int *data_flow, __const char *__path, __mode_t __mode, __dev_t __dev)
     ;





extern int mknodat (int *data_flow, int __fd, __const char *__path, __mode_t __mode,
      __dev_t __dev) ;





extern int mkfifo (__const char *__path, __mode_t __mode)
     ;





extern int mkfifoat (int __fd, __const char *__path, __mode_t __mode)
     ;





extern int utimensat (int __fd, __const char *__path,
        __const struct timespec __times[2],
        int __flags)
     ;




extern int futimens (int __fd, __const struct timespec __times[2]) ;
extern int __fxstat (int __ver, int __fildes, struct stat *__stat_buf)
     ;
extern int __xstat (int __ver, __const char *__filename,
      struct stat *__stat_buf) ;
extern int __lxstat (int __ver, __const char *__filename,
       struct stat *__stat_buf) ;
extern int __fxstatat (int __ver, int __fildes, __const char *__filename,
         struct stat *__stat_buf, int __flag)
     ;
extern int __xmknod (int __ver, __const char *__path, __mode_t __mode,
       __dev_t *__dev) ;

extern int __xmknodat (int __ver, int __fd, __const char *__path,
         __mode_t __mode, __dev_t *__dev)
     ;




extern __inline __attribute__ ((__gnu_inline__)) int
__attribute__ ((__nothrow__)) stat (int *data_flow, __const char *__path, struct stat *__statbuf)
{
  return __xstat (3, __path, __statbuf);
}


extern __inline __attribute__ ((__gnu_inline__)) int
__attribute__ ((__nothrow__)) lstat (int *data_flow, __const char *__path, struct stat *__statbuf)
{
  return __lxstat (3, __path, __statbuf);
}


extern __inline __attribute__ ((__gnu_inline__)) int
__attribute__ ((__nothrow__)) fstat (int *data_flow, int __fd, struct stat *__statbuf)
{
  return __fxstat (3, __fd, __statbuf);
}


extern __inline __attribute__ ((__gnu_inline__)) int
__attribute__ ((__nothrow__)) fstatat (int *data_flow, int __fd, __const char *__filename, struct stat *__statbuf, int __flag)

{
  return __fxstatat (3, __fd, __filename, __statbuf, __flag);
}



extern __inline __attribute__ ((__gnu_inline__)) int
__attribute__ ((__nothrow__)) mknod (int *data_flow, __const char *__path, __mode_t __mode, __dev_t __dev)
{
  return __xmknod (1, __path, __mode, &__dev);
}



extern __inline __attribute__ ((__gnu_inline__)) int
__attribute__ ((__nothrow__)) mknodat (int *data_flow, int __fd, __const char *__path, __mode_t __mode, __dev_t __dev)

{
  return __xmknodat (1, __fd, __path, __mode, &__dev);
}

extern int _gl_cxxalias_dummy;

extern int _gl_cxxalias_dummy;
extern int _gl_cxxalias_dummy
                                                                         ;

extern int _gl_cxxalias_dummy;
extern int _gl_cxxalias_dummy;


extern int _gl_cxxalias_dummy;
extern int _gl_cxxalias_dummy;


extern int _gl_cxxalias_dummy;

enum { I_RING_SIZE = 4 };
_Static_assert (1 <= I_RING_SIZE, "verify (" "1 <= I_RING_SIZE" ")");
struct I_ring
{
  int ir_data[I_RING_SIZE];
  int ir_default_val;
  unsigned int ir_front;
  unsigned int ir_back;
  _Bool ir_empty;
};
typedef struct I_ring I_ring;

void i_ring_init (int *data_flow, I_ring *ir, int ir_default_val);
int i_ring_push (int *data_flow, I_ring *ir, int val);
int i_ring_pop (int *data_flow, I_ring *ir);
_Bool i_ring_empty (int *data_flow, I_ring const *ir) __attribute__ ((__pure__));

typedef struct {
        struct _ftsent *fts_cur;
        struct _ftsent *fts_child;
        struct _ftsent **fts_array;
        dev_t fts_dev;
        char *fts_path;
        int fts_rfd;
        int fts_cwd_fd;

        size_t fts_pathlen;
        size_t fts_nitems;
        int (*fts_compar) (int *data_flow, struct _ftsent const **, struct _ftsent const **);
        int fts_options;
        struct hash_table *fts_leaf_optimization_works_ht;

        union {
                struct hash_table *ht;





                struct cycle_check_state *state;
        } fts_cycle;




        I_ring fts_fd_ring;
} FTS;

typedef struct _ftsent {
        struct _ftsent *fts_cycle;
        struct _ftsent *fts_parent;
        struct _ftsent *fts_link;
        DIR *fts_dirp;


        long fts_number;
        void *fts_pointer;
        char *fts_accpath;
        char *fts_path;
        int fts_errno;
        int fts_symfd;
        size_t fts_pathlen;

        FTS *fts_fts;



        ptrdiff_t fts_level;

        size_t fts_namelen;
        nlink_t fts_n_dirs_remaining;
        unsigned short int fts_info;



        unsigned short int fts_flags;





        unsigned short int fts_instr;

        struct stat fts_statp[1];
        char fts_name[];
} FTSENT;

FTSENT *fts_children (int *data_flow, FTS *, int) __attribute__ ((__warn_unused_result__));
int fts_close (int *data_flow, FTS *) __attribute__ ((__warn_unused_result__));
FTS *fts_open (int *data_flow, char * const *, int,
                   int (*)(int *data_flow, const FTSENT **, const FTSENT **))
  __attribute__ ((__warn_unused_result__));
FTSENT *fts_read (int *data_flow, FTS *) __attribute__ ((__warn_unused_result__));
int fts_set (int *data_flow, FTS *, FTSENT *, int) ;



       










       








       




       

       
       




       
struct flock
  {
    short int l_type;
    short int l_whence;

    __off_t l_start;
    __off_t l_len;




    __pid_t l_pid;
  };







       
extern int fcntl (int __fd, int __cmd, ...);
extern int open (__const char *__file, int __oflag, ...) ;
extern int openat (int __fd, __const char *__file, int __oflag, ...)
     ;
extern int creat (__const char *__file, __mode_t __mode) ;
extern int lockf (int __fd, int __cmd, __off_t __len);
extern int posix_fadvise (int __fd, __off_t __offset, __off_t __len,
     int __advise) ;
extern int posix_fallocate (int __fd, __off_t __offset, __off_t __len);

extern int rpl_fcntl (int *data_flow, int fd, int action, ...);
extern int _gl_cxxalias_dummy;






extern int _gl_cxxalias_dummy;
extern int _gl_cxxalias_dummy;




extern int _gl_cxxalias_dummy;
extern int _gl_cxxalias_dummy
                                                                               ;

extern int _gl_cxxalias_dummy;










extern int *__errno_location (void) __attribute__ ((__const__));


       








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


extern size_t __ctype_get_mb_cur_max (void) ;




extern double atof (int *data_flow, __const char *__nptr)
     __attribute__ ((__pure__))  ;

extern int atoi (int *data_flow, __const char *__nptr)
     __attribute__ ((__pure__))  ;

extern long int atol (int *data_flow, __const char *__nptr)
     __attribute__ ((__pure__))  ;





__extension__ extern long long int atoll (int *data_flow, __const char *__nptr)
     __attribute__ ((__pure__))  ;





extern double strtod (__const char *__restrict __nptr,
        char **__restrict __endptr)
      ;





extern float strtof (__const char *__restrict __nptr,
       char **__restrict __endptr)  ;

extern long double strtold (__const char *__restrict __nptr,
       char **__restrict __endptr)
      ;





extern long int strtol (__const char *__restrict __nptr,
   char **__restrict __endptr, int __base)
      ;

extern unsigned long int strtoul (__const char *__restrict __nptr,
      char **__restrict __endptr, int __base)
      ;




__extension__
extern long long int strtoq (__const char *__restrict __nptr,
        char **__restrict __endptr, int __base)
      ;

__extension__
extern unsigned long long int strtouq (__const char *__restrict __nptr,
           char **__restrict __endptr, int __base)
      ;





__extension__
extern long long int strtoll (__const char *__restrict __nptr,
         char **__restrict __endptr, int __base)
      ;

__extension__
extern unsigned long long int strtoull (__const char *__restrict __nptr,
     char **__restrict __endptr, int __base)
      ;


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

extern char *l64a (long int __n) ;


extern long int a64l (__const char *__s)
     __attribute__ ((__pure__))  ;




       






extern long int random (void) ;


extern void srandom (unsigned int __seed) ;





extern char *initstate (unsigned int __seed, char *__statebuf,
   size_t __statelen) ;



extern char *setstate (char *__statebuf) ;







struct random_data
  {
    int32_t *fptr;
    int32_t *rptr;
    int32_t *state;
    int rand_type;
    int rand_deg;
    int rand_sep;
    int32_t *end_ptr;
  };

extern int random_r (struct random_data *__restrict __buf,
       int32_t *__restrict __result) ;

extern int srandom_r (unsigned int __seed, struct random_data *__buf)
     ;

extern int initstate_r (unsigned int __seed, char *__restrict __statebuf,
   size_t __statelen,
   struct random_data *__restrict __buf)
     ;

extern int setstate_r (char *__restrict __statebuf,
         struct random_data *__restrict __buf)
     ;






extern int rand (void) ;

extern void srand (unsigned int __seed) ;




extern int rand_r (unsigned int *__seed) ;







extern double drand48 (void) ;
extern double erand48 (unsigned short int __xsubi[3]) ;


extern long int lrand48 (void) ;
extern long int nrand48 (unsigned short int __xsubi[3])
     ;


extern long int mrand48 (void) ;
extern long int jrand48 (unsigned short int __xsubi[3])
     ;


extern void srand48 (long int __seedval) ;
extern unsigned short int *seed48 (unsigned short int __seed16v[3])
     ;
extern void lcong48 (unsigned short int __param[7]) ;





struct drand48_data
  {
    unsigned short int __x[3];
    unsigned short int __old_x[3];
    unsigned short int __c;
    unsigned short int __init;
    unsigned long long int __a;
  };


extern int drand48_r (struct drand48_data *__restrict __buffer,
        double *__restrict __result) ;
extern int erand48_r (unsigned short int __xsubi[3],
        struct drand48_data *__restrict __buffer,
        double *__restrict __result) ;


extern int lrand48_r (struct drand48_data *__restrict __buffer,
        long int *__restrict __result)
     ;
extern int nrand48_r (unsigned short int __xsubi[3],
        struct drand48_data *__restrict __buffer,
        long int *__restrict __result)
     ;


extern int mrand48_r (struct drand48_data *__restrict __buffer,
        long int *__restrict __result)
     ;
extern int jrand48_r (unsigned short int __xsubi[3],
        struct drand48_data *__restrict __buffer,
        long int *__restrict __result)
     ;


extern int srand48_r (long int __seedval, struct drand48_data *__buffer)
     ;

extern int seed48_r (unsigned short int __seed16v[3],
       struct drand48_data *__buffer) ;

extern int lcong48_r (unsigned short int __param[7],
        struct drand48_data *__buffer)
     ;









extern void *malloc (size_t __size) __attribute__ ((__malloc__)) ;

extern void *calloc (size_t __nmemb, size_t __size)
     __attribute__ ((__malloc__)) ;










extern void *realloc (void *__ptr, size_t __size)
     __attribute__ ((__warn_unused_result__));

extern void free (void *__ptr) ;




extern void cfree (void *__ptr) ;








extern void *valloc (size_t __size) __attribute__ ((__malloc__)) ;




extern int posix_memalign (void **__memptr, size_t __alignment, size_t __size)
      ;




extern void abort (void) __attribute__ ((__noreturn__));



extern int atexit (void (*__func) (int *data_flow)) ;





extern int on_exit (void (*__func) (int *data_flow, int __status, void *__arg), void *__arg)
     ;






extern void exit (int __status) __attribute__ ((__noreturn__));






extern void _Exit (int __status) __attribute__ ((__noreturn__));






extern char *getenv (__const char *__name)  ;




extern char *__secure_getenv (__const char *__name)
      ;





extern int putenv (char *__string) ;





extern int setenv (__const char *__name, __const char *__value, int __replace)
     ;


extern int unsetenv (__const char *__name) ;






extern int clearenv (void) ;
extern char *mktemp (char *__template)  ;
extern int mkstemp (char *__template)  ;
extern int mkstemps (char *__template, int __suffixlen)  ;
extern char *mkdtemp (char *__template)  ;





extern int system (__const char *__command) ;

extern char *realpath (__const char *__restrict __name,
         char *__restrict __resolved) ;






typedef int (*__compar_fn_t) (int *data_flow, __const void *, __const void *);



extern void *bsearch (__const void *__key, __const void *__base,
        size_t __nmemb, size_t __size, __compar_fn_t __compar)
      ;



extern void qsort (void *__base, size_t __nmemb, size_t __size,
     __compar_fn_t __compar) ;
extern int abs (int __x) __attribute__ ((__const__)) ;
extern long int labs (long int __x) __attribute__ ((__const__)) ;



__extension__ extern long long int llabs (long long int __x)
     __attribute__ ((__const__)) ;







extern div_t div (int __numer, int __denom)
     __attribute__ ((__const__)) ;
extern ldiv_t ldiv (long int __numer, long int __denom)
     __attribute__ ((__const__)) ;




__extension__ extern lldiv_t lldiv (long long int __numer,
        long long int __denom)
     __attribute__ ((__const__)) ;

extern char *ecvt (double __value, int __ndigit, int *__restrict __decpt,
     int *__restrict __sign)  ;




extern char *fcvt (double __value, int __ndigit, int *__restrict __decpt,
     int *__restrict __sign)  ;




extern char *gcvt (double __value, int __ndigit, char *__buf)
      ;




extern char *qecvt (long double __value, int __ndigit,
      int *__restrict __decpt, int *__restrict __sign)
      ;
extern char *qfcvt (long double __value, int __ndigit,
      int *__restrict __decpt, int *__restrict __sign)
      ;
extern char *qgcvt (long double __value, int __ndigit, char *__buf)
      ;




extern int ecvt_r (double __value, int __ndigit, int *__restrict __decpt,
     int *__restrict __sign, char *__restrict __buf,
     size_t __len) ;
extern int fcvt_r (double __value, int __ndigit, int *__restrict __decpt,
     int *__restrict __sign, char *__restrict __buf,
     size_t __len) ;

extern int qecvt_r (long double __value, int __ndigit,
      int *__restrict __decpt, int *__restrict __sign,
      char *__restrict __buf, size_t __len)
     ;
extern int qfcvt_r (long double __value, int __ndigit,
      int *__restrict __decpt, int *__restrict __sign,
      char *__restrict __buf, size_t __len)
     ;







extern int mblen (__const char *__s, size_t __n) ;


extern int mbtowc (wchar_t *__restrict __pwc,
     __const char *__restrict __s, size_t __n) ;


extern int wctomb (char *__s, wchar_t __wchar) ;



extern size_t mbstowcs (wchar_t *__restrict __pwcs,
   __const char *__restrict __s, size_t __n) ;

extern size_t wcstombs (char *__restrict __s,
   __const wchar_t *__restrict __pwcs, size_t __n)
     ;








extern int rpmatch (__const char *__response)  ;
extern int getsubopt (char **__restrict __optionp,
        char *__const *__restrict __tokens,
        char **__restrict __valuep)
      ;
extern int getloadavg (double __loadavg[], int __nelem)
     ;






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
     ;


extern void *memmove (void *__dest, __const void *__src, size_t __n)
     ;






extern void *memccpy (void *__restrict __dest, __const void *__restrict __src,
        int __c, size_t __n)
     ;





extern void *memset (void *__s, int __c, size_t __n) ;


extern int memcmp (__const void *__s1, __const void *__s2, size_t __n)
     __attribute__ ((__pure__)) ;
extern void *memchr (__const void *__s, int __c, size_t __n)
      __attribute__ ((__pure__)) ;




extern char *strcpy (char *__restrict __dest, __const char *__restrict __src)
     ;

extern char *strncpy (char *__restrict __dest,
        __const char *__restrict __src, size_t __n)
     ;


extern char *strcat (char *__restrict __dest, __const char *__restrict __src)
     ;

extern char *strncat (char *__restrict __dest, __const char *__restrict __src,
        size_t __n) ;


extern int strcmp (__const char *__s1, __const char *__s2)
     __attribute__ ((__pure__)) ;

extern int strncmp (__const char *__s1, __const char *__s2, size_t __n)
     __attribute__ ((__pure__)) ;


extern int strcoll (__const char *__s1, __const char *__s2)
     __attribute__ ((__pure__)) ;

extern size_t strxfrm (char *__restrict __dest,
         __const char *__restrict __src, size_t __n)
     ;

extern int strcoll_l (__const char *__s1, __const char *__s2, __locale_t __l)
     __attribute__ ((__pure__)) ;

extern size_t strxfrm_l (char *__dest, __const char *__src, size_t __n,
    __locale_t __l) ;





extern char *strdup (__const char *__s)
     __attribute__ ((__malloc__)) ;






extern char *strndup (__const char *__string, size_t __n)
     __attribute__ ((__malloc__)) ;

extern char *strchr (__const char *__s, int __c)
     __attribute__ ((__pure__)) ;
extern char *strrchr (__const char *__s, int __c)
     __attribute__ ((__pure__)) ;





extern size_t strcspn (__const char *__s, __const char *__reject)
     __attribute__ ((__pure__)) ;


extern size_t strspn (__const char *__s, __const char *__accept)
     __attribute__ ((__pure__)) ;
extern char *strpbrk (__const char *__s, __const char *__accept)
     __attribute__ ((__pure__)) ;
extern char *strstr (__const char *__haystack, __const char *__needle)
     __attribute__ ((__pure__)) ;




extern char *strtok (char *__restrict __s, __const char *__restrict __delim)
     ;




extern char *__strtok_r (char *__restrict __s,
    __const char *__restrict __delim,
    char **__restrict __save_ptr)
     ;

extern char *strtok_r (char *__restrict __s, __const char *__restrict __delim,
         char **__restrict __save_ptr)
     ;


extern size_t strlen (__const char *__s)
     __attribute__ ((__pure__)) ;





extern size_t strnlen (__const char *__string, size_t __maxlen)
     __attribute__ ((__pure__)) ;





extern char *strerror (int __errnum) ;

extern int strerror_r (int __errnum, char *__buf, size_t __buflen) __asm__ ("" "__xpg_strerror_r")

                        ;
extern char *strerror_l (int __errnum, __locale_t __l) ;





extern void __bzero (void *__s, size_t __n) ;



extern void bcopy (__const void *__src, void *__dest, size_t __n)
     ;


extern void bzero (void *__s, size_t __n) ;


extern int bcmp (__const void *__s1, __const void *__s2, size_t __n)
     __attribute__ ((__pure__)) ;
extern char *index (__const char *__s, int __c)
     __attribute__ ((__pure__)) ;
extern char *rindex (__const char *__s, int __c)
     __attribute__ ((__pure__)) ;




extern int ffs (int __i) __attribute__ ((__const__));
extern int strcasecmp (__const char *__s1, __const char *__s2)
     __attribute__ ((__pure__)) ;


extern int strncasecmp (__const char *__s1, __const char *__s2, size_t __n)
     __attribute__ ((__pure__)) ;
extern char *strsep (char **__restrict __stringp,
       __const char *__restrict __delim)
     ;




extern char *strsignal (int __sig) ;


extern char *__stpcpy (char *__restrict __dest, __const char *__restrict __src)
     ;
extern char *stpcpy (char *__restrict __dest, __const char *__restrict __src)
     ;



extern char *__stpncpy (char *__restrict __dest,
   __const char *__restrict __src, size_t __n)
     ;
extern char *stpncpy (char *__restrict __dest,
        __const char *__restrict __src, size_t __n)
     ;


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
       




extern char *__strdup (__const char *__string) __attribute__ ((__malloc__));
extern char *__strndup (__const char *__string, size_t __n)
     __attribute__ ((__malloc__));








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
       




typedef __useconds_t useconds_t;
typedef __intptr_t intptr_t;






typedef __socklen_t socklen_t;
extern int access (__const char *__name, int __type) ;
extern int faccessat (int __fd, __const char *__file, int __type, int __flag)
      ;
extern __off_t lseek (int __fd, __off_t __offset, int __whence) ;
extern int close (int __fd);






extern ssize_t read (int __fd, void *__buf, size_t __nbytes) ;





extern ssize_t write (int __fd, __const void *__buf, size_t __n) ;
extern ssize_t pread (int __fd, void *__buf, size_t __nbytes,
        __off_t __offset) ;






extern ssize_t pwrite (int __fd, __const void *__buf, size_t __n,
         __off_t __offset) ;
extern int pipe (int __pipedes[2]) ;
extern unsigned int alarm (unsigned int __seconds) ;
extern unsigned int sleep (unsigned int __seconds);







extern __useconds_t ualarm (__useconds_t __value, __useconds_t __interval)
     ;






extern int usleep (__useconds_t __useconds);
extern int pause (void);



extern int chown (__const char *__file, __uid_t __owner, __gid_t __group)
      ;



extern int fchown (int __fd, __uid_t __owner, __gid_t __group) ;




extern int lchown (__const char *__file, __uid_t __owner, __gid_t __group)
      ;






extern int fchownat (int __fd, __const char *__file, __uid_t __owner,
       __gid_t __group, int __flag)
      ;



extern int chdir (__const char *__path)  ;



extern int fchdir (int __fd) ;
extern char *getcwd (char *__buf, size_t __size) ;
extern char *getwd (char *__buf)
      __attribute__ ((__deprecated__)) ;




extern int dup (int __fd) ;


extern int dup2 (int __fd, int __fd2) ;
extern char **__environ;







extern int execve (__const char *__path, char *__const __argv[],
     char *__const __envp[]) ;




extern int fexecve (int __fd, char *__const __argv[], char *__const __envp[])
     ;




extern int execv (__const char *__path, char *__const __argv[])
     ;



extern int execle (__const char *__path, __const char *__arg, ...)
     ;



extern int execl (__const char *__path, __const char *__arg, ...)
     ;



extern int execvp (__const char *__file, char *__const __argv[])
     ;




extern int execlp (__const char *__file, __const char *__arg, ...)
     ;
extern int nice (int __inc) ;




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
     ;


extern long int fpathconf (int __fd, int __name) ;


extern long int sysconf (int __name) ;



extern size_t confstr (int __name, char *__buf, size_t __len) ;




extern __pid_t getpid (void) ;


extern __pid_t getppid (void) ;




extern __pid_t getpgrp (void) ;
extern __pid_t __getpgid (__pid_t __pid) ;

extern __pid_t getpgid (__pid_t __pid) ;






extern int setpgid (__pid_t __pid, __pid_t __pgid) ;
extern int setpgrp (void) ;
extern __pid_t setsid (void) ;



extern __pid_t getsid (__pid_t __pid) ;



extern __uid_t getuid (void) ;


extern __uid_t geteuid (void) ;


extern __gid_t getgid (void) ;


extern __gid_t getegid (void) ;




extern int getgroups (int __size, __gid_t __list[]) ;
extern int setuid (__uid_t __uid) ;




extern int setreuid (__uid_t __ruid, __uid_t __euid) ;




extern int seteuid (__uid_t __uid) ;






extern int setgid (__gid_t __gid) ;




extern int setregid (__gid_t __rgid, __gid_t __egid) ;




extern int setegid (__gid_t __gid) ;
extern __pid_t fork (void) ;







extern __pid_t vfork (void) ;





extern char *ttyname (int __fd) ;



extern int ttyname_r (int __fd, char *__buf, size_t __buflen)
      ;



extern int isatty (int __fd) ;





extern int ttyslot (void) ;




extern int link (__const char *__from, __const char *__to)
      ;




extern int linkat (int __fromfd, __const char *__from, int __tofd,
     __const char *__to, int __flags)
      ;




extern int symlink (__const char *__from, __const char *__to)
      ;




extern ssize_t readlink (__const char *__restrict __path,
    char *__restrict __buf, size_t __len)
      ;




extern int symlinkat (__const char *__from, int __tofd,
        __const char *__to)  ;


extern ssize_t readlinkat (int __fd, __const char *__restrict __path,
      char *__restrict __buf, size_t __len)
      ;



extern int unlink (__const char *__name) ;



extern int unlinkat (int __fd, __const char *__name, int __flag)
     ;



extern int rmdir (__const char *__path) ;



extern __pid_t tcgetpgrp (int __fd) ;


extern int tcsetpgrp (int __fd, __pid_t __pgrp_id) ;






extern char *getlogin (void);







extern int getlogin_r (char *__name, size_t __name_len) ;




extern int setlogin (__const char *__name) ;
       
extern char *optarg;
extern int optind;




extern int opterr;



extern int optopt;
extern int getopt (int ___argc, char *const *___argv, const char *__shortopts)
       ;








extern char *rpl_optarg;
extern int rpl_optind;




extern int rpl_opterr;



extern int rpl_optopt;
extern int rpl_getopt (int *data_flow, int ___argc, char *const *___argv, const char *__shortopts)
       ;



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
       ;
extern int rpl_getopt_long_only (int *data_flow, int ___argc, char * *___argv,
        const char *__shortopts,
               const struct rpl_option *__longopts, int *__longind)
       ;









extern int gethostname (char *__name, size_t __len) ;






extern int sethostname (__const char *__name, size_t __len)
      ;



extern int sethostid (long int __id) ;





extern int getdomainname (char *__name, size_t __len)
      ;
extern int setdomainname (__const char *__name, size_t __len)
      ;





extern int vhangup (void) ;


extern int revoke (__const char *__file)  ;







extern int profil (unsigned short int *__sample_buffer, size_t __size,
     size_t __offset, unsigned int __scale)
     ;





extern int acct (__const char *__name) ;



extern char *getusershell (void) ;
extern void endusershell (void) ;
extern void setusershell (void) ;





extern int daemon (int __nochdir, int __noclose) ;






extern int chroot (__const char *__path)  ;



extern char *getpass (__const char *__prompt) ;
extern int fsync (int __fd);






extern long int gethostid (void);


extern void sync (void) ;





extern int getpagesize (void) __attribute__ ((__const__));




extern int getdtablesize (void) ;
extern int truncate (__const char *__file, __off_t __length)
      ;
extern int ftruncate (int __fd, __off_t __length) ;
extern int brk (void *__addr) ;





extern void *sbrk (intptr_t __delta) ;
extern long int syscall (long int __sysno, ...) ;
extern int fdatasync (int __fildes);
extern char *ctermid (char *__s) ;

       




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




       
       

int open_safer (int *data_flow, char const *, int, ...);
int creat_safer (int *data_flow, char const *, mode_t);


int openat_safer (int *data_flow, int, char const *, int, ...);
DIR *opendir_safer (int *data_flow, const char *name);
int dup_safer (int *data_flow, int);
int fd_safer (int *data_flow, int);
int pipe_safer (int *data_flow, int[2]);


int set_cloexec_flag (int *data_flow, int desc, _Bool value);





int dup_cloexec (int *data_flow, int fd);
       

       
       










_Noreturn void openat_restore_fail (int *data_flow, int);
_Noreturn void openat_save_fail (int *data_flow, int);


       
enum
{
  NOT_AN_INODE_NUMBER = 0
};
enum
{
  _FTS_INODE_SORT_DIR_ENTRIES_THRESHOLD = 10000
};

enum Fts_stat
{
  FTS_NO_STAT_REQUIRED = 1,
  FTS_STAT_REQUIRED = 2
};
static FTSENT *fts_alloc (int *data_flow, FTS *, const char *, size_t) ;
static FTSENT *fts_build (int *data_flow, FTS *, int) ;
static void fts_lfree (int *data_flow, FTSENT *) ;
static void fts_load (int *data_flow, FTS *, FTSENT *) ;
static size_t fts_maxarglen (int *data_flow, char * const *) ;
static void fts_padjust (int *data_flow, FTS *, FTSENT *) ;
static _Bool fts_palloc (int *data_flow, FTS *, size_t) ;
static FTSENT *fts_sort (int *data_flow, FTS *, FTSENT *, size_t) ;
static unsigned short int fts_stat (int *data_flow, FTS *, FTSENT *, _Bool) ;
static int fts_safe_changedir (int *data_flow, FTS *, FTSENT *, int, const char *)
     ;

       


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





extern intmax_t imaxabs (intmax_t __n) __attribute__ ((__const__));


extern imaxdiv_t imaxdiv (intmax_t __numer, intmax_t __denom)
      __attribute__ ((__const__));


extern intmax_t strtoimax (int *data_flow, __const char *__restrict __nptr,
      char **__restrict __endptr, int __base) ;


extern uintmax_t strtoumax (int *data_flow, __const char *__restrict __nptr,
       char ** __restrict __endptr, int __base) ;


extern intmax_t wcstoimax (int *data_flow, __const __gwchar_t *__restrict __nptr,
      __gwchar_t **__restrict __endptr, int __base)
     ;


extern uintmax_t wcstoumax (int *data_flow, __const __gwchar_t *__restrict __nptr,
       __gwchar_t ** __restrict __endptr, int __base)
     ;
__extension__
extern long long int __strtoll_internal (__const char *__restrict __nptr,
      char **__restrict __endptr,
      int __base, int __group)
   ;

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
   ;

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
   ;

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
   ;

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




       
       

struct dev_ino
{
  ino_t st_ino;
  dev_t st_dev;
};


struct cycle_check_state
{
  struct dev_ino dev_ino;
  uintmax_t chdir_counter;
  int magic;
};

void cycle_check_init (int *data_flow, struct cycle_check_state *state);
_Bool cycle_check (int *data_flow, struct cycle_check_state *state, struct stat const *sb);
       
typedef size_t (*Hash_hasher) (int *data_flow, const void *, size_t);
typedef _Bool (*Hash_comparator) (int *data_flow, const void *, const void *);
typedef void (*Hash_data_freer) (int *data_flow, void *);
typedef _Bool (*Hash_processor) (int *data_flow, void *, void *);

struct hash_tuning
  {



    float shrink_threshold;
    float shrink_factor;
    float growth_threshold;
    float growth_factor;
    _Bool is_n_buckets;
  };

typedef struct hash_tuning Hash_tuning;

struct hash_table;

typedef struct hash_table Hash_table;


size_t hash_get_n_buckets (int *data_flow, const Hash_table *) __attribute__ ((__pure__));
size_t hash_get_n_buckets_used (int *data_flow, const Hash_table *) __attribute__ ((__pure__));
size_t hash_get_n_entries (int *data_flow, const Hash_table *) __attribute__ ((__pure__));
size_t hash_get_max_bucket_length (int *data_flow, const Hash_table *) __attribute__ ((__pure__));
_Bool hash_table_ok (int *data_flow, const Hash_table *) __attribute__ ((__pure__));
void hash_print_statistics (int *data_flow, const Hash_table *, FILE *);
void *hash_lookup (int *data_flow, const Hash_table *, const void *);


void *hash_get_first (int *data_flow, const Hash_table *) __attribute__ ((__pure__));
void *hash_get_next (int *data_flow, const Hash_table *, const void *);
size_t hash_get_entries (int *data_flow, const Hash_table *, void **, size_t);
size_t hash_do_for_each (int *data_flow, const Hash_table *, Hash_processor, void *);


size_t hash_string (int *data_flow, const char *, size_t) __attribute__ ((__pure__));
void hash_reset_tuning (int *data_flow, Hash_tuning *);
Hash_table *hash_initialize (int *data_flow, size_t, const Hash_tuning *,
                             Hash_hasher, Hash_comparator,
                             Hash_data_freer) __attribute__ ((__warn_unused_result__));
void hash_clear (int *data_flow, Hash_table *);
void hash_free (int *data_flow, Hash_table *);


_Bool hash_rehash (int *data_flow, Hash_table *, size_t) __attribute__ ((__warn_unused_result__));
void *hash_insert (int *data_flow, Hash_table *, const void *) __attribute__ ((__warn_unused_result__));

int hash_insert_if_absent (int *data_flow, Hash_table *table, const void *entry,
                           const void **matched_ent);
void *hash_delete (int *data_flow, Hash_table *, const void *);


struct Active_dir
{
  dev_t dev;
  ino_t ino;
  FTSENT *fts_ent;
};

static _Bool
AD_compare (int *data_flow, void const *x, void const *y)
{
  struct Active_dir const *ax = x;
  struct Active_dir const *ay = y;
  return ax->ino == ay->ino
      && ax->dev == ay->dev;
}

static size_t
AD_hash (int *data_flow, void const *x, size_t table_size)
{
  struct Active_dir const *ax = x;
  return (uintmax_t) ax->ino % table_size;
}



static _Bool
setup_dir (int *data_flow, FTS *fts)
{
  if (fts->fts_options & (0x0100 | 0x0002))
    {
      enum { HT_INITIAL_SIZE = 31 };
      fts->fts_cycle.ht = hash_initialize (data_flow, HT_INITIAL_SIZE, ((void *)0), AD_hash,
                                           AD_compare, free);
      if (! fts->fts_cycle.ht)
        return 0;
    }
  else
    {
      fts->fts_cycle.state = malloc (sizeof *fts->fts_cycle.state);
      if (! fts->fts_cycle.state)
        return 0;
      cycle_check_init (data_flow, fts->fts_cycle.state);
    }

  return 1;
}



static _Bool
enter_dir (int *data_flow, FTS *fts, FTSENT *ent)
{
  if (fts->fts_options & (0x0100 | 0x0002))
    {
      struct stat const *st = ent->fts_statp;
      struct Active_dir *ad = malloc (sizeof *ad);
      struct Active_dir *ad_from_table;

      if (!ad)
        return 0;

      ad->dev = st->st_dev;
      ad->ino = st->st_ino;
      ad->fts_ent = ent;




      ad_from_table = hash_insert (data_flow, fts->fts_cycle.ht, ad);

      if (ad_from_table != ad)
        {
          free (ad);
          if (!ad_from_table)
            return 0;



          ent->fts_cycle = ad_from_table->fts_ent;
          ent->fts_info = 2;
        }
    }
  else
    {
      if (cycle_check (data_flow, fts->fts_cycle.state, ent->fts_statp))
        {




          ent->fts_cycle = ent;
          ent->fts_info = 2;
        }
    }

  return 1;
}



static void
leave_dir (int *data_flow, FTS *fts, FTSENT *ent)
{
  struct stat const *st = ent->fts_statp;
  if (fts->fts_options & (0x0100 | 0x0002))
    {
      struct Active_dir obj;
      void *found;
      obj.dev = st->st_dev;
      obj.ino = st->st_ino;
      found = hash_delete (data_flow, fts->fts_cycle.ht, &obj);
      if (!found)
        abort ();
      free (found);
    }
  else
    {
      FTSENT *parent = ent->fts_parent;
      if (parent != ((void *)0) && 0 <= parent->fts_level)
        do { if ((fts->fts_cycle.state)->chdir_counter == 0) abort (); if ((((fts->fts_cycle.state)->dev_ino).st_ino == (*st).st_ino && ((fts->fts_cycle.state)->dev_ino).st_dev == (*st).st_dev)) { (fts->fts_cycle.state)->dev_ino.st_dev = (*(parent->fts_statp)).st_dev; (fts->fts_cycle.state)->dev_ino.st_ino = (*(parent->fts_statp)).st_ino; } } while (0)
                                                                ;
    }
}



static void
free_dir (int *data_flow, FTS *sp)
{
  if (sp->fts_options & (0x0100 | 0x0002))
    {
      if (sp->fts_cycle.ht)
        hash_free (data_flow, sp->fts_cycle.ht);
    }
  else
    free (sp->fts_cycle.state);
}
static void
fd_ring_clear (int *data_flow, I_ring *fd_ring)
{
  while ( ! i_ring_empty (data_flow, fd_ring))
    {
      int fd = i_ring_pop (data_flow, fd_ring);
      if (0 <= fd)
        close (fd);
    }
}




static void
fts_set_stat_required (int *data_flow, FTSENT *p, _Bool required)
{
  do { if (!(p->fts_info == 11)) abort (); } while (0);
  p->fts_statp->st_size = (required
                           ? FTS_STAT_REQUIRED
                           : FTS_NO_STAT_REQUIRED);
}



static DIR *

opendirat (int *data_flow, int fd, char const *dir, int extra_flags, int *pdir_fd)
{
  int new_fd = openat_safer (data_flow, fd, dir,
                       (00 | 0200000 | 0400 | 04000
                        | extra_flags));
  DIR *dirp;

  if (new_fd < 0)
    return ((void *)0);
  set_cloexec_flag (data_flow, new_fd, 1);
  dirp = fdopendir (new_fd);
  if (dirp)
    *pdir_fd = new_fd;
  else
    {
      int saved_errno = (*__errno_location ());
      close (new_fd);
      (*__errno_location ()) = saved_errno;
    }
  return dirp;
}






static void

cwd_advance_fd (int *data_flow, FTS *sp, int fd, _Bool chdir_down_one)
{
  int old = sp->fts_cwd_fd;
  do { if (!(old != fd || old == -100)) abort (); } while (0);

  if (chdir_down_one)
    {


      int prev_fd_in_slot = i_ring_push (data_flow, &sp->fts_fd_ring, old);
      ;
      if (0 <= prev_fd_in_slot)
        close (prev_fd_in_slot);
    }
  else if ( ! (sp->fts_options & (0x0004)))
    {
      if (0 <= old)
        close (old);
    }

  sp->fts_cwd_fd = fd;
}





static int
restore_initial_cwd (int *data_flow, FTS *sp)
{
  int fail = (!(sp->fts_options & (0x0004)) && ((sp->fts_options & (0x0200)) ? (cwd_advance_fd (data_flow, (sp), ((sp->fts_options & (0x0200)) ? -100 : sp->fts_rfd), 1), 0) : fchdir ((sp->fts_options & (0x0200)) ? -100 : sp->fts_rfd)));
  fd_ring_clear (data_flow, &(sp->fts_fd_ring));
  return fail;
}





static int

diropen (int *data_flow, FTS const *sp, char const *dir)
{
  int open_flags = (00 | 0200000 | 0400 | 04000
                    | ((sp->fts_options & (0x0010)) ? 0400000 : 0)
                    | ((sp->fts_options & (0x0800)) ? 0 : 0));

  int fd = ((sp->fts_options & (0x0200))
            ? openat_safer (data_flow, sp->fts_cwd_fd, dir, open_flags)
            : open_safer (data_flow, dir, open_flags));
  if (0 <= fd)
    set_cloexec_flag (data_flow, fd, 1);
  return fd;
}

FTS *
fts_open (int *data_flow, char * const *argv,
          register int options,
          int (*compar) (int *data_flow, FTSENT const **, FTSENT const **))
{
        register FTS *sp;
        register FTSENT *p, *root;
        register size_t nitems;
        FTSENT *parent = ((void *)0);
        FTSENT *tmp = ((void *)0);
        _Bool defer_stat;


        if (options & ~0x1fff) {
                (*__errno_location ()) = (22);
                return (((void *)0));
        }
        if ((options & 0x0004) && (options & 0x0200)) {
                (*__errno_location ()) = (22);
                return (((void *)0));
        }
        if ( ! (options & (0x0002 | 0x0010))) {
                (*__errno_location ()) = (22);
                return (((void *)0));
        }


        if ((sp = malloc(sizeof(FTS))) == ((void *)0))
                return (((void *)0));
        memset(sp, 0, sizeof(FTS));
        sp->fts_compar = compar;
        sp->fts_options = options;


        if ((sp->fts_options & (0x0002))) {
                (sp->fts_options |= (0x0004));
                (sp->fts_options &= ~(0x0200));
        }


        sp->fts_cwd_fd = -100;
        if ( (sp->fts_options & (0x0200)) && ! 1)
          {




            int fd = open_safer (data_flow, ".",
                           00 | ((sp->fts_options & (0x0800)) ? 0 : 0));
            if (fd < 0)
              {
                if ( 0)
                  {
                    (sp->fts_options |= (0x0004));
                    (sp->fts_options &= ~(0x0200));
                  }
              }
            else
              {
                close (fd);
              }
          }
        {
          size_t maxarglen = fts_maxarglen(data_flow, argv);
          if (! fts_palloc(data_flow, sp, (((maxarglen)>(4096))?(maxarglen):(4096))))
                  goto mem1;
        }


        if (*argv != ((void *)0)) {
                if ((parent = fts_alloc(data_flow, sp, "", 0)) == ((void *)0))
                        goto mem2;
                parent->fts_level = (-1);
          }
        defer_stat = (compar == ((void *)0) || (sp->fts_options & (0x0400)));


        for (root = ((void *)0), nitems = 0; *argv != ((void *)0); ++argv, ++nitems) {

                size_t len = strlen(*argv);

                if ( ! (options & 0x1000))
                  {


                    char const *v = *argv;
                    if (2 < len && v[len - 1] == '/')
                      while (1 < len && v[len - 2] == '/')
                        --len;
                  }

                if ((p = fts_alloc(data_flow, sp, *argv, len)) == ((void *)0))
                        goto mem3;
                p->fts_level = 0;
                p->fts_parent = parent;
                p->fts_accpath = p->fts_name;



                if (defer_stat && root != ((void *)0)) {
                        p->fts_info = 11;
                        fts_set_stat_required(data_flow, p, 1);
                } else {
                        p->fts_info = fts_stat(data_flow, sp, p, 0);
                }





                if (compar) {
                        p->fts_link = root;
                        root = p;
                } else {
                        p->fts_link = ((void *)0);
                        if (root == ((void *)0))
                                tmp = root = p;
                        else {
                                tmp->fts_link = p;
                                tmp = p;
                        }
                }
        }
        if (compar && nitems > 1)
                root = fts_sort(data_flow, sp, root, nitems);






        if ((sp->fts_cur = fts_alloc(data_flow, sp, "", 0)) == ((void *)0))
                goto mem3;
        sp->fts_cur->fts_link = root;
        sp->fts_cur->fts_info = 9;
        if (! setup_dir (data_flow, sp))
                goto mem3;
        if (!(sp->fts_options & (0x0004)) && !(sp->fts_options & (0x0200))
            && (sp->fts_rfd = diropen (data_flow, sp, ".")) < 0)
                (sp->fts_options |= (0x0004));

        i_ring_init (data_flow, &sp->fts_fd_ring, -1);
        return (sp);

mem3: fts_lfree(data_flow, root);
        free(parent);
mem2: free(sp->fts_path);
mem1: free(sp);
        return (((void *)0));
}

static void

fts_load (int *data_flow, FTS *sp, register FTSENT *p)
{
        register size_t len;
        register char *cp;
        len = p->fts_pathlen = p->fts_namelen;
        memmove(sp->fts_path, p->fts_name, len + 1);
        if ((cp = strrchr(p->fts_name, '/')) && (cp != p->fts_name || cp[1])) {
                len = strlen(++cp);
                memmove(p->fts_name, cp, len + 1);
                p->fts_namelen = len;
        }
        p->fts_accpath = p->fts_path = sp->fts_path;
}

int
fts_close (int *data_flow, FTS *sp)
{
        register FTSENT *freep, *p;
        int saved_errno = 0;






        if (sp->fts_cur) {
                for (p = sp->fts_cur; p->fts_level >= 0;) {
                        freep = p;
                        p = p->fts_link != ((void *)0) ? p->fts_link : p->fts_parent;
                        free(freep);
                }
                free(p);
        }


        if (sp->fts_child)
                fts_lfree(data_flow, sp->fts_child);
        free(sp->fts_array);
        free(sp->fts_path);

        if ((sp->fts_options & (0x0200)))
          {
            if (0 <= sp->fts_cwd_fd)
              if (close (sp->fts_cwd_fd))
                saved_errno = (*__errno_location ());
          }
        else if (!(sp->fts_options & (0x0004)))
          {

            if (fchdir(sp->fts_rfd))
              saved_errno = (*__errno_location ());



            if (close (sp->fts_rfd))
              if (saved_errno == 0)
                saved_errno = (*__errno_location ());
          }

        fd_ring_clear (data_flow, &sp->fts_fd_ring);

        if (sp->fts_leaf_optimization_works_ht)
          hash_free (data_flow, sp->fts_leaf_optimization_works_ht);

        free_dir (data_flow, sp);


        free(sp);


        if (saved_errno) {
                (*__errno_location ()) = (saved_errno);
                return (-1);
        }

        return (0);
}







struct statfs
  {
    int f_type;
    int f_bsize;

    __fsblkcnt_t f_blocks;
    __fsblkcnt_t f_bfree;
    __fsblkcnt_t f_bavail;
    __fsfilcnt_t f_files;
    __fsfilcnt_t f_ffree;







    __fsid_t f_fsid;
    int f_namelen;
    int f_frsize;
    int f_flags;
    int f_spare[4];
  };





extern int statfs (__const char *__file, struct statfs *__buf)
     ;
extern int fstatfs (int __fildes, struct statfs *__buf)
     ;

static _Bool
dirent_inode_sort_may_be_useful (int *data_flow, int dir_fd)
{





  struct statfs fs_buf;


  if (fstatfs (dir_fd, &fs_buf) != 0)
    return 1;



  switch (fs_buf.f_type)
    {
    case 0x1021994:
    case 0x6969:


      return 0;

    default:
      return 1;
    }
}




static _Bool
leaf_optimization_applies (int *data_flow, int dir_fd)
{
  struct statfs fs_buf;


  if (fstatfs (dir_fd, &fs_buf) != 0)
    return 0;




  switch (fs_buf.f_type)
    {


    case 0x52654973:
    case 0x58465342:
      return 1;



    case 0x6969:




      ((void) 0);
    case 0x9FA0:


      ((void) 0);
    default:
      return 0;
    }
}
struct LCO_ent
{
  dev_t st_dev;
  _Bool opt_ok;
};




enum { LCO_HT_INITIAL_SIZE = 13 };

static size_t
LCO_hash (int *data_flow, void const *x, size_t table_size)
{
  struct LCO_ent const *ax = x;
  return (uintmax_t) ax->st_dev % table_size;
}

static _Bool
LCO_compare (int *data_flow, void const *x, void const *y)
{
  struct LCO_ent const *ax = x;
  struct LCO_ent const *ay = y;
  return ax->st_dev == ay->st_dev;
}




static _Bool
link_count_optimize_ok (int *data_flow, FTSENT const *p)
{
  FTS *sp = p->fts_fts;
  Hash_table *h = sp->fts_leaf_optimization_works_ht;
  struct LCO_ent tmp;
  struct LCO_ent *ent;
  _Bool opt_ok;
  struct LCO_ent *t2;



  if (!(sp->fts_options & (0x0200)))
    return 0;


  if (h == ((void *)0))
    {
      h = sp->fts_leaf_optimization_works_ht
        = hash_initialize (data_flow, LCO_HT_INITIAL_SIZE, ((void *)0), LCO_hash,
                           LCO_compare, free);
      if (h == ((void *)0))
        return 0;
    }
  tmp.st_dev = p->fts_statp->st_dev;
  ent = hash_lookup (data_flow, h, &tmp);
  if (ent)
    return ent->opt_ok;


  t2 = malloc (sizeof *t2);
  if (t2 == ((void *)0))
    return 0;


  opt_ok = leaf_optimization_applies (data_flow, sp->fts_cwd_fd);
  t2->opt_ok = opt_ok;
  t2->st_dev = p->fts_statp->st_dev;

  ent = hash_insert (data_flow, h, t2);
  if (ent == ((void *)0))
    {

      free (t2);
      return 0;
    }
  do { if (!(ent == t2)) abort (); } while (0);

  return opt_ok;
}
FTSENT *
fts_read (int *data_flow, register FTS *sp)
{
        register FTSENT *p, *tmp;
        register unsigned short int instr;
        register char *t;


        if (sp->fts_cur == ((void *)0) || (sp->fts_options & (0x4000)))
                return (((void *)0));


        p = sp->fts_cur;


        instr = p->fts_instr;
        p->fts_instr = 3;


        if (instr == 1) {
                p->fts_info = fts_stat(data_flow, sp, p, 0);
                return (p);
        }
       
                                                              ;







        if (instr == 2 &&
            (p->fts_info == 12 || p->fts_info == 13)) {
                p->fts_info = fts_stat(data_flow, sp, p, 1);
                if (p->fts_info == 1 && !(sp->fts_options & (0x0004))) {
                        if ((p->fts_symfd = diropen (data_flow, sp, ".")) < 0) {
                                p->fts_errno = (*__errno_location ());
                                p->fts_info = 7;
                        } else
                                p->fts_flags |= 0x02;
                }
                goto check_for_dir;
        }


        if (p->fts_info == 1) {

                if (instr == 4 ||
                    ((sp->fts_options & (0x0040)) && p->fts_statp->st_dev != sp->fts_dev)) {
                        if (p->fts_flags & 0x02)
                                (void)close(p->fts_symfd);
                        if (sp->fts_child) {
                                fts_lfree(data_flow, sp->fts_child);
                                sp->fts_child = ((void *)0);
                        }
                        p->fts_info = 6;
                        do { ; leave_dir (data_flow, sp, p); ; } while (0);
                        return (p);
                }


                if (sp->fts_child != ((void *)0) && (sp->fts_options & (0x2000))) {
                        (sp->fts_options &= ~(0x2000));
                        fts_lfree(data_flow, sp->fts_child);
                        sp->fts_child = ((void *)0);
                }
                if (sp->fts_child != ((void *)0)) {
                        if (fts_safe_changedir(data_flow, sp, p, -1, p->fts_accpath)) {
                                p->fts_errno = (*__errno_location ());
                                p->fts_flags |= 0x01;
                                for (p = sp->fts_child; p != ((void *)0);
                                     p = p->fts_link)
                                        p->fts_accpath =
                                            p->fts_parent->fts_accpath;
                        }
                } else if ((sp->fts_child = fts_build(data_flow, sp, 3)) == ((void *)0)) {
                        if ((sp->fts_options & (0x4000)))
                                return (((void *)0));



                        if (p->fts_errno && p->fts_info != 4)
                                p->fts_info = 7;
                        do { ; leave_dir (data_flow, sp, p); ; } while (0);
                        return (p);
                }
                p = sp->fts_child;
                sp->fts_child = ((void *)0);
                goto name;
        }


next: tmp = p;




        if (p->fts_link == ((void *)0) && p->fts_parent->fts_dirp)
          {
            p = tmp->fts_parent;
            sp->fts_cur = p;
            sp->fts_path[p->fts_pathlen] = '\0';

            if ((p = fts_build (data_flow, sp, 3)) == ((void *)0))
              {
                if ((sp->fts_options & (0x4000)))
                  return ((void *)0);
                goto cd_dot_dot;
              }

            free(tmp);
            goto name;
          }

        if ((p = p->fts_link) != ((void *)0)) {
                sp->fts_cur = p;
                free(tmp);






                if (p->fts_level == 0) {
                        if (restore_initial_cwd(data_flow, sp)) {
                                (sp->fts_options |= (0x4000));
                                return (((void *)0));
                        }
                        free_dir(data_flow, sp);
                        fts_load(data_flow, sp, p);
                        setup_dir(data_flow, sp);
                        goto check_for_dir;
                }






                if (p->fts_instr == 4)
                        goto next;
                if (p->fts_instr == 2) {
                        p->fts_info = fts_stat(data_flow, sp, p, 1);
                        if (p->fts_info == 1 && !(sp->fts_options & (0x0004))) {
                                if ((p->fts_symfd = diropen (data_flow, sp, ".")) < 0) {
                                        p->fts_errno = (*__errno_location ());
                                        p->fts_info = 7;
                                } else
                                        p->fts_flags |= 0x02;
                        }
                        p->fts_instr = 3;
                }

name: t = sp->fts_path + (p->fts_parent->fts_path[p->fts_parent->fts_pathlen - 1] == '/' ? p->fts_parent->fts_pathlen - 1 : p->fts_parent->fts_pathlen);
                *t++ = '/';
                memmove(t, p->fts_name, p->fts_namelen + 1);
check_for_dir:
                sp->fts_cur = p;
                if (p->fts_info == 11)
                  {
                    if (p->fts_statp->st_size == FTS_STAT_REQUIRED)
                      {
                        FTSENT *parent = p->fts_parent;
                        if (0 < p->fts_level


                            && parent->fts_n_dirs_remaining == 0
                            && (sp->fts_options & (0x0008))
                            && (sp->fts_options & (0x0010))
                            && link_count_optimize_ok (data_flow, parent))
                          {

                          }
                        else
                          {
                            p->fts_info = fts_stat(data_flow, sp, p, 0);
                            if (((((p->fts_statp->st_mode)) & 0170000) == (0040000))
                                && p->fts_level != 0
                                && parent->fts_n_dirs_remaining)
                                  parent->fts_n_dirs_remaining--;
                          }
                      }
                    else
                      do { if (!(p->fts_statp->st_size == FTS_NO_STAT_REQUIRED)) abort (); } while (0);
                  }

                if (p->fts_info == 1)
                  {



                    if (p->fts_level == 0)
                      sp->fts_dev = p->fts_statp->st_dev;
                    ;
                    if (! enter_dir (data_flow, sp, p))
                      {
                        (*__errno_location ()) = (12);
                        return ((void *)0);
                      }
                  }
                return p;
        }
cd_dot_dot:


        p = tmp->fts_parent;
        sp->fts_cur = p;
        free(tmp);

        if (p->fts_level == (-1)) {




                free(p);
                (*__errno_location ()) = (0);
                return (sp->fts_cur = ((void *)0));
        }

        do { if (!(p->fts_info != 11)) abort (); } while (0);


        sp->fts_path[p->fts_pathlen] = '\0';







        if (p->fts_level == 0) {
                if (restore_initial_cwd(data_flow, sp)) {
                        p->fts_errno = (*__errno_location ());
                        (sp->fts_options |= (0x4000));
                }
        } else if (p->fts_flags & 0x02) {
                if ((!(sp->fts_options & (0x0004)) && ((sp->fts_options & (0x0200)) ? (cwd_advance_fd (data_flow, (sp), (p->fts_symfd), 1), 0) : fchdir (p->fts_symfd)))) {
                        p->fts_errno = (*__errno_location ());
                        (sp->fts_options |= (0x4000));
                }
                (void)close(p->fts_symfd);
        } else if (!(p->fts_flags & 0x01) &&
                   fts_safe_changedir(data_flow, sp, p->fts_parent, -1, "..")) {
                p->fts_errno = (*__errno_location ());
                (sp->fts_options |= (0x4000));
        }




        if (p->fts_info != 2) {
                p->fts_info = p->fts_errno ? 7 : 6;
                if (p->fts_errno == 0)
                        do { ; leave_dir (data_flow, sp, p); ; } while (0);
        }
        return (sp->fts_options & (0x4000)) ? ((void *)0) : p;
}
int
fts_set(int *data_flow, FTS *sp __attribute__ ((__unused__)), FTSENT *p, int instr)
{
        if (instr != 0 && instr != 1 && instr != 2 &&
            instr != 3 && instr != 4) {
                (*__errno_location ()) = (22);
                return (1);
        }
        p->fts_instr = instr;
        return (0);
}

FTSENT *
fts_children (int *data_flow, register FTS *sp, int instr)
{
        register FTSENT *p;
        int fd;

        if (instr != 0 && instr != 0x2000) {
                (*__errno_location ()) = (22);
                return (((void *)0));
        }


        p = sp->fts_cur;





        (*__errno_location ()) = (0);


        if ((sp->fts_options & (0x4000)))
                return (((void *)0));


        if (p->fts_info == 9)
                return (p->fts_link);






        if (p->fts_info != 1 )
                return (((void *)0));


        if (sp->fts_child != ((void *)0))
                fts_lfree(data_flow, sp->fts_child);

        if (instr == 0x2000) {
                (sp->fts_options |= (0x2000));
                instr = 2;
        } else
                instr = 1;
        if (p->fts_level != 0 || p->fts_accpath[0] == '/' ||
            (sp->fts_options & (0x0004)))
                return (sp->fts_child = fts_build(data_flow, sp, instr));

        if ((fd = diropen (data_flow, sp, ".")) < 0)
                return (sp->fts_child = ((void *)0));
        sp->fts_child = fts_build(data_flow, sp, instr);
        if ((sp->fts_options & (0x0200)))
          {
            cwd_advance_fd (data_flow, sp, fd, 1);
          }
        else
          {
            if (fchdir(fd))
              {
                int saved_errno = (*__errno_location ());
                close (fd);
                (*__errno_location ()) = (saved_errno);
                return ((void *)0);
              }
            close (fd);
          }
        return (sp->fts_child);
}






static int
fts_compare_ino (int *data_flow, struct _ftsent const **a, struct _ftsent const **b)
{
  return (a[0]->fts_statp->st_ino < b[0]->fts_statp->st_ino ? -1
          : b[0]->fts_statp->st_ino < a[0]->fts_statp->st_ino ? 1 : 0);
}



static void
set_stat_type (int *data_flow, struct stat *st, unsigned int dtype)
{
  mode_t type;
  switch (dtype)
    {
    case DT_BLK:
      type = 0060000;
      break;
    case DT_CHR:
      type = 0020000;
      break;
    case DT_DIR:
      type = 0040000;
      break;
    case DT_FIFO:
      type = 0010000;
      break;
    case DT_LNK:
      type = 0120000;
      break;
    case DT_REG:
      type = 0100000;
      break;
    case DT_SOCK:
      type = 0140000;
      break;
    default:
      type = 0;
    }
  st->st_mode = type;
}
static FTSENT *

fts_build (int *data_flow, register FTS *sp, int type)
{
        register FTSENT *p, *head;
        register size_t nitems;
        FTSENT *tail;
        void *oldaddr;
        int saved_errno;
        _Bool descend;
        _Bool doadjust;
        ptrdiff_t level;
        nlink_t nlinks;
        _Bool nostat;
        size_t len, maxlen, new_len;
        char *cp;
        int dir_fd;
        FTSENT *cur = sp->fts_cur;
        _Bool continue_readdir = !!cur->fts_dirp;
        size_t max_entries;




        if (continue_readdir)
          {
            DIR *dp = cur->fts_dirp;
            dir_fd = dirfd (dp);
            if (dir_fd < 0)
              {
                do { closedir (cur->fts_dirp); cur->fts_dirp = ((void *)0); } while (0);
                if (type == 3)
                  {
                    cur->fts_info = 4;
                    cur->fts_errno = (*__errno_location ());
                  }
                return ((void *)0);
              }
          }
        else
          {


            if ((cur->fts_dirp = opendirat(data_flow, (! (sp->fts_options & (0x0004)) && (sp->fts_options & (0x0200)) ? sp->fts_cwd_fd : -100), cur->fts_accpath, ((((sp->fts_options & (0x0010)) && ! ((sp->fts_options & (0x0001)) && cur->fts_level == 0)) ? 0400000 : 0) | ((sp->fts_options & (0x0800)) ? 0 : 0)), &dir_fd)) == ((void *)0))
              {
                if (type == 3)
                  {
                    cur->fts_info = 4;
                    cur->fts_errno = (*__errno_location ());
                  }
                return ((void *)0);
              }



            if (cur->fts_info == 11)
              cur->fts_info = fts_stat(data_flow, sp, cur, 0);
            else if (sp->fts_options & 0x0100)
              {





                do { ; leave_dir (data_flow, sp, cur); ; } while (0);
                fts_stat (data_flow, sp, cur, 0);
                if (! enter_dir (data_flow, sp, cur))
                  {
                    (*__errno_location ()) = (12);
                    return ((void *)0);
                  }
              }
          }
        max_entries = sp->fts_compar ? (((((0u) + 1) << ((32) ? (32) - 1 - (0) : 0)) - 1) * 2 + 1) : 100000;






        if (type == 2) {
                nlinks = 0;

                nostat = 0;
        } else if ((sp->fts_options & (0x0008)) && (sp->fts_options & (0x0010))) {
                nlinks = (cur->fts_statp->st_nlink
                          - ((sp->fts_options & (0x0020)) ? 0 : 2));
                nostat = 1;
        } else {
                nlinks = -1;
                nostat = 0;
        }
        if (continue_readdir)
          {


            descend = 1;
          }
        else if (nlinks || type == 3) {
                if ((sp->fts_options & (0x0200)))
                  {
                    dir_fd = dup_safer (data_flow, dir_fd);
                    if (0 <= dir_fd)
                      set_cloexec_flag (data_flow, dir_fd, 1);
                  }
                if (dir_fd < 0 || fts_safe_changedir(data_flow, sp, cur, dir_fd, ((void *)0))) {
                        if (nlinks && type == 3)
                                cur->fts_errno = (*__errno_location ());
                        cur->fts_flags |= 0x01;
                        descend = 0;
                        do { closedir (cur->fts_dirp); cur->fts_dirp = ((void *)0); } while (0);
                        if ((sp->fts_options & (0x0200)) && 0 <= dir_fd)
                                close (dir_fd);
                        cur->fts_dirp = ((void *)0);
                } else
                        descend = 1;
        } else
                descend = 0;
        len = (cur->fts_path[cur->fts_pathlen - 1] == '/' ? cur->fts_pathlen - 1 : cur->fts_pathlen);
        if ((sp->fts_options & (0x0004))) {
                cp = sp->fts_path + len;
                *cp++ = '/';
        } else {

                cp = ((void *)0);
        }
        len++;
        maxlen = sp->fts_pathlen - len;

        level = cur->fts_level + 1;


        doadjust = 0;
        head = ((void *)0);
        tail = ((void *)0);
        nitems = 0;
        while (cur->fts_dirp) {
                _Bool is_dir;
                size_t d_namelen;
                (*__errno_location ()) = (0);
                struct dirent *dp = readdir(cur->fts_dirp);
                if (dp == ((void *)0)) {
                        if ((*__errno_location ())) {
                                cur->fts_errno = (*__errno_location ());


                                cur->fts_info = (continue_readdir || nitems)
                                                ? 7 : 4;
                        }
                        break;
                }
                if (!(sp->fts_options & (0x0020)) && (dp->d_name[0] == '.' && (!dp->d_name[1] || (dp->d_name[1] == '.' && !dp->d_name[2]))))
                        continue;

                d_namelen = (strlen ((dp)->d_name));
                p = fts_alloc (data_flow, sp, dp->d_name, d_namelen);
                if (!p)
                        goto mem1;
                if (d_namelen >= maxlen) {

                        oldaddr = sp->fts_path;
                        if (! fts_palloc(data_flow, sp, d_namelen + len + 1)) {





mem1: saved_errno = (*__errno_location ());
                                free(p);
                                fts_lfree(data_flow, head);
                                do { closedir (cur->fts_dirp); cur->fts_dirp = ((void *)0); } while (0);
                                cur->fts_info = 7;
                                (sp->fts_options |= (0x4000));
                                (*__errno_location ()) = (saved_errno);
                                return (((void *)0));
                        }

                        if (oldaddr != sp->fts_path) {
                                doadjust = 1;
                                if ((sp->fts_options & (0x0004)))
                                        cp = sp->fts_path + len;
                        }
                        maxlen = sp->fts_pathlen - len;
                }

                new_len = len + d_namelen;
                if (new_len < len) {






                        free(p);
                        fts_lfree(data_flow, head);
                        do { closedir (cur->fts_dirp); cur->fts_dirp = ((void *)0); } while (0);
                        cur->fts_info = 7;
                        (sp->fts_options |= (0x4000));
                        (*__errno_location ()) = (36);
                        return (((void *)0));
                }
                p->fts_level = level;
                p->fts_parent = sp->fts_cur;
                p->fts_pathlen = new_len;



                p->fts_statp->st_ino = (dp)->d_ino;


                if ((sp->fts_options & (0x0004))) {
                        p->fts_accpath = p->fts_path;
                        memmove(cp, p->fts_name, p->fts_namelen + 1);
                } else
                        p->fts_accpath = p->fts_name;

                if (sp->fts_compar == ((void *)0) || (sp->fts_options & (0x0400))) {
                        _Bool skip_stat = ((sp->fts_options & (0x0010))
                                          && (sp->fts_options & (0x0008))
                                          && ((dp)->d_type != DT_UNKNOWN)
                                          && ! ((dp)->d_type == (DT_DIR)));
                        p->fts_info = 11;


                        set_stat_type (data_flow, p->fts_statp, ((dp)->d_type));
                        fts_set_stat_required(data_flow, p, !skip_stat);
                        is_dir = ((sp->fts_options & (0x0010))
                                  && ((dp)->d_type == (DT_DIR)));
                } else {
                        p->fts_info = fts_stat(data_flow, sp, p, 0);
                        is_dir = (p->fts_info == 1
                                  || p->fts_info == 2
                                  || p->fts_info == 5);
                }


                if (nlinks > 0 && is_dir)
                        nlinks -= nostat;


                p->fts_link = ((void *)0);
                if (head == ((void *)0))
                        head = tail = p;
                else {
                        tail->fts_link = p;
                        tail = p;
                }
                ++nitems;
                if (max_entries <= nitems) {



                        goto break_without_closedir;
                }
        }

        if (cur->fts_dirp)
                do { closedir (cur->fts_dirp); cur->fts_dirp = ((void *)0); } while (0);

 break_without_closedir:





        if (doadjust)
                fts_padjust(data_flow, sp, head);





        if ((sp->fts_options & (0x0004))) {
                if (len == sp->fts_pathlen || nitems == 0)
                        --cp;
                *cp = '\0';
        }
        if (!continue_readdir && descend && (type == 1 || !nitems) &&
            (cur->fts_level == 0
             ? restore_initial_cwd(data_flow, sp)
             : fts_safe_changedir(data_flow, sp, cur->fts_parent, -1, ".."))) {
                cur->fts_info = 7;
                (sp->fts_options |= (0x4000));
                fts_lfree(data_flow, head);
                return (((void *)0));
        }


        if (!nitems) {
                if (type == 3
                    && cur->fts_info != 4 && cur->fts_info != 7)
                        cur->fts_info = 6;
                fts_lfree(data_flow, head);
                return (((void *)0));
        }





        if (nitems > _FTS_INODE_SORT_DIR_ENTRIES_THRESHOLD
            && !sp->fts_compar
            && (sp->fts_options & (0x0200))
            && dirent_inode_sort_may_be_useful (data_flow, sp->fts_cwd_fd)) {
                sp->fts_compar = fts_compare_ino;
                head = fts_sort (data_flow, sp, head, nitems);
                sp->fts_compar = ((void *)0);
        }


        if (sp->fts_compar && nitems > 1)
                head = fts_sort(data_flow, sp, head, nitems);
        return (head);
}
static unsigned short int

fts_stat(int *data_flow, FTS *sp, register FTSENT *p, _Bool follow)
{
        struct stat *sbp = p->fts_statp;
        int saved_errno;

        if (p->fts_level == 0 && (sp->fts_options & (0x0001)))
                follow = 1;






        if ((sp->fts_options & (0x0002)) || follow) {
                if (stat(data_flow, p->fts_accpath, sbp)) {
                        saved_errno = (*__errno_location ());
                        if ((*__errno_location ()) == 2
                            && lstat(data_flow, p->fts_accpath, sbp) == 0) {
                                (*__errno_location ()) = (0);
                                return (13);
                        }
                        p->fts_errno = saved_errno;
                        goto err;
                }
        } else if (fstatat(data_flow, sp->fts_cwd_fd, p->fts_accpath, sbp,
                           0x100)) {
                p->fts_errno = (*__errno_location ());
err: memset(sbp, 0, sizeof(struct stat));
                return (10);
        }

        if (((((sbp->st_mode)) & 0170000) == (0040000))) {
                p->fts_n_dirs_remaining = (sbp->st_nlink
                                           - ((sp->fts_options & (0x0020)) ? 0 : 2));
                if ((p->fts_name[0] == '.' && (!p->fts_name[1] || (p->fts_name[1] == '.' && !p->fts_name[2])))) {

                        return (p->fts_level == 0 ? 1 : 5);
                }

                return (1);
        }
        if (((((sbp->st_mode)) & 0170000) == (0120000)))
                return (12);
        if (((((sbp->st_mode)) & 0170000) == (0100000)))
                return (8);
        return (3);
}

static int
fts_compar (int *data_flow, void const *a, void const *b)
{





  FTSENT const **pa = (FTSENT const **) a;
  FTSENT const **pb = (FTSENT const **) b;
  return pa[0]->fts_fts->fts_compar (data_flow, pa, pb);
}

static FTSENT *

fts_sort (int *data_flow, FTS *sp, FTSENT *head, register size_t nitems)
{
        register FTSENT **ap, *p;
        FTSENT *dummy;
        int (*compare) (int *, void const *, void const *) =
          (data_flow, (sizeof &dummy == sizeof (void *)
            && (long int) &dummy == (long int) (void *) &dummy)
           ? (int (*) (void const *, void const *)) sp->fts_compar
           : fts_compar);
        if (nitems > sp->fts_nitems) {
                FTSENT **a;

                sp->fts_nitems = nitems + 40;
                if ((((((0u) + 1) << ((32) ? (32) - 1 - (0) : 0)) - 1) * 2 + 1) / sizeof *a < sp->fts_nitems
                    || ! (a = realloc (sp->fts_array,
                                       sp->fts_nitems * sizeof *a))) {
                        free(sp->fts_array);
                        sp->fts_array = ((void *)0);
                        sp->fts_nitems = 0;
                        return (head);
                }
                sp->fts_array = a;
        }
        for (ap = sp->fts_array, p = head; p; p = p->fts_link)
                *ap++ = p;
        qsort((void *)sp->fts_array, nitems, sizeof(FTSENT *), compare);
        for (head = *(ap = sp->fts_array); --nitems; ++ap)
                ap[0]->fts_link = ap[1];
        ap[0]->fts_link = ((void *)0);
        return (head);
}

static FTSENT *

fts_alloc (int *data_flow, FTS *sp, const char *name, register size_t namelen)
{
        register FTSENT *p;
        size_t len;





        len = ((__builtin_offsetof (FTSENT, fts_name) + __builtin_offsetof (struct { char __a; FTSENT __b; }, __b) - 1 + (namelen + 1)) & ~ (__builtin_offsetof (struct { char __a; FTSENT __b; }, __b) - 1));
        if ((p = malloc(len)) == ((void *)0))
                return (((void *)0));


        memcpy(p->fts_name, name, namelen);
        p->fts_name[namelen] = '\0';

        p->fts_namelen = namelen;
        p->fts_fts = sp;
        p->fts_path = sp->fts_path;
        p->fts_errno = 0;
        p->fts_dirp = ((void *)0);
        p->fts_flags = 0;
        p->fts_instr = 3;
        p->fts_number = 0;
        p->fts_pointer = ((void *)0);
        return (p);
}

static void

fts_lfree (int *data_flow, register FTSENT *head)
{
        register FTSENT *p;


        while ((p = head)) {
                head = head->fts_link;
                if (p->fts_dirp)
                        closedir (p->fts_dirp);
                free(p);
        }
}
static _Bool

fts_palloc (int *data_flow, FTS *sp, size_t more)
{
        char *p;
        size_t new_len = sp->fts_pathlen + more + 256;




        if (new_len < sp->fts_pathlen) {
                free(sp->fts_path);
                sp->fts_path = ((void *)0);
                (*__errno_location ()) = (36);
                return 0;
        }
        sp->fts_pathlen = new_len;
        p = realloc(sp->fts_path, sp->fts_pathlen);
        if (p == ((void *)0)) {
                free(sp->fts_path);
                sp->fts_path = ((void *)0);
                return 0;
        }
        sp->fts_path = p;
        return 1;
}





static void

fts_padjust (int *data_flow, FTS *sp, FTSENT *head)
{
        FTSENT *p;
        char *addr = sp->fts_path;
        for (p = sp->fts_child; p; p = p->fts_link)
                do { if ((p)->fts_accpath != (p)->fts_name) { (p)->fts_accpath = (char *)addr + ((p)->fts_accpath - (p)->fts_path); } (p)->fts_path = addr; } while (0);


        for (p = head; p->fts_level >= 0;) {
                do { if ((p)->fts_accpath != (p)->fts_name) { (p)->fts_accpath = (char *)addr + ((p)->fts_accpath - (p)->fts_path); } (p)->fts_path = addr; } while (0);
                p = p->fts_link ? p->fts_link : p->fts_parent;
        }
}

static size_t
 __attribute__ ((__pure__))
fts_maxarglen (int *data_flow, char * const *argv)
{
        size_t len, max;

        for (max = 0; *argv; ++argv)
                if ((len = strlen(*argv)) > max)
                        max = len;
        return (max + 1);
}
static int

fts_safe_changedir (int *data_flow, FTS *sp, FTSENT *p, int fd, char const *dir)
{
        int ret;
        _Bool is_dotdot = dir && (__extension__ ({ size_t __s1_len, __s2_len; (__builtin_constant_p (dir) && __builtin_constant_p ("..") && (__s1_len = __builtin_strlen (dir), __s2_len = __builtin_strlen (".."), (!((size_t)(const void *)((dir) + 1) - (size_t)(const void *)(dir) == 1) || __s1_len >= 4) && (!((size_t)(const void *)(("..") + 1) - (size_t)(const void *)("..") == 1) || __s2_len >= 4)) ? __builtin_strcmp (dir, "..") : (__builtin_constant_p (dir) && ((size_t)(const void *)((dir) + 1) - (size_t)(const void *)(dir) == 1) && (__s1_len = __builtin_strlen (dir), __s1_len < 4) ? (__builtin_constant_p ("..") && ((size_t)(const void *)(("..") + 1) - (size_t)(const void *)("..") == 1) ? __builtin_strcmp (dir, "..") : (__extension__ ({ __const unsigned char *__s2 = (__const unsigned char *) (__const char *) (".."); register int __result = (((__const unsigned char *) (__const char *) (dir))[0] - __s2[0]); if (__s1_len > 0 && __result == 0) { __result = (((__const unsigned char *) (__const char *) (dir))[1] - __s2[1]); if (__s1_len > 1 && __result == 0) { __result = (((__const unsigned char *) (__const char *) (dir))[2] - __s2[2]); if (__s1_len > 2 && __result == 0) __result = (((__const unsigned char *) (__const char *) (dir))[3] - __s2[3]); } } __result; }))) : (__builtin_constant_p ("..") && ((size_t)(const void *)(("..") + 1) - (size_t)(const void *)("..") == 1) && (__s2_len = __builtin_strlen (".."), __s2_len < 4) ? (__builtin_constant_p (dir) && ((size_t)(const void *)((dir) + 1) - (size_t)(const void *)(dir) == 1) ? __builtin_strcmp (dir, "..") : (__extension__ ({ __const unsigned char *__s1 = (__const unsigned char *) (__const char *) (dir); register int __result = __s1[0] - ((__const unsigned char *) (__const char *) (".."))[0]; if (__s2_len > 0 && __result == 0) { __result = (__s1[1] - ((__const unsigned char *) (__const char *) (".."))[1]); if (__s2_len > 1 && __result == 0) { __result = (__s1[2] - ((__const unsigned char *) (__const char *) (".."))[2]); if (__s2_len > 2 && __result == 0) __result = (__s1[3] - ((__const unsigned char *) (__const char *) (".."))[3]); } } __result; }))) : __builtin_strcmp (dir, "..")))); }) == 0);
        int newfd;





        if ((sp->fts_options & (0x0004)))
          {
            if ((sp->fts_options & (0x0200)) && 0 <= fd)
              close (fd);
            return 0;
          }

        if (fd < 0 && is_dotdot && (sp->fts_options & (0x0200)))
          {





            if ( ! i_ring_empty (data_flow, &sp->fts_fd_ring))
              {
                int parent_fd;
                ;
                parent_fd = i_ring_pop (data_flow, &sp->fts_fd_ring);
                is_dotdot = 1;
                if (0 <= parent_fd)
                  {
                    fd = parent_fd;
                    dir = ((void *)0);
                  }
              }
          }

        newfd = fd;
        if (fd < 0 && (newfd = diropen (data_flow, sp, dir)) < 0)
          return -1;
        if ((sp->fts_options & (0x0002)) || ! 1
            || (dir && (__extension__ ({ size_t __s1_len, __s2_len; (__builtin_constant_p (dir) && __builtin_constant_p ("..") && (__s1_len = __builtin_strlen (dir), __s2_len = __builtin_strlen (".."), (!((size_t)(const void *)((dir) + 1) - (size_t)(const void *)(dir) == 1) || __s1_len >= 4) && (!((size_t)(const void *)(("..") + 1) - (size_t)(const void *)("..") == 1) || __s2_len >= 4)) ? __builtin_strcmp (dir, "..") : (__builtin_constant_p (dir) && ((size_t)(const void *)((dir) + 1) - (size_t)(const void *)(dir) == 1) && (__s1_len = __builtin_strlen (dir), __s1_len < 4) ? (__builtin_constant_p ("..") && ((size_t)(const void *)(("..") + 1) - (size_t)(const void *)("..") == 1) ? __builtin_strcmp (dir, "..") : (__extension__ ({ __const unsigned char *__s2 = (__const unsigned char *) (__const char *) (".."); register int __result = (((__const unsigned char *) (__const char *) (dir))[0] - __s2[0]); if (__s1_len > 0 && __result == 0) { __result = (((__const unsigned char *) (__const char *) (dir))[1] - __s2[1]); if (__s1_len > 1 && __result == 0) { __result = (((__const unsigned char *) (__const char *) (dir))[2] - __s2[2]); if (__s1_len > 2 && __result == 0) __result = (((__const unsigned char *) (__const char *) (dir))[3] - __s2[3]); } } __result; }))) : (__builtin_constant_p ("..") && ((size_t)(const void *)(("..") + 1) - (size_t)(const void *)("..") == 1) && (__s2_len = __builtin_strlen (".."), __s2_len < 4) ? (__builtin_constant_p (dir) && ((size_t)(const void *)((dir) + 1) - (size_t)(const void *)(dir) == 1) ? __builtin_strcmp (dir, "..") : (__extension__ ({ __const unsigned char *__s1 = (__const unsigned char *) (__const char *) (dir); register int __result = __s1[0] - ((__const unsigned char *) (__const char *) (".."))[0]; if (__s2_len > 0 && __result == 0) { __result = (__s1[1] - ((__const unsigned char *) (__const char *) (".."))[1]); if (__s2_len > 1 && __result == 0) { __result = (__s1[2] - ((__const unsigned char *) (__const char *) (".."))[2]); if (__s2_len > 2 && __result == 0) __result = (__s1[3] - ((__const unsigned char *) (__const char *) (".."))[3]); } } __result; }))) : __builtin_strcmp (dir, "..")))); }) == 0)))
          {
            struct stat sb;
            if (fstat(data_flow, newfd, &sb))
              {
                ret = -1;
                goto bail;
              }
            if (p->fts_statp->st_dev != sb.st_dev
                || p->fts_statp->st_ino != sb.st_ino)
              {
                (*__errno_location ()) = (2);
                ret = -1;
                goto bail;
              }
          }

        if ((sp->fts_options & (0x0200)))
          {
            cwd_advance_fd (data_flow, sp, newfd, ! is_dotdot);
            return 0;
          }

        ret = fchdir(newfd);
bail:
        if (fd < 0)
          {
            int oerrno = (*__errno_location ());
            (void)close(newfd);
            (*__errno_location ()) = (oerrno);
          }
        return ret;
}
