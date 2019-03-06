(module
 (table 0 anyfunc)
 (memory $0 1)
 (data (i32.const 16) "__flag_here!!__\00")
 (data (i32.const 48) "__fail_message!!__\00")
 (export "memory" (memory $0))
 (export "_Z4calci" (func $_Z4calci))
 (func $_Z4calci (; 0 ;) (param $0 i32) (result i32)
  (select
   (i32.const 16)
   (i32.const 48)
   (i32.eq
    (i32.mul
     (tee_local $0
      (i32.rem_s
       (get_local $0)
       (i32.const 432)
      )
     )
     (get_local $0)
    )
    (i32.const 106929)
   )
  )
 )
)
