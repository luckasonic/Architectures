    .model SMALL
    .STACK 100H
    .data
    oneChar db 1 DUP(0) ;space for one character
    Buffer db 255 DUP(0) ;space for 255 characters
    VAR db 255 DUP(0) ;space for a 255 character substring
    numParams db 255
    ASCcr equ 13   ; ASCII value for carriage return
    ASClf equ 10   ; ASCII value for line feed
    ASCnull equ 0   ; null terminator

    .code
  MOV ax,@DATA
    MOV es,ax

  
    call fillCommandLine
    call readFile

  

    mov ax,es
    mov ds,ax




    print_int PROC
    mov bx, 10          ; Base 10
    mov di, 10          ; Digit place (start from 10^9)
    xor cx, cx          ; Counter for digits
print_loop:
    xor dx, dx          ; Clear dx
    div bx              ; Divide ax by 10, result in ax, remainder in dx
    push dx             ; Push remainder onto stack
    inc cx              ; Increment digit counter
    test ax, ax         ; Check if quotient is 0
    jnz print_loop      ; If not zero, continue loop

print_digits:
    pop dx              ; Pop digit from stack
    add dl, '0'         ; Convert digit to ASCII
    mov ah, 02h         ; DOS function to print character
    int 21h             ; Print character
    loop print_digits   ; Loop until all digits are printed
    ret
print_int ENDP
     

    fillCommandLine PROC
   mov ax, @DATA
   mov es, AX
   mov si, 81h
   lea di, VAR 
   mov cl, [si-1]
   xor ch, ch
   inc si
   jcxz exit
   cld
   rep movsb

   
exit:
    
 ENDP fillCommandLine
 readFile PROC
    mov si, offset Buffer
read_next:
    mov ah, 3Fh
    mov bx, 0h               ; stdin handle
    mov cx, 1                ; 1 byte to read
    mov dx, offset oneChar   ; read to ds:dx 
    int 21h                  ;  ax = number of bytes read
    or ax,ax
    jz bye
    mov bl, oneChar
    mov es:[si], bl
    inc si
    jmp read_next
bye:
    ret
readFile ENDP
StrPos PROC
    push    ax              ; Save modified registers
    push    bx
    push    cx
    push    di
    push    si

    call    StrLength       ; Find length of target string
    mov     ax, cx          ; Save length(s2) in ax
    xchg    si, di          ; Swap si and di
    call    StrLength       ; Find length of substring
    mov     bx, cx          ; Save length(s1) in bx
    xchg    si, di          ; Restore si and di
    sub     ax, bx          ; ax = last possible index
    jb      q30             ; Exit if len target < len substring
    mov     dx, 0           ; Initialize dx to 0 (counter)
    jmp     q11             ; Jump to the loop

q11:
    pop si
    push si
q10:
    inc     dx              ; Increment the counter
    mov     cx, bx          ; Reload substring length into cx
    mov     di, si          ; Reset di to start of substring
q12:
    mov     cl, BYTE PTR[di]      
    mov     ch, BYTE PTR[si]      
    cmp     ch, "$"
    je      q20
    cmp     cl, "$"
    je      q30
    cmp     cl, ASCcr
    je      q30
    cmp     cl, ASClf
    je      q30
    cmp     cl, ASCnull
    je      q30
    cmp     ch, cl         ; Compare characters
    jne     q21             ; If not equal, jump to q21
    inc     di              ; Move to the next character in substring
    inc     si              ; Move to the next character in target string
    loop    q12             ; Repeat for the length of the substring
    jmp     qwer            ; Jump to qwer if full substring is matched

q21:
    pop     si              ; Restore si
    inc     si              ; Move to the next character in target string
    jmp     q10             ; Repeat for the length of the target string

qwer:
    inc     dx              ; Increment the counter one more time for the match found
    jmp     q10             ; Continue searching for the next occurrence
q20:
    pop     si              ; Restore si
    pop     di              ; Restore registers
    pop     cx
    pop     bx
    pop     ax
    ret                     ; Return to caller

q30:
    pop     si
    mov     ax, dx          ; Move the count to ax for return
    pop     di              ; Restore registers
    pop     cx
    pop     bx
    pop     ax
    ret                     ; Return to caller

StrPos ENDP

StrLength PROC
     push di
     push si
     xor     cx, cx          ; Set cx to 0 for counting
count_loop:
    cmp     byte ptr [di], '$' ; Check for null terminator
    je      end_count       ; If null terminator found, exit loop
    inc     cx              ; Increment count
    inc     di              ; Move to the next character
    jmp     count_loop      ; Repeat until null terminator found
end_count: 
   ;dec cx
   pop si
   pop di
   ret                     ; Return to caller
StrLength ENDP




 end
