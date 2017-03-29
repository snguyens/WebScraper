import java.io.IOException;
import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/*
* A brief description of how this program works: 
* 
* User will specify which professor they want to search.
*
* Program will scrape the page to find overall quality, would take again, and level of difficulty. 
*
* Program will collect individual ratings from each review and the comment associated with it,
* then sorting all the reviews from highest review to lowest review. 
*/

public class CollectReviews {

	public static void main(String[] args) {
		try {
			/* User inputs desired professor ID from ratemyprofessor */
			System.out.println("Enter the ID of the professor as listed on ratemyprofessor.com (i.e. 336683): ");
			Scanner scanner = new Scanner(System.in);
			String id = scanner.nextLine();

			/* Create a new connection to the webpage */
			Document doc = Jsoup.connect("https://www.ratemyprofessors.com/ShowRatings.jsp?tid=" + id).get();  

			/* Select overall quality, take again, and level of difficulty from the scraped data */
			Element overall = doc.select("div.breakdown-container.quality > div > div.grade").get(0); 
			Element takeAgain = doc.select("div.breakdown-section.takeAgain > div.grade").get(0);
			Element difficulty = doc.select("div.breakdown-section.difficulty > div.grade").get(0); 

			/* Implement priority queue to store reviews from top-rated review to lowest-rated review */
			Comparator<Pair<Double, String>> comparator = new OverallComparator();
			PriorityQueue<Pair<Double, String>> pq = new PriorityQueue<Pair<Double, String>>(100, comparator);

			/* Select all individual overall scores from each review, either poor, average, or good */
			org.jsoup.select.Elements overallScore = doc.select("span.score.poor,span.score.average,span.score.good");

			/* Select the comment associated with each review */
			org.jsoup.select.Elements comments = doc.select("p.commentsParagraph"); 

			/* Loop through all the reviews */
			for (int i = 0; i < overallScore.size(); i += 2) {
				double score = Double.parseDouble(overallScore.get(i).text());
				String comment = comments.get(i/2).text();

				/*Insert into priority queue the overall score and the review associated with it */
				pq.add(new Pair<Double, String>(score, comment));
			}

			/* Print to the user overall quality, overall difficulty, and would take again */
			System.out.println("Overall quality: " + overall.text());	
			System.out.println("Overall difficulty: " + difficulty.text());
			System.out.println("Would take again: " + takeAgain.text() + "\n");

			/* Display head of pq and remove the head of the pq until empty */
			while (!pq.isEmpty()) {
				/* Print the overall quality and comment associated with it */
				System.out.println(pq.peek().getKey() + "\n" + pq.peek().getValue() + "\n");

				/* Remove head of pq */
				pq.remove();
			}

		} catch (IOException ex) {
			System.out.println("Caught IOException: " + ex.getMessage());
		}
	};

}