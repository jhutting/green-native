package com.ing.green.quarkus;

import com.ing.green.quarkus.model.Task;
import com.ing.green.quarkus.model.TaskStatus;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static io.restassured.RestAssured.given;

@QuarkusTest
class TaskResourceTest {
    private String listOfBookTitles = "Shortcut\n" +
            "Medical Mycology: Cellular and Molecular Techniques\n" +
            "The First Third\n" +
            "Reasons Mommy Drinks\n" +
            "Manual of High Risk Pregnancy and Delivery, 5e (Manual of High Risk Pregnancy & Delivery)\n" +
            "Oscillations and Waves: An Introduction\n" +
            "DAMAGES\n" +
            "Sermons on Timothy and Titus (16th-17th Century Facsimile Edition)\n" +
            "Oils and Vinegars: Discovering and Enjoying Gourmet Oils and Vinegars\n" +
            "The Handbook of Painted Decoration: The Tools, Materials, and Step-by-Step Techniques of Trompe L'Oeil Painting\n" +
            "Stage Plays from the Classics: One-Act Adaptations from Famous Short Stories, Novels, and Plays\n" +
            "Runner's World Meals on the Run: 150 energy-packed recipes in 30 minutes or less\n" +
            "Mosaics Inside & Out: Patterns and Inspirations for 17 Mosaic Projects\n" +
            "She Appears! Encounters with Kwan Yin, Goddess of Compassion\n" +
            "A Companion to Museum Studies\n" +
            "United States History  Early Years (Level 5): Houghton Mifflin Social Studies\n" +
            "Indoor Marijuana Horticulture: The Indoor Bible\n" +
            "This Book Is Full of Spiders: Seriously, Dude, Don't Touch It (John Dies at the End)\n" +
            "The Secret to Long Life in Your DNA: The Beljanski Approach to Cellular Health\n" +
            "Rabbi Israel Salanter and the Mussar Movement: Seeking the Torah of Truth\n" +
            "The Healthiest City: Milwaukee and the Politics of Health Reform\n" +
            "My Fair Gentleman (Proper Romance) (A Proper Romance)\n" +
            "Stand Up Paddling: Flatwater to Surf and Rivers (Mountaineering Outdoor Experts) (Moes)\n" +
            "Your Guide to Zion and Bryce Canyon (True North Series)\n" +
            "Unnatural Talent: Creating, Printing and Selling Your Comic in the Digital Age\n" +
            "Brainstorm: The Power and Purpose of the Teenage Brain\n" +
            "E-Commerce Concepts\n" +
            "Well Enough Alone: A Cultural History of My Hypochondria\n" +
            "Basic and Clinical Pharmacology 13 E\n" +
            "How to Have a Big Wedding on a Small Budget\n" +
            "Russian Grammar (Quickstudy: Academic)\n" +
            "Responsible Travel Guide Cambodia\n" +
            "Principles of Marketing (16th Edition) (Newest Edition)\n" +
            "Halo: The Poster Collection\n" +
            "Mercy Watson: Something Wonky this Way Comes\n" +
            "If You're Lucky\n" +
            "Great-Tasting Potatoes Cookbook\n" +
            "The Kuwaiti Oil Fires (Environmental Disasters)\n" +
            "Healing Without Medication: A Comprehensive Guide to the Complementary Techniques Anyone Can Use to Achieve Real Healing\n" +
            "Eternity's Sunrise: The Imaginative World of William Blake\n" +
            "Paraguay (Bradt Travel Guide)\n" +
            "Treating Trauma and Traumatic Grief in Children and Adolescents\n" +
            "A Terrible Revenge: The Ethnic Cleansing of the East European Germans\n" +
            "Plastics: America's Packaging Dilemma (Island Press Critical Issues Series)\n" +
            "Microbiologically Safe Foods\n" +
            "Life Reimagined: Discovering Your New Life Possibilities\n" +
            "It's St. Patrick's Day (Turtleback School & Library Binding Edition) (Scholastic Reader: Level 1 (Pb))\n" +
            "Strands of Sorrow (Black Tide Rising)\n" +
            "Analytic Philosophy\n" +
            "The Modern Coral Reef Aquarium, Volume 2 (v. 2)\n" +
            "Promises Kept: The Life of an Issei Man\n" +
            "Vocabulary for the College Bound Student\n" +
            "Hunting the Rockies: Home of the Giants\n" +
            "The Slums of Aspen: Immigrants vs. the Environment in America's Eden (Nation of Nations)\n" +
            "Disaster Law and Policy, Second Edition (Aspen Elective)\n" +
            "Mobile & Social Game Design: Monetization Methods and Mechanics, Second Edition\n" +
            "Ready, Freddy! #22: Science Fair Flop\n" +
            "HSPT Flashcard Study System: HSPT Exam Practice Questions & Review for the High School Placement Test (Cards)\n" +
            "On the Backroad to Heaven: Old Order Hutterites, Mennonites, Amish, and Brethren (Center Books in Anabaptist Studies)\n" +
            "Spiritual Astrology: A Path to Divine Awakening\n" +
            "Passing on Bypass Using External CounterPulsation : An FDA Cleared Alternative to Treat Heart Disease Without Surgery, Drugs or Angioplasty. SECOND EDITION\n" +
            "Walking the Walk (w/DVD): Getting Fit with Faith\n" +
            "New Jersey Puzzle Book (Which Way USA?)\n" +
            "The Best American Essays of the Century (The Best American Series)\n" +
            "God Made All of Me: A Book to Help Children Protect Their Bodies\n" +
            "Classic baseball cards: The golden years, 1886-1956\n" +
            "Garfield 2016 Day-to-Day Calendar\n" +
            "Beyond Courage: The Untold Story of Jewish Resistance During the Holocaust\n" +
            "The Scientist as Rebel (New York Review Books)\n" +
            "The Life and Love of Dogs\n" +
            "Fish Tales: Stories & Recipes from Sustainable Fisheries Around the World\n" +
            "Health: The Basics (11th Edition)\n" +
            "Unprocessed: How to achieve vibrant health and your ideal weight.\n" +
            "U.S. Army Special Forces Guide to Unconventional Warfare: Devices and Techniques for Incendiaries\n" +
            "Most Important Year in a Woman's Life, The/The Most Important Year in a Man's Life\n" +
            "Texas Birds: A Folding Pocket Guide to Familiar Species (Pocket Naturalist Guide Series)\n" +
            "A-Z of Embroidery Stitches 2 (A-Z of Needlecraft)\n" +
            "The Maverick of Copper Creek (Copper Creek Cowboys)\n" +
            "Monster High: The Scary Cute Collection\n" +
            "The ADA Practical Guide to Soft Tissue Oral Disease\n" +
            "They Wished They Were Honest: The Knapp Commission and New York City Police Corruption\n" +
            "What Horses Reveal: From First Meeting to Friend for Life\n" +
            "Vital Breath of the Dao: Chinese Shamanic Tiger Qigong\n" +
            "iPhone Application Development For Dummies\n" +
            "Ian Fleming Introduces Jamaica\n" +
            "Art for London Transport 2015 Calendar\n" +
            "Seneca: Epistles 66-92 (Loeb No. 76)\n" +
            "Wild\n" +
            "Principles of Optics: Electromagnetic Theory of Propagation, Interference and Diffraction of Light\n" +
            "No More Fatigue: Why You're So Tired and What You Can Do About It\n" +
            "A Social Security Owner's Manual, 3rd Edition: Your Guide to Social Security Retirement, Dependent's, and Survivor's Benefits\n" +
            "The New Testament: A Historical Introduction to the Early Christian Writings\n" +
            "Stephen Irwin\n" +
            "The Rewind Files\n" +
            "Mao: The Unknown Story\n" +
            "Swimming: Swimming Made Easy- Beginner and Expert Strategies For Becoming A Better Swimmer (Swimming, Swimmers Guide, Swim Strokes, Swimming Better)\n" +
            "British Diecast Model Toys Catalogue by John Ramsay (Editor) (1-Nov-2001) Hardcover\n" +
            "Sherlock Holmes: The Major Stories with Contemporary Critical Essays (Bedford Series in History & Culture)\n" +
            "A is for Abinadi: An Alphabet Book of Scripture Heroes\n" +
            "Zoonosi e sanità pubblica: Un approccio interdisciplinare per un problema emergente (Italian Edition)\n" +
            "Potatoes (The Australian Women's Weekly Essentials)\n" +
            "Bride in the Solomons\n" +
            "Consumerism (Opposing Viewpoints)\n" +
            "Social Media Marketing All-in-One For Dummies\n" +
            "Fallacies of the Anti Hadith argument\n" +
            "Invitation to Dynamical Systems (Dover Books on Mathematics)\n" +
            "The 7-Day Flat-Belly Tea Cleanse: The Revolutionary New Plan to Melt Up to 10 Pounds of Fat in Just One Week!\n" +
            "Moomin Book Five: The Complete Tove Jansson Comic Strip\n" +
            "The Kentucky Wildcats Fans' Bucket List\n" +
            "Coaching the Complete Triple Gun Offense\n" +
            "Supply Chain Management: Strategy, Planning and Operations\n" +
            "America : A Patriotic Primer\n" +
            "Black Earth: The Holocaust as History and Warning\n" +
            "Agricola Cookbook\n" +
            "A Philosophical Retrospective: Facts, Values, and Jewish Identity (Columbia Themes in Philosophy)\n" +
            "Kokology: The Game of Self-Discovery\n" +
            "Growing Hawaii's Native Plants: A Simple Step-by-Step Approach for Every Species\n" +
            "Law Enforcement In The United States\n" +
            "Kuan Yin Oracle Set\n" +
            "Biological Models in Radiopharmaceutical Development (Developments in Nuclear Medicine)\n" +
            "Indiana: Off the beaten path (Off the Beaten Path Indiana)\n" +
            "For the Love of 2am: Poetry For Insomniacs\n" +
            "In a World of Gods and Goddesses: The Mystic Art of Indra Sharma\n" +
            "Introduction to Psychology\n" +
            "CLEP Micro & Macro Economics Examinations Essential Study References 2013\n" +
            "An Acre of Glass: A History and Forecast of the Telescope\n" +
            "The Miracle of Castel Di Sangro\n" +
            "Tales of King Arthur (Library of Fantasy & Adventure)\n" +
            "Visit the Zoo, vol. VI\n" +
            "Asset Building & Community Development";
    
    @Test
    void testHelloEndpoint() {
        given()
          .when().get("/tasks")
          .then()
             .statusCode(200);
    }

    @Disabled
    @Test
    @DisplayName("This will add random tasks to the database")
    void generateTasks() {
        final Random random = new Random();
        for(String title: listOfBookTitles.split("\n")) {
            TaskStatus status = TaskStatus.values()[random.nextInt(TaskStatus.values().length)];
            String name = random.nextInt(2) == 0 ? "Johan" : "Vasco";
            Task task = new Task(0L, title, name, status);
            given()
                    .when().body(task).contentType(ContentType.JSON).post("/tasks")
                    .then()
                    .statusCode(200);
        }
    }
}