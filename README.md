# EVoting_Graduate_Exercice
Ξεκινώντας ανοίγω το netbeans πηγαίνω στα services και 3εκιναω την βάση  και κάνω connect. Έχω βάλει μια main class που από εκεί ξεκάνει η εφαρμογή. 
Μέσα στην main κάνω import  τα 3 κομμάτια του java rmi , μια κλάση server, άλλη μια κλάση για τον client και ένα συνδετικό interface.
Επίσης βάζω  μια κλάση για το οπτικό κομμάτι της εφαρμογής.Στην main  υπάρχει ο κώδικας για το εάν η εφαρμογή τρέξει σαν σερβερ η σαν client.
Στην αρχή την τρέχουμε σαν σερβερ ανοίγοντας το cmd και μπαίνοντας στο παθ του project  στο εκτελέσιμο εικονίδιο
C:\Users\kosma\Documents\NetBeansProjects\EVoting\dist 
και μετά γραφούμε  java -jar EVoting.jar –server για να ξεκινήσει οσερβερ , αφού ξεκινήσει τρέχοντας από το netbeans τον κώδικα ανοίγει σαν client.
Στον κώδικα έχω χρησιμοποιήσει πολλές φορές το try-catch Έτσι εάν υπάρχει πρόβλημα  πετάει ένα exception ,
επίσης έχω μια if –else  μέσα στο try για να κάνει την σύνδεση η σαν σερβερ η σαν  client και να συνεχίσει στο οπτικό κομμάτι.
Για το rmi , η βάση δεδομένων ενώνεται μόνο με τον server ,μέσα στον σερβερ έχουμε καλούμε με τους τύπους τους τις απαντήσεις από την βάση δεδομένων. 
Στο interface δηλώνουμε τον τύπο δεδομένου  από τα δεδομένα  των table που περνούμε με την VoteEntity και την UserEntity. 
Το Userentity είναι για το πρώτο πινάκα και έχω δώσει ένα string για το ID της σύνδεση και έναν αριθμό τύπου long για να μπορέσω να την συνδέσω
με το δεύτερο πινάκα που θα κραταει τους ψήφους.Επειδή είχα ένα πρόβλημα με την απλή δημιουργία  των πινάκων τα έκανα με κώδικα  είναι στο db-schema_init.sql.
Στο db τώρα κάνουμε την ένωση με την βάση , αυτό το έκανα στατικό για να μην το καλεί συνεχεία. 
Πιο κάτω στην db έχουμε το find Users αυτό βρίσκει τα ονόματα που εγώ είχα βάλει (αυτό έγινε χειροκίνητα χωρίς κώδικα) και καλό την κλάση User Entity.
Στην συνεχεία έχουμε τους ελέγχους εάν έχει ψηφήσει κάποιος ,γίνεται ο έλεγχος με το api βρίσκοντας τον χρηστή εάν έχει ψηφήσει ,
τι ψήφησε  και βάζουμε την ψήφο του καθενός  από τους χρήστης σε τελικό πινάκα.
Σε όλους αυτούς τους ελέγχους και στην εισχώρηση των δεδομένων έχω βάλει try catch μήπως και κάτι δεν πάει καλά να πετάξει σφάλμα. 
Τέλος έχουμε την φόρμα του login που εκεί έχουμε το οπτικό κομμάτι κάνει έναν έλεγχο εάν ο χρήστης  είναι γραμμένος στην  βάση και βγαζει το ανάλογο μήνυμα.
Έχουμε και ένα ρεσετ και εχιτ κουμπι. Εάν ο χρηστης  είναι στη βάση  και δεν έχει ψηφήσει τον προχώρα στην τελική φόρμα.
Εάν είναι στην βάση και έχει ψηφήσει στον κώδικα της τελικής φόρμας  γίνεται ένας έλεγχος εάν έχει ψήφο στην βάση και του πεταει το αντίστοιχο μήνυμα πριν συνδεθεί.
Τέλος μπορεί να ψηφήσει μόνο μια φορά επιλέγοντας το radiobuttom που θέλει ,καταχωρείτε η απάντηση του σαν αριθμός από το 1-4 και πατώντας το αντίστοιχο μήνυμα 
του λέει ότι ψήφησε και τον επιστρέφει στην αρχή της εφαρμογής.