import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Task 6: Interactive Web Scraping Program
 * Cognifyz Technologies - Software Development Internship (Level 3)
 *
 * Objective: Fetch data from websites interactively and present extracted elements
 * (titles, headings, quotes, links, prices) in a clean, user-friendly format.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=================================================");
        System.out.println("     COGNIFYZ INTERACTIVE WEB SCRAPER (TASK 6)   ");
        System.out.println("=================================================");

        while (running) {
            System.out.println("\nSelect Scraper Presets or Custom URL:");
            System.out.println("1. Scrape Famous Quotes & Authors (quotes.toscrape.com)");
            System.out.println("2. Scrape Wikipedia Tech Article Headings (en.wikipedia.org)");
            System.out.println("3. Scrape Custom Target URL (Extract Titles & Links)");
            System.out.println("4. Exit");
            System.out.println("-------------------------------------------------");
            System.out.print("Enter choice (1-4): ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid choice! Enter a number between 1 and 4.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 4) {
                System.out.println("Exiting Web Scraper. Thank you!");
                running = false;
                break;
            }

            String targetUrl = "";
            switch (choice) {
                case 1:
                    targetUrl = "https://quotes.toscrape.com/";
                    scrapeQuotes(targetUrl);
                    break;
                case 2:
                    targetUrl = "https://en.wikipedia.org/wiki/Java_(programming_language)";
                    scrapeWikipediaHeadings(targetUrl);
                    break;
                case 3:
                    System.out.print("Enter Target Website URL (e.g. https://example.com): ");
                    targetUrl = scanner.nextLine().trim();
                    if (!targetUrl.startsWith("http://") && !targetUrl.startsWith("https://")) {
                        targetUrl = "https://" + targetUrl;
                    }
                    scrapeCustomUrl(targetUrl);
                    break;
                default:
                    System.out.println("Invalid option. Please choose between 1 and 4.");
            }
        }
        scanner.close();
    }

    private static String fetchHtml(String urlString) {
        StringBuilder htmlContent = new StringBuilder();
        try {
            URI uri = new URI(urlString);
            URL url = uri.toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) JavaScraper/1.0");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);

            int status = conn.getResponseCode();
            if (status != 200) {
                System.out.println(" HTTP Warning: Received status code " + status);
            }

            try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    htmlContent.append(inputLine).append("\n");
                }
            }
        } catch (Exception e) {
            System.err.println(" ERROR fetching URL [" + urlString + "]: " + e.getMessage());
            return null;
        }
        return htmlContent.toString();
    }

    private static void scrapeQuotes(String url) {
        System.out.println("\n[SCRAPING] Connecting to " + url + " ...");
        String html = fetchHtml(url);
        if (html == null || html.isEmpty()) return;

        Pattern quotePattern = Pattern.compile("<span class=\"text\" itemprop=\"text\">(.*?)</span>");
        Pattern authorPattern = Pattern.compile("<small class=\"author\" itemprop=\"author\">(.*?)</small>");

        Matcher qMatcher = quotePattern.matcher(html);
        Matcher aMatcher = authorPattern.matcher(html);

        List<String> quotes = new ArrayList<>();
        List<String> authors = new ArrayList<>();

        while (qMatcher.find()) {
            quotes.add(cleanText(qMatcher.group(1)));
        }
        while (aMatcher.find()) {
            authors.add(cleanText(aMatcher.group(1)));
        }

        System.out.println("\n=================================================");
        System.out.println("         EXTRACTED QUOTES & AUTHORS             ");
        System.out.println("=================================================");
        int count = Math.min(quotes.size(), authors.size());
        for (int i = 0; i < count; i++) {
            System.out.printf("%2d. %s\n    - Author: %s\n\n", (i + 1), quotes.get(i), authors.get(i));
        }
        System.out.println("Total Extracted: " + count + " quotes.");
    }

    private static void scrapeWikipediaHeadings(String url) {
        System.out.println("\n[SCRAPING] Connecting to Wikipedia page...");
        String html = fetchHtml(url);
        if (html == null || html.isEmpty()) return;

        Pattern headingPattern = Pattern.compile("<h[23][^>]*><span class=\"mw-headline\"[^>]*>(.*?)</span></h[23]>");
        Matcher matcher = headingPattern.matcher(html);

        System.out.println("\n=================================================");
        System.out.println("     EXTRACTED WIKIPEDIA SECTION HEADINGS        ");
        System.out.println("=================================================");
        int count = 0;
        while (matcher.find()) {
            count++;
            String heading = cleanText(matcher.group(1));
            System.out.printf(" %2d. %s\n", count, heading);
        }
        System.out.println("\nTotal Headings Found: " + count);
    }

    private static void scrapeCustomUrl(String url) {
        System.out.println("\n[SCRAPING] Connecting to custom URL: " + url);
        String html = fetchHtml(url);
        if (html == null || html.isEmpty()) return;

        // Extract Page Title
        Pattern titlePattern = Pattern.compile("<title[^>]*>(.*?)</title>", Pattern.CASE_INSENSITIVE);
        Matcher titleMatcher = titlePattern.matcher(html);
        String title = titleMatcher.find() ? cleanText(titleMatcher.group(1)) : "Unknown Page Title";

        // Extract Links
        Pattern linkPattern = Pattern.compile("<a\\s+(?:[^>]*?\\s+)?href=[\"']([^\"']+)[\"'][^>]*>(.*?)</a>", Pattern.CASE_INSENSITIVE);
        Matcher linkMatcher = linkPattern.matcher(html);

        System.out.println("\n=================================================");
        System.out.println("            WEBPAGE SCRAPING RESULT              ");
        System.out.println("=================================================");
        System.out.println(" Page Title: " + title);
        System.out.println(" Target URL : " + url);
        System.out.println("-------------------------------------------------");
        System.out.println(" Sample Hyperlinks Extracted:");

        int count = 0;
        while (linkMatcher.find() && count < 10) {
            String href = linkMatcher.group(1);
            String anchorText = cleanText(linkMatcher.group(2));
            if (!anchorText.isEmpty() && !href.startsWith("#")) {
                count++;
                System.out.printf(" %2d. Text: %-30s | Link: %s\n", count,
                        anchorText.length() > 30 ? anchorText.substring(0, 27) + "..." : anchorText, href);
            }
        }
        System.out.println("=================================================");
    }

    private static String cleanText(String input) {
        if (input == null) return "";
        return input.replaceAll("<[^>]*>", "").replaceAll("&ldquo;", "\"")
                .replaceAll("&rdquo;", "\"").replaceAll("&amp;", "&")
                .replaceAll("&nbsp;", " ").trim();
    }
}
