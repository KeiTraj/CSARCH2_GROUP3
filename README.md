<<<<<<< HEAD
# Full Associative + MRU
=======
## CSARCH2-Cache-Simulation-Project---Group2
 The **Cache Simulation Project** is designed to give an overview on the scenarios of the assigned cache mapping and replacement policy. This project contains various test case scenarios to analyze and differentiate each case.
 
 ## Specifications
 * Cache line = 16 words
 * Number of cache blocks = 32 blocks
 * Read Policy: non load-through
 * Number of memory blocks = minimum of 1024
 * Full Associative + MRU

## How to run the program
 1. Download the this program into ZIP file. 
 2. Extract the ZIP file and open the file with any IDE that is suitable for editing and runningg java applications.
 3. Run the CacheGUI.java to run the program. This will display the pop-up window of the program.
 
 ## Test cases
 1. Sequential sequence: up to 2n cache block. Repeat the sequence four times. Example: 0,1,2,3,...,63 {4x}
    ![Screenshot 2025-03-22 171021](https://github.com/user-attachments/assets/e448d46c-9260-48cc-a0ee-bbb9a847f54f)
    
 2. Random sequence: containing 4n main memory blocks.
    ![Screenshot 2025-03-22 171258](https://github.com/user-attachments/assets/5ed4cb04-d59c-4361-89f9-1614fae2fc03)

 3. Mid-repeat blocks: Start at block 0, repeat the sequence in the middle two times up to n-1 blocks, after
   which continue up to 2n. Then, repeat the sequence four times. Example: if n=8, sequence=0, 1,2,3,4,5,6,7
   1,2,3,4,5,6,7 8,9,10,11,12,13,14,15 {4x}
   ![Screenshot 2025-03-22 171537](https://github.com/user-attachments/assets/a518e55a-ce66-40c5-b5b1-343a6687e16e)

>>>>>>> 9d9f21f3f7ad4ac4443639a9ec86b14aa9f4cb02
