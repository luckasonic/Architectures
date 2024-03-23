    .model SMALL
    .STACK 100H
    .data
    msg DB "$"
    oneChar db 1 DUP(0) ;space for one character
    Buffer db 255 DUP(0) ;space for 255 characters
    substring db 255 DUP(0) ;space for a 255 character substring
    Filename db "idk.in",0
    Handle dw "?"
    ASCcr equ 13   ; ASCII value for carriage return
    ASClf equ 10   ; ASCII value for line feed

    .code
    mov ax, @DATA
    mov ds, ax
    mov si, offset substring
    lea dx, msg
    MOV AH,9             
    INT 21H

    COME:   
    MOV AH,1
    INT 21H             

    CMP AL,13            
    JE HERE
    MOV [SI],AL
    INC SI               
    JMP COME  


HERE:
MOV AH,2
MOV DL,0DH
INT 21H           
MOV DL,0AH
INT 21H
    mov dx, offset Filename
    mov al, 0
    mov ah, 3dh
    int 21h
    mov Handle, ax
    xor cx, cx
    read_next:
    mov dx, offset oneChar
    mov bx, Handle
    push cx
    mov cx, 1
    mov ah, 3Fh
    int 21H
    pop cx
    cmp oneChar, 0Dh
    jne continue_reading
    call count_occurrences
    xor cx, cx
    jmp read_next
    continue_reading:
    push ax
    mov al, oneChar
    push bx
    mov bx, cx
    mov [Buffer+bx], al
    pop bx
    pop ax
    inc cx
    or ax,ax ;is it the end of the file
    jnz read_next ;not the end -> reading next byte
    mov bx, Handle
    mov ah, 3Eh
    int 21h
