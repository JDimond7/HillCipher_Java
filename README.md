# HillCipher in Java

This is a reimplementation of my other program (in Racket), with some differences. The Racket application has two text areas, 
one for plaintext and the other for ciphertext, while this one has only one text area. The programs also handle newlines differently, 
so they are not compatible! The Racket one ignores newlines (it converts a paragraph into a string, newlines replaced with a space), 
while this one treats it as any other character. Consequently, one-line text may be encrypted into something multi-line, and vice versa. 
