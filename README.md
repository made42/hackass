# hackass

Assembler for the Hack computer built in the Nand to Tetris courses.

**Usage:** The Hack assembler accepts a single command-line argument, as follows,

``prompt>HackAssembler Prog.asm``

where the input file ``Prog.asm`` contains assembly instructions (the ``.asm`` extension is mandatory). The file name
may contain a file path. If no path is specified, the assembler operatos on the current folder. The assembler creates an
output file named ``Prog.hack`` and writes the translated binary instructions into it. The output file is created in the
same folder as the input file. If there is a file by this name in the folder, it will be overwritten.
