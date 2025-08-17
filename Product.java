import java.io.*;


public class Product {
    public static void main(String[] args) {
        String inputFile = "products.csv";
        String outputFile = "filtered_products.csv";

        // Step 1: Create sample products.csv with 40 entries
        createSampleCSV(inputFile);

        // Step 2-6: Read, filter, and write
        try (
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            FileWriter fw = new FileWriter(outputFile)
        ) {
            String line;
            StringBuilder result = new StringBuilder();

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 2) continue; // skip invalid lines

                String name = parts[0].trim();
                double price = Double.parseDouble(parts[1].trim());

                if (price > 1000) {
                    result.append(name).append(",").append(price).append("\n");
                }
            }

            fw.write(result.toString());
            System.out.println("✅ Filtered products written to " + outputFile);

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Print new file contents for verification
        System.out.println("\n--- Filtered Products ---");
        try (BufferedReader br = new BufferedReader(new FileReader(outputFile))) {
            br.lines().forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper method to create a sample CSV with 40+ entries
    private static void createSampleCSV(String filename) {
        String[] products = {
            "Laptop,55000","Mouse,500","Keyboard,1200","Phone,35000","Pen,50",
            "Monitor,8000","Tablet,15000","Charger,900","Desk,5000","Chair,2500",
            "Printer,12000","Scanner,7000","Speaker,3000","Headphones,2200","Camera,45000",
            "Smartwatch,12000","USB Cable,200","Power Bank,1800","Projector,55000","Router,3500",
            "Fan,2300","AC,40000","TV,60000","Washing Machine,35000","Fridge,50000",
            "Microwave,12000","Oven,14000","Mixer,2500","Toaster,1500","Juicer,1800",
            "Shoes,3000","Jacket,4500","T-shirt,800","Jeans,2200","Bag,3500",
            "Book,600","Cycle,12000","Bike Helmet,2500","Car Tyre,7000","Sofa,45000",
            "Bed,55000","Cup,100","Watch,3000","Glasses,1200","Drill Machine,5000"
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
