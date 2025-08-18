import java.io.*;

// ✅ Custom Exception for Invalid Product Data
class InvalidProductDataException extends Exception {
    public InvalidProductDataException(String message) {
        super(message);
    }
}

public class ExceptionProgram {
    public static void main(String[] args) {
        String inputFile = "products.csv";
        String outputFile = "filtered_products.csv";

        // Step 1: Create sample products.csv with valid & invalid entries
        createSampleCSV(inputFile);

        BufferedReader br = null;
        FileWriter fw = null;

        try {
            // Step 2: Wrap file reading in try-catch
            br = new BufferedReader(new FileReader(inputFile));
            fw = new FileWriter(outputFile);

            String line;
            StringBuilder result = new StringBuilder();

            while ((line = br.readLine()) != null) {
                try {
                    String[] parts = line.split(",");
                    if (parts.length < 2) {
                        throw new InvalidProductDataException("❌ Invalid row: " + line);
                    }

                    String name = parts[0].trim();
                    double price;

                    try {
                        price = Double.parseDouble(parts[1].trim());
                    } catch (NumberFormatException e) {
                        throw new InvalidProductDataException("❌ Invalid price for product: " + line);
                    }

                    if (price > 1000) {
                        result.append(name).append(",").append(price).append("\n");
                    }

                } catch (InvalidProductDataException e) {
                    // Print custom error message but continue processing
                    System.out.println(e.getMessage());
                }
            }

            fw.write(result.toString());
            System.out.println("\n✅ Filtered products written to " + outputFile);

        } catch (FileNotFoundException e) {
            System.out.println("❌ Input file not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("❌ IO Error: " + e.getMessage());
        } finally {
            // Step 5: Safely close resources
            try {
                if (br != null) br.close();
                if (fw != null) fw.close();
            } catch (IOException e) {
                System.out.println("❌ Error closing resources: " + e.getMessage());
            }
        }

        // Step 6: Print new file contents for verification
        System.out.println("\n--- Filtered Products ---");
        try (BufferedReader verifyBr = new BufferedReader(new FileReader(outputFile))) {
            verifyBr.lines().forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper method: create sample CSV with valid & invalid rows
    private static void createSampleCSV(String filename) {
        String[] products = {
            "Laptop,55000","Mouse,abc","Keyboard,1200","Phone,35000","InvalidRow",
            "Monitor,8000","Table,XYZ","Charger,900","Desk,5000","Chair,2500",
            "Book,600","Cycle,12000","Sofa,45000","Watch,notANumber","Bed,55000"
        };

        try (FileWriter fw = new FileWriter(filename)) {
            for (String product : products) {
                fw.write(product + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
