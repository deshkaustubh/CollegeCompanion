package tech.kaustubhdeshpande.collegecompanion.screens.documentsandComms.excuseGenerator

enum class ExcuseTopic {
    Assignment, Late, Leave, Burnout, GroupWork, Tech, Lab, Forget, Extension
}

enum class ExcuseTone {
    Honest, Respectful, Light
}

data class Excuse(
    val topic: ExcuseTopic,
    val tone: ExcuseTone,
    val text: String
)

// Updated list with more elaborate, story-like excuses for a Mumbai classroom.
val allExcuses: List<Excuse> = listOf(

    // 📝 Assignment
    Excuse(
        ExcuseTopic.Assignment,
        ExcuseTone.Honest,
        "Sir, actually kal mummy ki tabiyat theek nahi thi, toh unke saath doctor ke paas jaana pada. Vapis aate aate kaafi late ho gaya, aur jab assignment karne baitha toh laptop hi crash ho gaya. Subah se theek karwane diya hai."
    ),
    Excuse(
        ExcuseTopic.Assignment,
        ExcuseTone.Honest,
        "Concept theek se samajh nahi aaya, thoda time lagega isme."
    ),
    Excuse(
        ExcuseTopic.Assignment,
        ExcuseTone.Honest,
        "Doosre subjects ka itna load tha ki ispe aane ka time hi nahi mila."
    ),
    Excuse(
        ExcuseTopic.Assignment,
        ExcuseTone.Respectful,
        "I apologize, ma'am. Ghar pe puja thi, isliye poora din usi mein nikal gaya. I couldn't manage the assignment on time."
    ),
    Excuse(
        ExcuseTopic.Assignment,
        ExcuseTone.Respectful,
        "I've been facing some network issues at home, which delayed my research. I will submit it by tomorrow."
    ),
    Excuse(
        ExcuseTopic.Assignment,
        ExcuseTone.Respectful,
        "My apologies for the delay. I will ensure the completed assignment is submitted by evening."
    ),
    Excuse(ExcuseTopic.Assignment, ExcuseTone.Light, "Assignment ready hai, bas thoda finishing touch... kal tak de doon?"),
    Excuse(
        ExcuseTopic.Assignment,
        ExcuseTone.Light,
        "Sir, inspiration hi nahi aayi. Ab aayi hai, bas thoda time chahiye."
    ),
    Excuse(ExcuseTopic.Assignment, ExcuseTone.Light, "Brain ne bola 'aaj nahi ho paayega'. Kal pakka."),

    // 🕐 Late to Class
    Excuse(
        ExcuseTopic.Late,
        ExcuseTone.Honest,
        "Sorry sir, Churchgate se fast local pakdi thi, par Vile Parle ke paas aake signal issue ke kaaran ruk gayi. 15 minute vahi pe khadi thi, phir itni gardi ho gayi ki utarne mein hi time lag gaya."
    ),
    Excuse(ExcuseTopic.Late, ExcuseTone.Honest, "Alarm hi nahi baja, pata nahi kaise. Sorry ma'am."),
    Excuse(
        ExcuseTopic.Late,
        ExcuseTone.Honest,
        "Dadar station pe bohot gardi thi, nikalne mein time lag gaya."
    ),
    Excuse(
        ExcuseTopic.Late,
        ExcuseTone.Respectful,
        "Apologies for being late, ma'am. The train was running 20 minutes late today due to a signal issue."
    ),
    Excuse(
        ExcuseTopic.Late,
        ExcuseTone.Respectful,
        "I'm very sorry for the delay. Subah-subah cylinder wala aa gaya tha, aur ghar pe koi nahi tha, isliye rukna pada."
    ),
    Excuse(
        ExcuseTopic.Late,
        ExcuseTone.Respectful,
        "My sincerest apologies for my tardiness. It won't be repeated."
    ),
    Excuse(ExcuseTopic.Late, ExcuseTone.Light, "Time pe nikla tha, par Virar fast ne 'you shall not pass' bol diya."),
    Excuse(ExcuseTopic.Late, ExcuseTone.Light, "Sir, meri neend aur main, humara breakup ho gaya... thoda late se."),
    Excuse(
        ExcuseTopic.Late,
        ExcuseTone.Light,
        "Main physically late hoon, but mentally main time pe tha."
    ),

    // 📆 Leave / Absence
    Excuse(
        ExcuseTopic.Leave,
        ExcuseTone.Honest,
        "Ma'am, ghar pe achanak mehmaan aa gaye the door ke."
    ),
    Excuse(ExcuseTopic.Leave, ExcuseTone.Honest, "Thodi tabiyat theek nahi lag rahi thi, isliye rest le liya."),
    Excuse(
        ExcuseTopic.Leave,
        ExcuseTone.Honest,
        "Sir, subah achanak Aaji ki tabiyat kharab ho gayi, unko doctor ke paas leke jaana pada. Abhi reports ka wait kar rahe hain, isliye aana possible nahi hai."
    ),
    Excuse(
        ExcuseTopic.Leave,
        ExcuseTone.Respectful,
        "I will be unable to attend today's lecture as we have a small religious ceremony at home."
    ),
    Excuse(
        ExcuseTopic.Leave,
        ExcuseTone.Respectful,
        "Unfortunately, I will be absent as I have to take my grandmother for a doctor's check-up."
    ),
    Excuse(ExcuseTopic.Leave, ExcuseTone.Respectful, "I have a medical appointment that I could not reschedule."),
    Excuse(ExcuseTopic.Leave, ExcuseTone.Light, "Sir, ek din ka 'strategic timeout' liya hai. Kal se back in form."),
    Excuse(
        ExcuseTopic.Leave,
        ExcuseTone.Light,
        "Thoda 'out of station' tha... mentally."
    ),
    Excuse(
        ExcuseTopic.Leave,
        ExcuseTone.Light,
        "Aaj attendance ka 'work from home' kar raha hoon."
    ),

    // 💭 Burnout
    Excuse(
        ExcuseTopic.Burnout,
        ExcuseTone.Honest,
        "Sir, sab kuch upar se jaa raha hai. Ek din ka break chahiye."
    ),
    Excuse(
        ExcuseTopic.Burnout,
        ExcuseTone.Honest,
        "Itna submissions ka pressure hai ki poora drain ho gaya hoon."
    ),
    Excuse(
        ExcuseTopic.Burnout,
        ExcuseTone.Honest,
        "Focus hi nahi kar paa raha, sir. Thoda space chahiye."
    ),
    Excuse(
        ExcuseTopic.Burnout,
        ExcuseTone.Respectful,
        "Ma'am, the academic workload has been quite overwhelming lately. I need some time to recalibrate."
    ),
    Excuse(
        ExcuseTopic.Burnout,
        ExcuseTone.Respectful,
        "I've been finding it difficult to manage everything. I'm taking a day to organize and get back on track."
    ),
    Excuse(
        ExcuseTopic.Burnout,
        ExcuseTone.Respectful,
        "I need to prioritize my well-being at the moment to avoid a complete burnout."
    ),
    Excuse(
        ExcuseTopic.Burnout,
        ExcuseTone.Light,
        "Brain ka server down hai, reboot ke liye time chahiye."
    ),
    Excuse(
        ExcuseTopic.Burnout,
        ExcuseTone.Light,
        "Motivation chutti pe gaya hai, kal tak aa jaayega."
    ),
    Excuse(
        ExcuseTopic.Burnout,
        ExcuseTone.Light,
        "Meri social battery khatam ho gayi hai. Charging pe lagayi hai."
    ),

    // 🤝 Group Work
    Excuse(
        ExcuseTopic.GroupWork,
        ExcuseTone.Honest,
        "Sorry yaar, mera part reh gaya. Abhi 2 ghante mein karke bhejta hoon."
    ),
    Excuse(
        ExcuseTopic.GroupWork,
        ExcuseTone.Honest,
        "Guys, main doosre kaam mein fass gaya tha. My apologies. Aaj raat tak pakka."
    ),
    Excuse(
        ExcuseTopic.GroupWork,
        ExcuseTone.Honest,
        "Topic hi samajh nahi aaya theek se. Koi explain kar do, main fatafat kar doonga."
    ),
    Excuse(
        ExcuseTopic.GroupWork,
        ExcuseTone.Respectful,
        "Apologies to the team. I was unable to complete my part on time due to some other commitments. I will finish it tonight."
    ),
    Excuse(
        ExcuseTopic.GroupWork,
        ExcuseTone.Respectful,
        "I take responsibility for the delay on my end. I will ensure it doesn't affect our final submission."
    ),
    Excuse(
        ExcuseTopic.GroupWork,
        ExcuseTone.Respectful,
        "I have not been able to complete my portion due to some personal issues. I apologize for the inconvenience."
    ),
    Excuse(ExcuseTopic.GroupWork, ExcuseTone.Light, "Mera contribution 'coming soon' mode pe tha. Ab release kar raha hoon."),
    Excuse(
        ExcuseTopic.GroupWork,
        ExcuseTone.Light,
        "Main perfection ka wait kar raha tha. Ab 'good enough' pe settle kar raha hoon."
    ),
    Excuse(
        ExcuseTopic.GroupWork,
        ExcuseTone.Light,
        "Procrastination se lad raha tha. Main jeet gaya. Ab kaam bhejta hoon."
    ),

    // 🔧 Tech Issues
    Excuse(
        ExcuseTopic.Tech,
        ExcuseTone.Honest,
        "Sir, Wi-Fi hi nahi chal raha subah se."
    ),
    Excuse(
        ExcuseTopic.Tech,
        ExcuseTone.Honest,
        "Laptop achanak band ho gaya. Abhi theek karwa raha hoon."
    ),
    Excuse(ExcuseTopic.Tech, ExcuseTone.Honest, "File save ki thi, but corrupt ho gayi. Phir se banana padega."),
    Excuse(
        ExcuseTopic.Tech,
        ExcuseTone.Respectful,
        "I am currently experiencing technical difficulties with my device. I am trying to resolve the issue."
    ),
    Excuse(
        ExcuseTopic.Tech,
        ExcuseTone.Respectful,
        "I apologize, but a power outage at my place has prevented me from completing the work."
    ),
    Excuse(
        ExcuseTopic.Tech,
        ExcuseTone.Respectful,
        "I am having trouble with the college portal and it's not accepting my submission. I have already emailed the IT support."
    ),
    Excuse(ExcuseTopic.Tech, ExcuseTone.Light, "Laptop ne dhoka de diya, sir. Last moment pe."),
    Excuse(ExcuseTopic.Tech, ExcuseTone.Light, "Mera laptop Windows update pe atka hai, jaise meri life."),
    Excuse(
        ExcuseTopic.Tech,
        ExcuseTone.Light,
        "File corrupt ho gayi, jaise mera luck."
    ),

    // 🧪 Lab
    Excuse(
        ExcuseTopic.Lab,
        ExcuseTone.Honest,
        "Sorry ma'am, gas cylinder deliver hone wala tha, par unka pipe leak ho raha tha. Usko theek karwane mein itna time chala gaya ki aana hi cancel karna pada."
    ),
    Excuse(
        ExcuseTopic.Lab,
        ExcuseTone.Honest,
        "Tabiyat theek nahi lag rahi thi, isliye risk nahi liya."
    ),
    Excuse(ExcuseTopic.Lab, ExcuseTone.Honest, "Main date galat samajh baitha. My mistake."),
    Excuse(
        ExcuseTopic.Lab,
        ExcuseTone.Respectful,
        "I was unfortunately unable to attend the lab session today due to being unwell."
    ),
    Excuse(
        ExcuseTopic.Lab,
        ExcuseTone.Respectful,
        "My apologies for missing the lab. I had to go with my father to the municipality office."
    ),
    Excuse(
        ExcuseTopic.Lab,
        ExcuseTone.Respectful,
        "I regret my absence from the lab practical. I will get the details from my lab partner."
    ),
    Excuse(
        ExcuseTopic.Lab,
        ExcuseTone.Light,
        "Lab coat dhulne gaya tha, sir. Sookha hi nahi."
    ),
    Excuse(ExcuseTopic.Lab, ExcuseTone.Light, "Aaj ka din experiments ke liye aacha nahi tha, according to my horoscope."),
    Excuse(
        ExcuseTopic.Lab,
        ExcuseTone.Light,
        "Sir, main aane hi wala tha, par rickshaw wale ne doosre college drop kar diya."
    ),

    // 🤦 Forgot to submit
    Excuse(ExcuseTopic.Forget, ExcuseTone.Honest, "Sir, bana liya tha, bas submit karna bhool gaya. Abhi kar doon?"),
    Excuse(ExcuseTopic.Forget, ExcuseTone.Honest, "It was completed and saved in my drafts. Dimaag se nikal gaya."),
    Excuse(
        ExcuseTopic.Forget,
        ExcuseTone.Honest,
        "Mujhe laga deadline kal ki hai. My bad. Abhi submit kar raha hoon."
    ),
    Excuse(
        ExcuseTopic.Forget,
        ExcuseTone.Respectful,
        "I sincerely apologize for the oversight. The assignment was ready, but I neglected to submit it before the deadline."
    ),
    Excuse(
        ExcuseTopic.Forget,
        ExcuseTone.Respectful,
        "Please forgive my error, I mistakenly thought I had already submitted the file. I am submitting it right now."
    ),
    Excuse(
        ExcuseTopic.Forget,
        ExcuseTone.Respectful,
        "I am writing to apologize for failing to submit my assignment. It was a complete oversight on my part."
    ),
    Excuse(
        ExcuseTopic.Forget,
        ExcuseTone.Light,
        "Assignment ready tha, par mere dimaag ka 'send' button jam ho gaya tha."
    ),
    Excuse(ExcuseTopic.Forget, ExcuseTone.Light, "Maine 'save' kiya, 'submit' nahi. Classic blunder."),
    Excuse(
        ExcuseTopic.Forget,
        ExcuseTone.Light,
        "Task completed. Submission... buffering. Ab ho gaya."
    ),

    // ⏳ Extension Request
    Excuse(
        ExcuseTopic.Extension,
        ExcuseTone.Honest,
        "Sir, thoda aur time milega kya? Bahut load hai."
    ),
    Excuse(
        ExcuseTopic.Extension,
        ExcuseTone.Honest,
        "Ma'am, I'm not well. Can I please get a one-day extension?"
    ),
    Excuse(
        ExcuseTopic.Extension,
        ExcuseTone.Honest,
        "Is topic mein thoda struggle kar raha hoon. Thoda extra time aacha rahega."
    ),
    Excuse(
        ExcuseTopic.Extension,
        ExcuseTone.Respectful,
        "I would be very grateful if you could grant a small extension for this submission."
    ),
    Excuse(
        ExcuseTopic.Extension,
        ExcuseTone.Respectful,
        "Due to overlapping deadlines, I would appreciate a 24-hour extension to submit quality work."
    ),
    Excuse(
        ExcuseTopic.Extension,
        ExcuseTone.Respectful,
        "Would it be possible to get a small extension? I want to ensure I can submit my best work."
    ),
    Excuse(
        ExcuseTopic.Extension,
        ExcuseTone.Light,
        "Sir, deadline ko thoda aage khiska sakte hain kya? Please?"
    ),
    Excuse(
        ExcuseTopic.Extension,
        ExcuseTone.Light,
        "Bas ek din aur... aur ek cup chai. Phir assignment aapke paas."
    ),
    Excuse(
        ExcuseTopic.Extension,
        ExcuseTone.Light,
        "Thoda sa time de do, sir. Promise, is baar disappoint nahi karunga."
    )
)
