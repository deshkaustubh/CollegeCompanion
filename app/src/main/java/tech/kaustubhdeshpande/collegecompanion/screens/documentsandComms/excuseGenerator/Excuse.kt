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

val allExcuses: List<Excuse> = listOf(

    // 📝 Assignment
    Excuse(ExcuseTopic.Assignment, ExcuseTone.Honest, "This week was heavy and I couldn’t manage to finish the assignment."),
    Excuse(ExcuseTopic.Assignment, ExcuseTone.Honest, "I tried but couldn’t focus enough to wrap it well."),
    Excuse(ExcuseTopic.Assignment, ExcuseTone.Honest, "Mentally off track — I’ll catch up now with more clarity."),
    Excuse(ExcuseTopic.Assignment, ExcuseTone.Respectful, "Apologies for the delay — I’m completing the assignment today."),
    Excuse(ExcuseTopic.Assignment, ExcuseTone.Respectful, "I underestimated the time needed. I’ll submit it shortly."),
    Excuse(ExcuseTopic.Assignment, ExcuseTone.Respectful, "I’ve been managing a lot this week — requesting a little more time."),
    Excuse(ExcuseTopic.Assignment, ExcuseTone.Light, "The tab was open. My brain… not so much."),
    Excuse(ExcuseTopic.Assignment, ExcuseTone.Light, "Work in progress turned into nap in progress."),
    Excuse(ExcuseTopic.Assignment, ExcuseTone.Light, "I blinked… and the deadline blinked back."),

    // 🕐 Late
    Excuse(ExcuseTopic.Late, ExcuseTone.Honest, "I wasn’t in the right state of mind to attend today."),
    Excuse(ExcuseTopic.Late, ExcuseTone.Honest, "I overslept unintentionally — I’ll stay in sync."),
    Excuse(ExcuseTopic.Late, ExcuseTone.Honest, "Needed a breather today — I’ll catch up on missed work."),
    Excuse(ExcuseTopic.Late, ExcuseTone.Respectful, "Apologies for missing class — I had a difficult morning."),
    Excuse(ExcuseTopic.Late, ExcuseTone.Respectful, "I’ll catch up on notes and be more regular going forward."),
    Excuse(ExcuseTopic.Late, ExcuseTone.Respectful, "Sorry for the absence — I had an unavoidable delay."),
    Excuse(ExcuseTopic.Late, ExcuseTone.Light, "My alarm betrayed me. Deeply."),
    Excuse(ExcuseTopic.Late, ExcuseTone.Light, "Mentally on time. Physically… still loading."),
    Excuse(ExcuseTopic.Late, ExcuseTone.Light, "Woke up to 47 missed notifications — timing wasn’t one of them."),

    // 📆 Leave
    Excuse(ExcuseTopic.Leave, ExcuseTone.Honest, "I needed the day off to handle a few personal things."),
    Excuse(ExcuseTopic.Leave, ExcuseTone.Honest, "I wasn’t in the headspace to attend today."),
    Excuse(ExcuseTopic.Leave, ExcuseTone.Honest, "Mental reset day. Will return focused."),
    Excuse(ExcuseTopic.Leave, ExcuseTone.Respectful, "Requesting leave for personal reasons — thank you for understanding."),
    Excuse(ExcuseTopic.Leave, ExcuseTone.Respectful, "I won’t be able to attend due to a personal emergency."),
    Excuse(ExcuseTopic.Leave, ExcuseTone.Respectful, "Apologies — family duty called today."),
    Excuse(ExcuseTopic.Leave, ExcuseTone.Light, "Not skipping — just taking a tactical time-out."),
    Excuse(ExcuseTopic.Leave, ExcuseTone.Light, "Today’s vibes: blank page. Needed to log out of reality."),
    Excuse(ExcuseTopic.Leave, ExcuseTone.Light, "Taking a short escape from the semester spiral — will return.") ,

    // 💭 Burnout
    Excuse(ExcuseTopic.Burnout, ExcuseTone.Honest, "I’ve been overwhelmed lately — just trying to recalibrate."),
    Excuse(ExcuseTopic.Burnout, ExcuseTone.Honest, "Couldn’t function well this week — bouncing back now."),
    Excuse(ExcuseTopic.Burnout, ExcuseTone.Honest, "Completely drained recently. I’m working on it."),
    Excuse(ExcuseTopic.Burnout, ExcuseTone.Respectful, "Mentally I needed a short break to stay afloat."),
    Excuse(ExcuseTopic.Burnout, ExcuseTone.Respectful, "I’ve been trying to keep up but fell behind — sorry for the delay."),
    Excuse(ExcuseTopic.Burnout, ExcuseTone.Respectful, "Hope it’s okay I took a breather before burning out."),
    Excuse(ExcuseTopic.Burnout, ExcuseTone.Light, "Brain went into airplane mode. Trying to reconnect."),
    Excuse(ExcuseTopic.Burnout, ExcuseTone.Light, "Ran out of capacity. Recharging on silent mode."),
    Excuse(ExcuseTopic.Burnout, ExcuseTone.Light, "Sleep logged out. Motivation ghosted. Recovering now."),

    // 🤝 Group Work
    Excuse(ExcuseTopic.GroupWork, ExcuseTone.Honest, "I couldn’t manage my part — I’ll finish it today."),
    Excuse(ExcuseTopic.GroupWork, ExcuseTone.Honest, "Completely fumbled my task. I’m fixing it now."),
    Excuse(ExcuseTopic.GroupWork, ExcuseTone.Honest, "I wasn’t able to deliver this time. Next round’s on me."),
    Excuse(ExcuseTopic.GroupWork, ExcuseTone.Respectful, "Apologies for the delay — I’ll ensure my part’s done ASAP."),
    Excuse(ExcuseTopic.GroupWork, ExcuseTone.Respectful, "I’ll take full responsibility for lagging behind."),
    Excuse(ExcuseTopic.GroupWork, ExcuseTone.Respectful, "I wasn’t well and didn’t want to block the group — catching up now."),
    Excuse(ExcuseTopic.GroupWork, ExcuseTone.Light, "Delay’s on me. Panic mode is now active."),
    Excuse(ExcuseTopic.GroupWork, ExcuseTone.Light, "Not proud, but I ghosted the task. Making up for it."),
    Excuse(ExcuseTopic.GroupWork, ExcuseTone.Light, "Wasn’t lagging — just buffering. Finishing now."),

    // 🔧 Tech
    Excuse(ExcuseTopic.Tech, ExcuseTone.Honest, "Device crashed mid-task. I’ve got a fix incoming."),
    Excuse(ExcuseTopic.Tech, ExcuseTone.Honest, "WiFi issues broke the streak. I’m getting it done again."),
    Excuse(ExcuseTopic.Tech, ExcuseTone.Honest, "Couldn’t upload on time due to a system failure."),
    Excuse(ExcuseTopic.Tech, ExcuseTone.Respectful, "I’m facing tech issues but working around them."),
    Excuse(ExcuseTopic.Tech, ExcuseTone.Respectful, "Device failure delayed my submission. Sorry for the inconvenience."),
    Excuse(ExcuseTopic.Tech, ExcuseTone.Respectful, "Running into network trouble — will submit as soon as I can."),
    Excuse(ExcuseTopic.Tech, ExcuseTone.Light, "My laptop and I had a disagreement. I lost."),
    Excuse(ExcuseTopic.Tech, ExcuseTone.Light, "Typed it all out. Battery decided otherwise."),
    Excuse(ExcuseTopic.Tech, ExcuseTone.Light, "Tech glitch: The assignment is real. The upload... vanished."),

    // 🧪 Lab
    Excuse(ExcuseTopic.Lab, ExcuseTone.Honest, "I couldn’t show up today due to personal reasons."),
    Excuse(ExcuseTopic.Lab, ExcuseTone.Honest, "Completely blanked out on the timing. I regret it."),
    Excuse(ExcuseTopic.Lab, ExcuseTone.Honest, "Had a bit of a mental block — missed the lab."),
    Excuse(ExcuseTopic.Lab, ExcuseTone.Respectful, "Apologies for missing the practical — was dealing with something urgent."),
    Excuse(ExcuseTopic.Lab, ExcuseTone.Respectful, "Family obligation made me miss lab. I’ll make up for it."),
    Excuse(ExcuseTopic.Lab, ExcuseTone.Respectful, "Couldn’t attend lab today — request permission to reschedule."),
    Excuse(ExcuseTopic.Lab, ExcuseTone.Light, "My brain was in lab mode. My body was still in bed."),
    Excuse(ExcuseTopic.Lab, ExcuseTone.Light, "Lab was calling. My commute said 'not today.'"),
    Excuse(ExcuseTopic.Lab, ExcuseTone.Light, "Fully dressed for lab. Then my rickshaw ghosted me."),
    Excuse(ExcuseTopic.Lab, ExcuseTone.Light, "Lab day got lab-broken. Will make up next time."),

    // Forgot to submit
    Excuse(ExcuseTopic.Forget, ExcuseTone.Honest, "It was ready — I just forgot to hit submit."),
    Excuse(ExcuseTopic.Forget, ExcuseTone.Honest, "I lost track of the final step in the rush."),
    Excuse(ExcuseTopic.Forget, ExcuseTone.Honest, "Task completed, submission missed. Making up for it now."),
    Excuse(ExcuseTopic.Forget, ExcuseTone.Respectful, "Apologies — I prepped it all but missed the upload."),
    Excuse(ExcuseTopic.Forget, ExcuseTone.Respectful, "I intended to submit but missed the window. I'm resolving it."),
    Excuse(ExcuseTopic.Forget, ExcuseTone.Respectful, "Forgot to turn it in. Fixing that right away."),
    Excuse(ExcuseTopic.Forget, ExcuseTone.Light, "Prepared it. Forgot it. Now submitting it like a redemption arc."),
    Excuse(ExcuseTopic.Forget, ExcuseTone.Light, "Hit Ctrl+S but forgot Send. Classic."),
    Excuse(ExcuseTopic.Forget, ExcuseTone.Light, "Assignment: Done. Brain: Offline. Submission: Missed."),

    // deadline extension
    Excuse(ExcuseTopic.Extension, ExcuseTone.Honest, "I’m behind but still trying. Requesting one more day."),
    Excuse(ExcuseTopic.Extension, ExcuseTone.Honest, "Can’t do justice to it right now — need a short extension."),
    Excuse(ExcuseTopic.Extension, ExcuseTone.Honest, "Things stacked up unexpectedly. Hoping for a little room."),
    Excuse(ExcuseTopic.Extension, ExcuseTone.Respectful, "I humbly request one more day to finish it properly."),
    Excuse(ExcuseTopic.Extension, ExcuseTone.Respectful, "Requesting a short deadline extension due to overlapping work."),
    Excuse(ExcuseTopic.Extension, ExcuseTone.Respectful, "Would appreciate the chance to submit with a bit more time."),
    Excuse(ExcuseTopic.Extension, ExcuseTone.Light, "Just one extra reset and one extra cup of chai — please?"),
    Excuse(ExcuseTopic.Extension, ExcuseTone.Light, "Need to ask for an extension… and forgiveness."),
    Excuse(ExcuseTopic.Extension, ExcuseTone.Light, "Almost there — just need one more tomorrow to make it good.")
)