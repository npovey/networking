

### Project with RESTful APIs

[TOC]

This project uses two RESTful APIs:

<https://openweathermap.org/>

<https://newsapi.org/>

The project fetches the weather temperature and news for the given city and outputs it to the screen. The program also has retry logic. I used the simple retry logic where the program waits more as number of tries goes up.

####Step1

To run the program please navigate to the RESTJSON folder and run 'make'

```python
(base) nps-MacBook-Air-2:newREST np$ make
```

Output. You should see the following lines

```python

javac -cp .:gson-2.8.6.jar -g Wind.java
javac -cp .:gson-2.8.6.jar -g Weather.java
javac -cp .:gson-2.8.6.jar -g Sys.java
javac -cp .:gson-2.8.6.jar -g Main.java
javac -cp .:gson-2.8.6.jar -g Clouds.java
javac -cp .:gson-2.8.6.jar -g Coord.java
javac -cp .:gson-2.8.6.jar -g Rain.java
javac -cp .:gson-2.8.6.jar -g Result.java
javac -cp .:gson-2.8.6.jar -g Source.java
javac -cp .:gson-2.8.6.jar -g Article.java
javac -cp .:gson-2.8.6.jar -g NewsResult.java
javac -cp .:gson-2.8.6.jar -g Driver.java
(base) nps-MacBook-Air-2:newREST np$ 
```

####Step2

Run the code below to see the weather and news for "Seattle"

```python
(base) nps-MacBook-Air-2:newREST3 np$ java -cp .:bin:gson-2.8.6.jar Driver Seattle
```

Output:

```python
(base) nps-MacBook-Air-2:newREST np$ java -cp .:gson-2.8.6.jar Driver Seattle
Temperature in Seattle is 33.21 F

Popular NEWS from Seattle
‘No one should face such violence’: Gunfire in downtown Seattle kills one and injures seven, including a child - The Washington Post
‘No one should face such violence’: Gunfire in downtown Seattle kills one and injures seven, including a child The Washington Post 1 dead and 7 injured, including 9-year-old boy, in mass shooting on downtown Seattle sidewalk Seattle Times 1 person killed and …
https://www.washingtonpost.com/nation/2020/01/23/downtown-seattle-shooting/

Multi-agency response after novel coronavirus confirmed in Washington state - KING 5
Multi-agency response after novel coronavirus confirmed in Washington state KING 5 LIVE Q&A: Seattle doctor discusses coronavirus after first US case confirmed in Everett KING 5 View full coverage on Google News
https://www.youtube.com/watch?v=6Ey0z9R_n9k

Police say that at least one person is dead and 7 others are injured after shooting in downtown Seattle
Police on Wednesday night confirmed that a gunman opened fire in downtown Seattle on Wednesday night, killing one person and injuring seven others. The Seattle Times said seven people are being treated at a hospital for gunshot wounds, including a 9-year-old …
https://www.businessinsider.com/seattle-shooting-death-toll-injuries-videos-photos-7-1

Got a Wyze camera with people detection? Not anymore you don't!
Wyze announced the feature would "temporarily" disappear in November of 2019... What you need to know Apple has confirmed the successful acquisition of Seattle AI company Xnor.ai. It means that Xnor.ai's on-device people detection technology is no longer avai…
https://www.androidcentral.com/got-wyze-camera-people-detection-not-anymore-you-dont


```



#### Other

##### Example 2. Didn't provide any inputs

```python
(base) nps-MacBook-Air-2:newREST3 np$ make clean
rm -f bin/*.class
(base) nps-MacBook-Air-2:newREST3 np$ make
javac -d bin -cp .:bin:gson-2.8.6.jar -g src/Wind.java
javac -d bin -cp .:bin:gson-2.8.6.jar -g src/Weather.java
javac -d bin -cp .:bin:gson-2.8.6.jar -g src/Sys.java
javac -d bin -cp .:bin:gson-2.8.6.jar -g src/Main.java
javac -d bin -cp .:bin:gson-2.8.6.jar -g src/Clouds.java
javac -d bin -cp .:bin:gson-2.8.6.jar -g src/Coord.java
javac -d bin -cp .:bin:gson-2.8.6.jar -g src/Rain.java
javac -d bin -cp .:bin:gson-2.8.6.jar -g src/Result.java
javac -d bin -cp .:bin:gson-2.8.6.jar -g src/Source.java
javac -d bin -cp .:bin:gson-2.8.6.jar -g src/Article.java
javac -d bin -cp .:bin:gson-2.8.6.jar -g src/NewsResult.java
javac -d bin -cp .:bin:gson-2.8.6.jar -g src/Driver.java
(base) nps-MacBook-Air-2:newREST3 np$ java -cp .:bin:gson-2.8.6.jar Driver
Must enter a city name!
Example: java -cp .:bin:gson-2.8.6.jar Driver Seattle
Example: java -cp .:bin:gson-2.8.6.jar Driver Redmond
(base) nps-MacBook-Air-2:newREST3 np$ 
```

##### Example 3: Searched for Redmond

```python
(base) nps-MacBook-Air-2:newREST np$ java -cp .:gson-2.8.6.jar Driver Redmond
Temperature in Redmond is 33.22 F

Popular NEWS from Redmond
Global National: Jan. 24, 2020 | China's coronavirus lockdown and Canada's precautions - Global News
Redmond Shannon reports as millions of people are facing tightening restrictions in China with the deadly coronavirus outbreak continuing to spread. Meanwhil...
https://www.youtube.com/watch?v=JYID4VjRo7A

Mixed reaction to Harry and Meghan's royal withdrawal - Global News
Mixed reaction to Harry and Meghan's royal withdrawal Global News Prince Harry might be looking for a job when he comes to Canada. Here are some options CBC.ca Splitting heirs: Royal Family members can’t live in Canada, but ex-Royals can The Globe and Mail Qu…
https://www.youtube.com/watch?v=n_cW2WXLuSQ

2019 was one of the safest years on record for air travel - Global News
2019 was one of the safest years on record for air travel Global News American Airlines vows to share Boeing proceeds with workers The Globe and Mail Global plane crash fatalities involving large commercial aircraft fell more than 50% to 257 in 2019 Daily Mai…
https://www.youtube.com/watch?v=Q9wncFA6v2A

Global National: Feb 1, 2020 | More Canadians look to get out of China amid coronavirus outbreak - Global News
China's death toll continues to rise as the World Health Organization is advising other countries to prepare for "domestic outbreak control" should the disea...
https://www.youtube.com/watch?v=OsvXizp34io

(base) nps-MacBook-Air-2:newREST np$ 
```

##### Example 4: Searched for New York

```python
(base) nps-MacBook-Air-2:newREST3 np$ java -cp .:bin:gson-2.8.6.jar Driver New York
Temperature in New York is 34.95 F

Popular NEWS from New York
A Major New Particle Collider Is Coming to New York
The U.S. Department of Energy has decided on the final location of a major upcoming American particle collider: Brookhaven National Lab on Long Island in New York. Read more...
https://gizmodo.com/a-major-new-particle-collider-is-coming-to-new-york-1840931125

Ben Smith is leaving BuzzFeed for the New York Times
Ben Smith, the former political blogger who built BuzzFeed News into a digital powerhouse, announced Tuesday that he is leaving the website and joining The New York Times as the newspaper's media columnist.
https://www.cnn.com/2020/01/28/media/ben-smith-buzzfeed-new-york-times/index.html

If you’re feeling nervous about the coronavirus, you’re not alone
Reduce anxiety about the new coronavirus by putting the risks in perspective, taking a break from reading the news, and spending time with friends.
https://www.theverge.com/2020/1/24/21080628/coronavirus-risk-anxiety-how-to-cope-strategies-news-china-wuhan

What the Bolton news could mean for the impeachment trial
President Trump's lawyers will continue giving their opening arguments at the impeachment trial. Follow here for the latest.
https://www.cnn.com/politics/live-news/trump-impeachment-trial-01-27-20/index.html

(base) nps-MacBook-Air-2:newREST3 np$ 
```



#####Example 5 Searched for Salt Lake City

```python
(base) nps-MacBook-Air-2:newREST3 np$ java -cp .:bin:gson-2.8.6.jar Driver Salt Lake City
Temperature in Salt Lake City is 38.01 F

Popular NEWS from Salt Lake City
4 Killed, 1 Injured in Family Shooting in Salt Lake City Suburb
Four members of a family were killed and one injured in a shooting Friday night in a Salt Lake City suburb, local media report.
https://time.com/5767806/utah-family-shooting/

Clayton Christensen, author of “The Innovator’s Dilemma,” has passed away at age 67
Clayton Christensen, a longtime professor at Harvard Business School who became famous worldwide after authoring in 1997 the best-selling business book, “The Innovator’s Dilemma: When New Technologies Cause Great Firms to Fail,” passed away last night, The De…
http://techcrunch.com/2020/01/24/clayton-christensen-author-of-the-innovators-dilemma-has-passed-away-at-age-67/

Boy arrested after shooting that killed 4 in small Utah town - USA TODAY
Boy arrested after shooting that killed 4 in small Utah town USA TODAY Utah boy arrested after shooting that killed 4, injured 1 in same family, police say Fox News 4 killed, 1 wounded in Utah shooting CNN 'Devastating' Grantsville shooting leaves community i…
https://www.usatoday.com/story/news/nation/2020/01/18/juvenile-arrested-after-4-people-killed-grantsville-utah-shooting/4514274002/?utm_source=google&utm_medium=amp&utm_campaign=speakable

2019 AAAS Kavli Science Journalism Award winners
Strong local reporting on the status of Puget Sound's killer whales, the degradation of soils in a region of France, air quality in Utah, and the impact of an Idaho nuclear research facility are among the winning entries for the 2019 AAAS Kavli Science Journa…
https://science.sciencemag.org/content/367/6477/521.full

(base) nps-MacBook-Air-2:newREST3 np$ 

```



