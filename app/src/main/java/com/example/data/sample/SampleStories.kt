package com.example.data.sample

import com.example.R
import com.example.data.model.Story
import com.example.data.model.StoryCategory

object SampleStories {

    val stories: List<Story> = listOf(
        // 1. புத்திசாலி முயல்
        Story(
            id = "story_rabbit_lion",
            titleTamil = "புத்திசாலி முயலும் சிங்கமும்",
            titleEnglish = "The Clever Rabbit and the Lion",
            category = StoryCategory.VILANGU_KATHAIGAL,
            contentTamil = """ஒரு அடர்ந்த காட்டில் பாசுரகன் என்ற பலம் வாய்ந்த சிங்கம் வாழ்ந்து வந்தது. அது தினமும் காட்டில் உள்ள ஏராளமான விலங்குகளை வேட்டையாடித் தின்று அழித்து வந்தது. இதனால் காட்டின் விலங்குகள் அனைத்தும் பயந்து நடுங்கின.

ஒரு நாள் விலங்குகள் அனைத்தும் ஒன்று கூடி சிங்கத்திடம் சென்றன. "அரசே! தாங்கள் தினமும் பல விலங்குகளை வேட்டையாட வேண்டாம். நாங்களே முறைப்படி தினமும் ஒரு விலங்கை உங்கள் உணவாக அனுப்பி வைக்கிறோம்" என்று கூறின. சிங்கமும் அதற்கு ஒப்புக்கொண்டது.

பல நாட்கள் கடந்தன. ஒரு நாள் ஒரு சிறிய, ஆனால் மிகுந்த புத்தி கூர்மை கொண்ட முயலின் முறை வந்தது. அந்த முயல் காட்டு விலங்குகளை சிங்கத்தின் பிடியில் இருந்து காப்பாற்ற ஒரு தந்திரம் யோசித்தது. அது சிங்கத்தின் குகைக்கு மிக மெதுவாகத் தாமதமாகச் சென்றது.

பசியோடு காத்திருந்த சிங்கம் முயலைப் பார்த்து கர்ஜித்தது: "சிறிய முயலே! இவ்வளவு தாமதமாகவா வருவது? உன்னைத் தின்றுவிட்டு காட்டில் உள்ள அத்தனை விலங்குகளையும் இன்றே கொன்று குவிப்பேன்!"

முயல் பணிவுடன் கூறியது: "அரசே! என்னையும் சேர்த்து நான்கு முயல்களைத்தான் மற்ற விலங்குகள் அனுப்பின. ஆனால் வழியில் மற்றொரு பெரிய சிங்கம் எங்களை வழிமறித்தது. அது தன்னைத்தான் இந்தக் காட்டின் உண்மையான ராஜா என்று கூறி மற்ற முயல்களைப் பிடித்துக்கொண்டுவிட்டது. நான் எப்படியோ தப்பித்து உங்களுக்கு இந்த விஷயத்தைச் சொல்ல ஓடிவந்தேன்."

இதைக் கேட்ட பாசுரகன் ஆத்திரத்தின் உச்சிக்குச் சென்றது. "என்னைக் காட்டிலும் பெரிய சிங்கமா! அது எங்கே இருக்கிறது? உடனே என்னிடம் காட்டு!" என்று கர்ஜித்தது.

முயல் சிங்கத்தை ஒரு பழைய ஆழமான கிணற்றின் அருகே அழைத்துச் சென்றது. "அரசே, அந்த சிங்கம் இந்த கோட்டைக்குள்தான் ஒளிந்திருக்கிறது" என்று காட்டியது. பாசுரகன் கிணற்றுக்குள் எட்டிப் பார்த்தது. தண்ணீரில் அதன் சொந்த நிழல் தெரிந்தது. அதை மற்றொரு சிங்கம் என்று நினைத்த பாசுரகன் பலமாகக் கர்ஜித்தது. கிணற்றுக்குள்ளிருந்து எதிரொலி கேட்டது. உடனே பாசுரகன் எதிரி சிங்கத்தைக் கொல்ல நினைத்து கிணற்றுக்குள் பாய்ந்தது. ஆழமான தண்ணீரில் மூழ்கி அந்த சிங்கம் மடிந்தது.

புத்திசாலி முயல் மகிழ்ச்சியுடன் காட்டின் மற்ற விலங்குகளிடம் சென்று விஷயத்தைச் சொன்னது. அனைத்து விலங்குகளும் முயலின் புத்திசாலித்தனத்தைப் பாராட்டி அமைதியாகவும் சுதந்திரமாகவும் வாழ்ந்தன.""",
            contentEnglish = """Once upon a time in a dense forest, there lived a ferocious lion named Bhasuraka. He hunted mercilessly, terrifying all the creatures of the jungle.

The animals met and proposed an arrangement: "O King, instead of hunting us indiscriminately, we shall send one animal each day to your den for your meal." The lion agreed.

Days went by, and eventually it was the turn of a tiny, clever rabbit. The rabbit decided to free the forest from the tyrant. He purposely arrived very late at the lion's cave.

Hungry and furious, the lion roared, "You puny creature! Why are you so late? I shall devour you and exterminate every beast in this forest!"

The rabbit bowed politely: "Your Majesty, four of us were sent to you, but another lion intercepted us, claiming he is the true king of the forest. He took the others, and I barely escaped to bring you this news."

Blinded by fury, the lion demanded to be led to this rival. The clever rabbit led the lion to a deep stone well. "He hides in this fortress, sire," whispered the rabbit. The lion peered inside and saw his own reflection in the clear water. Believing it was his challenger, he roared fiercely. The well echoed back his roar. Enraged, the lion leapt into the deep well and drowned.

The tiny rabbit returned to the jubilant animals, demonstrating that intellect triumphs over raw brute force.""",
            moralTamil = "ஆற்றலை விட புத்திக் கூர்மையே மிகச் சிறந்தது. ஆபத்து வரும் வேளையில் பதறாமல் சிந்திக்க வேண்டும்.",
            moralEnglish = "Intelligence and wisdom are far greater than mere physical strength.",
            storyteller = "பாட்டி (Grandmother)",
            readingTimeMinutes = 3,
            illustrationRes = R.drawable.img_rabbit_lion,
            isFavorite = true,
            isFeatured = true,
            isPopular = true,
            isPublished = true
        ),

        // 2. தாகமுள்ள காகம்
        Story(
            id = "story_thirsty_crow",
            titleTamil = "தாகமுள்ள காகமும் கூழாங்கற்களும்",
            titleEnglish = "The Thirsty Crow and the Pebbles",
            category = StoryCategory.KULANTHAIGAL,
            contentTamil = """கோடை காலம் சுட்டெரித்தது. ஒரு கிராமத்துத் தோட்டத்தில் ஒரு கருப்புக் காகம் கடுமையான தாகத்தால் தவித்துக்கொண்டிருந்தது. அது நீர் தேடி வானில் நெடுந்தூரம் பறந்தது. ஆனால் குளம், குட்டைகள் அனைத்தும் வறண்டு போயிருந்தன.

காகத்தின் நாக்கு வறண்டு, தாகம் தாங்க முடியாமல் ஒரு மரக்கிளையில் அமர்ந்தது. அப்போது ஒரு பழைய வீட்டின் கொல்லைப்புறத்தில் ஒரு மண் கூஜா (மண்பானை) தென்பட்டது. காகம் உடனே பறந்து சென்று பானையின் விளிம்பில் அமர்ந்தது.

உள்ளே பார்த்தால் அடியில் சிறிது தண்ணீர் இருந்தது! காகம் தன் அலகை உள்ளே செலுத்தித் தண்ணீரைக் குடிக்க முயன்றது. ஆனால் அதன் அலகு அடியில் இருந்த தண்ணீரை எட்டவே முடியவில்லை. தண்ணீர் மிகக் குறைவான மட்டத்தில் இருந்தது.

காகம் சோர்ந்து போகவில்லை. "எப்படியாவது இந்தத் தண்ணீரைக் குடிக்க வேண்டும். முயற்சி செய்தால் வழி பிறக்கும்" என்று சுற்றிலும் உற்றுப் பார்த்தது. அங்கே முற்றத்தில் சிறிய கூழாங்கற்கள் இறைந்து கிடப்பதைக் கண்டது.

உடனே காகத்திற்கு ஒரு யோசனை வந்தது. அது தன் அலகால் ஒரு கூழாங்கல்லைக் கவ்வி எடுத்து வந்து பானைக்குள் போட்டது. "டப்" என்ற சத்தத்துடன் கல் தண்ணீரில் விழுந்தது. நீர் மட்டம் சிறிது உயர்ந்தது!

மகிழ்ச்சியடைந்த காகம் விடாமுயற்சியோடு ஒவ்வொரு கூழாங்கல்லாகப் பொறுக்கி எடுத்து வந்து பானைக்குள் போட்டுக் கொண்டே இருந்தது. கற்கள் நிறைய நிறைய, பானையின் அடியில் இருந்த தண்ணீர் மெல்ல மெல்ல விளிம்பு வரை மேலே எழுந்தது.

காகம் மிகுந்த மகிழ்ச்சியோடு குளிர்ந்த நீரை வயிறாரக் குடித்து தன் தாகத்தைத் தீர்த்துக் கொண்டது. பின்னர் புத்துணர்ச்சியோடு வானில் "கா... கா..." என்று உற்சாகமாகக் கரைந்து பறந்தது.""",
            contentEnglish = """On a blistering summer afternoon, a thirsty crow flew across a parched village in search of water. Every pond and puddle had dried up under the scorching sun.

Exhausted, the crow spotted a tall clay pitcher in a village backyard. Hopeful, he perched on the rim and peered inside.

To his sorrow, there was only a little water at the very bottom. He stretched his neck as far as possible, but his beak could not reach the refreshing water.

Instead of despairing, the wise bird surveyed his surroundings. Scattered around the yard were numerous smooth pebbles. An ingenious idea struck him!

Picking up one pebble with his beak, he dropped it into the pitcher. Plop! The water level rose an imperceptible fraction. Patiently, one stone after another, the crow dropped pebbles into the pitcher.

With every stone, the water rose higher and higher until it reached the very brim. The delighted crow drank his fill of the cool, sweet water, and soared high into the sky cawing happily.""",
            moralTamil = "விடாமுயற்சியும் சமயோசித சிந்தனையும் இருந்தால் எத்தகைய கடினமான சவாலையும் வெல்லலாம்.",
            moralEnglish = "Where there is a will and thoughtful persistence, there is always a way.",
            storyteller = "தாத்தா (Grandfather)",
            readingTimeMinutes = 2,
            illustrationRes = R.drawable.img_crow_pot,
            isFavorite = true,
            isFeatured = true,
            isPopular = true,
            isPublished = true
        ),

        // 3. நேர்மையான உழவன்
        Story(
            id = "story_honest_farmer",
            titleTamil = "நேர்மையான உழவனும் தங்கக் கோடரியும்",
            titleEnglish = "The Honest Farmer and the River Deity",
            category = StoryCategory.NEETHI_KATHAIGAL,
            contentTamil = """ஒரு சிறிய கிராமத்தில் கந்தன் என்ற ஏழை உழவன் வசித்து வந்தான். அவன் ஒரு சிறந்த உழைப்பாளி, நேர்மைக்கு பெயர் போனவன். தன் குடும்பத்தைக் காப்பாற்ற அவன் தினமும் ஆற்றுப் படுகையில் உள்ள மரங்களின் காய்ந்த சுள்ளிகளையும் விறகுகளையும் வெட்டி விற்று பிழைப்பு நடத்தி வந்தான்.

ஒரு நாள் அவன் ஆற்றங்கரையில் ஒரு பெரிய மரத்தின் காய்ந்த கிளையை வெட்டிக்கொண்டிருந்தான். திடீரென அவனது கை நழுவி, அவனது ஒரே வாழ்வாதாரமான இரும்புக் கோடரி ஆழமான ஆற்று நீரில் "பொத்" என்று விழுந்து மூழ்கியது.

கந்தன் செய்வதறியாது திகைத்தான். "ஐயோ! என் கோடரி போய்விட்டதே! இனி என் மனைவி பிள்ளைகளுக்கு உணவு வாங்க என்ன செய்வேன்?" என்று ஆற்றுப் படித்துறையில் அமர்ந்து கண்ணீர் விட்டு அழுதான்.

அவனது உண்மையான அழுகையைக் கேட்டு ஆற்றின் தெய்வம் நீர் மேலெழுந்து வந்தது. "மகனே! ஏன் அழுகிறாய்?" என்று பரிவோடு கேட்டது. கந்தன் தன் கோடரி ஆற்றில் விழுந்த விவரத்தைச் சொன்னான்.

ஆற்று தெய்வம் உடனே தண்ணீருக்குள் மூழ்கி, ஒரு மின்னும் தங்கக் கோடரியை எடுத்து வந்தது. "மகனே! இந்தக் கோடரி உன்னுடையதா?" என்று கேட்டது. கந்தன் இரு கைகளையும் கூப்பி, "இல்லை தாயே! இது என் கோடரி இல்லை. நான் ஒரு ஏழை, எனக்கு இரும்புக் கோடரிதான் இருந்தது" என்றான்.

தெய்வம் மீண்டும் மூழ்கி, இந்த முறை ஒரு பளபளக்கும் வெள்ளிக் கோடரியை எடுத்து வந்தது. "இது உன்னுடையதா?" எனக் கேட்டது. கந்தன் புன்னகையுடன், "தாயே, இதுவும் என்னுடையது அல்ல" என்று நேர்மையுடன் மறுத்தான்.

மூன்றாம் முறை மூழ்கிய தெய்வம், அவனது பழைய இரும்புக் கோடரியை எடுத்து வந்தது. அதைப் பார்த்த கந்தன் முகத்தில் பேரானந்தம்! "ஆம் தாயே! இதுதான் என் உழைப்பின் கோடரி!" என்று நன்றியோடு வாங்கினான்.

கந்தனின் தூய்மையான நேர்மையைக் கண்டு உள்ளம் மகிழ்ந்த நதி தெய்வம், "மகனே, இந்த உலகத்தில் பேராசை படாத உன்னைப் போன்ற உத்தமர்கள் அரிது. உனது நேர்மைக்குப் பரிசாக இந்த தங்க மற்றும் வெள்ளிக் கோடரிகளையும் நீயே வைத்துக்கொள்!" என்று ஆசி வழங்கி மறைந்தது. கந்தன் தன் வாழ்நாள் முழுவதும் அந்த நற்குணத்தோடு வாழ்ந்தான்.""",
            contentEnglish = """In a peaceful village lived Kandhan, an industrious and deeply honest farmer. To provide for his family, he gathered fallen timber near the sacred riverbank.

One evening, while chopping a dried limb overhanging the water, the axe slipped from his sweat-slicked grasp and plunged into the river's deep vortex.

Distraught, Kandhan sat weeping on the embankment: "My only axe is lost! How will I feed my young children tomorrow?"

Moved by his sincere grief, the radiant River Goddess emerged from the sparkling currents. Hearing his plight, she dove beneath the waves and resurfaced holding a shimmering axe forged of pure gold. "Is this yours, my son?" she asked.

Kandhan respectfully shook his head: "No, divine Mother. I am but a humble farmer; that opulent axe belongs not to me."

The deity submerged again and brought forth a gleaming silver axe. Kandhan once more declined: "Neither is this mine, Mother."

Finally, she brought up his old, rusted iron axe with its worn wooden handle. Kandhan cried with joy: "Yes, blessed Mother! That is truly my working axe!"

Delighted by his steadfast integrity, the Goddess smiled: "Honesty is the most priceless treasure a person can possess. As a reward for your truthfulness, take both the gold and silver axes." Kandhan prospered while remaining ever humble and true.""",
            moralTamil = "நேர்மையே மனிதனின் மிகப்பெரிய செல்வம். உண்மை பேசினால் தெய்வமும் துணை நிற்கும்.",
            moralEnglish = "Honesty is the most valuable virtue; truth and humility bring lasting blessing.",
            storyteller = "தாத்தா (Grandfather)",
            readingTimeMinutes = 4,
            illustrationRes = R.drawable.img_village_hero,
            isFavorite = true,
            isFeatured = true,
            isPopular = false,
            isPublished = true
        ),

        // 4. பாட்டியின் மந்திரத் தோட்டம்
        Story(
            id = "story_grandmas_garden",
            titleTamil = "பாட்டியின் மந்திரத் தோட்டம்",
            titleEnglish = "Grandmother's Magical Herb Garden",
            category = StoryCategory.PAATI_KATHAIGAL,
            contentTamil = """சுந்தரவல்லி பாட்டி கிராமத்தின் எல்லையில் ஒரு அழகிய ஓட்டு வீட்டில் வசித்து வந்தார். வீட்டின் பின்னால் அவர் வளர்த்து வந்த தோட்டம் ஊரிலேயே மிகவும் பிரபலமானது. துளசி, தூதுவளை, வல்லாரை, முசுமுசுக்கை, கற்றாழை, ஆடாதோடை, வெற்றிலை என எண்ணற்ற பாரம்பரிய மூலிகைகள் அந்தத் தோட்டத்தில் செழித்து வளர்ந்திருந்தன.

கிராமத்தில் யாருக்காவது தலைவலி, சளி, இருமல் அல்லது காய்ச்சல் வந்தால் உடனே பாட்டியின் வீட்டு வாசலில் வந்து நிற்பார்கள். பாட்டி தோட்டத்திலிருந்து சில இலைகளைப் பறித்து, அம்மியில் அரைத்து, சுடச்சுட கஷாயம் போட்டுக் கொடுப்பார். அடுத்த நாளே நோய் பறந்து போய்விடும்!

ஊர் மக்கள் பாட்டியின் தோட்டத்தை "மந்திரத் தோட்டம்" என்று அழைக்கத் தொடங்கினர். ஆனால் பாட்டியின் பேரன் கண்ணன் ஒரு நாள் கேட்டான்: "பாட்டி! நிஜமாகவே உங்க தோட்டத்தில் மந்திர சக்தி இருக்கா? நீங்க என்ன மந்திரம் ஓதுறீங்க?"

பாட்டி புன்னகைத்து அவனைக் கையைப் பிடித்து தோட்டத்திற்கு அழைத்துச் சென்றார். "கண்ணா, இங்கே வா. இயற்கையை விட பெரிய மந்திரவாதி இந்த உலகத்தில் யாரும் இல்லை. இந்த நிலத்தில் நாம் விதைக்கும் ஒவ்வொரு விதையிலும் இறைவன் மருத்துவக் குணங்களை ஒளித்து வைத்திருக்கிறான். நான் செடிகளோடு அன்பாகப் பேசுவேன், சரியான நேரத்தில் நீர் ஊற்றி, இயற்கை உரமிட்டு, என் குழந்தைகளைப் போல கவனித்துக் கொள்வேன். அதுதான் என் மந்திரம்!"

பாட்டி மேலும் கூறினார்: "நாம் முன்னோர்கள் காட்டிய பாரம்பரிய மூலிகைகளைப் பாதுகாப்பதும், அடுத்த தலைமுறைக்குக் கற்றுக் கொடுப்பதும் நமது கடமை. உடல் நலம் என்பது மருந்து மாத்திரைகளில் இல்லை; நாம் உண்ணும் இயற்கை உணவிலும் இயற்கையோடு இயைந்த வாழ்விலும்தான் இருக்கிறது."

கண்ணன் அன்று முதல் பாட்டியுடன் சேர்ந்து தினமும் மூலிகைத் தோட்டத்தைப் பராமரிக்கத் தொடங்கினான். பாட்டியின் உண்மையான அன்பு, கிராமத்தின் தலைமுறைகளுக்கு நல்வாழ்வை அருளும் மந்திரமாக நிலைத்தது.""",
            contentEnglish = """Grandmother Sundaravalli lived at the green edge of the village in an earthen home with a terracotta-tiled roof. Behind her home flourished a garden revered across the region. Thulasi (Holy Basil), Thoothuvalai, Vallarai, Aloe Vera, and betel leaves danced in the fresh breeze.

Whenever a villager had a fever, cold, or exhaustion, they visited Paati. With maternal warmth, she would pluck fresh leaves, grind them on her stone pestle, and brew a soothing herbal decoction (kashayam). By morning, ailments vanished!

Villagers whispered that Paati possessed magic. One afternoon, her inquisitive grandson Kannan asked: "Grandmother, do you truly know secret magic spells? What enchantment do you cast upon your herbs?"

Paati laughed gently, leading him by the hand among the fragrant leaves: "My dear child, Mother Nature is the greatest magician of all. In every seed lies divine healing powers. My only 'spell' is love, clean water, rich compost, and speaking kindly to these plants every dawn."

She taught him that preserving traditional medicinal knowledge and living harmoniously with nature is the secret to enduring health. From that day onward, Kannan proudly joined Paati in nurturing the beloved garden.""",
            moralTamil = "இயற்கையே நமக்கு அருளப்பட்ட மிகப்பெரிய மருந்து. பாரம்பரிய அறிவையும் இயற்கையையும் போற்றி வளர்ப்போம்.",
            moralEnglish = "Nature is our greatest healer. Cherishing traditional wisdom keeps us healthy and wholesome.",
            storyteller = "பாட்டி (Grandmother)",
            readingTimeMinutes = 3,
            illustrationRes = R.drawable.img_village_hero,
            isFavorite = false,
            isFeatured = true,
            isPopular = true,
            isPublished = true
        ),

        // 5. ஞானமுள்ள முதியவரும் ஒற்றுமை கிராமமும்
        Story(
            id = "story_wise_old_man",
            titleTamil = "ஞானமுள்ள முதியவரும் ஒற்றுமை கிராமமும்",
            titleEnglish = "The Wise Old Man and the United Village",
            category = StoryCategory.GRAMATHU_KATHAIGAL,
            contentTamil = """மயிலாடும்பாறை என்ற அழகிய கிராமத்தில் வேலுச்சாமி தாத்தா வாழ்ந்து வந்தார். அவருக்கு வயது தொண்ணூறு இருக்கும். கிராமத்தின் தலைமுறை மாற்றங்களையும் பல வறட்சிகளையும் கண்ட ஞானி அவர்.

ஒரு முறை அந்தக் கிராமத்தில் பருவமழை பொய்த்தது. கிணறுகள் வறண்டு, ஆற்றில் மணல் மட்டுமே சுட்டெரித்தது. விளைநிலங்கள் பாளம்பாளாக வெடித்தன. கிராமத்து இளைஞர்கள் செய்வதறியாது தங்களுக்குள் சண்டையிட்டுக் கொண்டனர்: "எங்கள் தெருவுக்குத்தான் நீர் வர வேண்டும்!", "இல்லை, எங்கள் நிலங்களுக்குத்தான் மிச்சமுள்ள தண்ணீர் வேண்டும்!" என்று மனக்கசப்பு ஏற்பட்டது.

வேலுச்சாமி தாத்தா கிராமத்தின் பெரிய ஆலமரத்தடியில் ஊர் மக்களைக் கூட்டினார். இளைஞர்கள் தங்கள் வாதங்களை முன்வைத்தனர். தாத்தா அமைதியாகக் கேட்டார்.

பின்னர் தன் உதவியாளரிடம் சொல்லி பத்து உறுதியான ஈர்க்குச்சிகளைக் (தென்னங்குச்சி) கொண்டு வரச் சொன்னார். ஒரு குச்சியை எடுத்து ஊரின் பலசாலி வாலிபனிடம் கொடுத்து, "இதை ஒடி" என்றார். அவன் நொடியில் அதை "டக்" என்று இரண்டாக முறித்துப் போட்டான்.

இப்போது தாத்தா மீதமிருந்த அனைத்து குச்சிகளையும் ஒன்றாகச் சேர்த்து ஒரு கயிற்றால் இறுகக் கட்டினார். அதே வாலிபனிடம் கொடுத்து, "இப்போது இந்த கட்டை முறித்துப் பார்" என்றார். அவன் தன் முழு பலத்தையும் பயன்படுத்திப் பார்த்தான், குச்சிக் கட்டு அசையவே இல்லை!

தாத்தா புன்னகையுடன் சொன்னார்: "பார்த்தீர்களா மக்களே! தனித்தனியாக இருக்கும்போது நீங்கள் இந்த ஒற்றைக் குச்சியைப் போல பலவீனமானவர்கள். யார் வேண்டுமானாலும் உங்களை உடைத்துவிடலாம். ஆனால் நாம் அனைவரும் ஒன்றுபட்டு நின்றால், எத்தகைய கடும் வறட்சியையும் சவாலையும் நம்மால் வெல்ல முடியும்!"

தாத்தாவின் அறிவுரையை உணர்ந்த ஊர் மக்கள் வெட்கித் தலைகுனிந்தனர். உடனே அனைவரும் கைகோர்த்து, ஊரின் எல்லையில் உள்ள பழங்காலக் குளத்தைத் தூர்வாரி, கால்வாய்களைச் சீரமைக்கும் பணியில் ஒற்றுமையுடன் இறங்கினர். அடுத்த வாரமே வானம் கருமேகங்களால் சூழ்ந்து பெருமழை பெய்தது. ஊர் மக்கள் தூர்வாரிய குளம் நிரம்பி வழிந்தது. ஒற்றுமையின் மகிமையை ஊரே கொண்டாடியது.""",
            contentEnglish = """In the picturesque village of Mayiladumparai lived Grandpa Velusamy, a venerable elder of ninety years whose wisdom had guided generations through triumphs and famines.

One scorching year, the monsoons failed. The river ran dry, fields cracked, and scarcity ignited bitter quarrels among the villagers over water rights.

Grandpa Velusamy summoned the entire village beneath the shade of the ancient banyan tree. The youths argued fiercely. The elder listened with serene patience.

He then asked for a bundle of slender coconut leaf sticks. Handing a single stick to the strongest youth, he said, "Snap this in two." With effortless ease, the young man snapped it instantly.

Next, Grandpa Velusamy bound all the remaining sticks tightly with jute rope. "Now, break this bundle," he challenged. The muscular youth strained with all his might, veins bulging on his forehead, but could not bend it in the slightest.

Grandpa Velusamy smiled: "Look, my children. When divided, each of you is as vulnerable as that lone stick. Anyone can break your spirit. But when you stand united in love and brotherhood, no hardship on earth can conquer you."

Chastened and enlightened, the villagers embraced. Together, they dug deep rainwater harvesting reservoirs and cleared ancient irrigation channels. When the monsoon arrived days later, their restored lake filled to the brim, nourishing the community for years to come.""",
            moralTamil = "கூடி வாழ்ந்தால் கோடி நன்மை. ஒற்றுமையே பலம்; வேற்றுமை அழிவைத் தரும்.",
            moralEnglish = "Unity is strength; divided we fall, united we prosper.",
            storyteller = "தாத்தா (Grandfather)",
            readingTimeMinutes = 4,
            illustrationRes = R.drawable.img_village_hero,
            isFavorite = true,
            isFeatured = false,
            isPopular = true,
            isPublished = true
        ),

        // 6. தெனாலிராமன் கதைகள்
        Story(
            id = "story_tenali_mangoes",
            titleTamil = "தெனாலிராமனும் தங்க மாம்பழங்களும்",
            titleEnglish = "Tenali Raman and the Golden Mangoes",
            category = StoryCategory.TENALI_RAMAN,
            contentTamil = """விஜயநகர மன்னர் கிருஷ்ணதேவராயரின் தாயார் மாம்பழங்களை மிகவும் விரும்பி உண்பவர். ஒரு சமயம் அவர் நோய்வாய்ப்பட்டு படுக்கையில் இருந்தபோது, ஒரு சுவையான மாம்பழம் சாப்பிட வேண்டும் என்று ஆசைப்பட்டார்.

மன்னர் உடனே மாம்பழம் கொண்டு வர ஆட்களை அனுப்பினார். ஆனால் மாம்பழம் வருவதற்குள் அவரது தாயார் உயிர் பிரிந்துவிட்டது. தாயின் கடைசி ஆசையை நிறைவேற்ற முடியவில்லையே என்று மன்னர் மிகுந்த மனவேதனை அடைந்தார்.

இதை அறிந்த சில தந்திரமான அரண்மனை புரோகிதர்கள் மன்னரிடம் வந்தனர். "மன்னா, உங்கள் தாயாரின் ஆத்மா சாந்தியடைய வேண்டுமானால், நீங்கள் பிராமணர்களுக்கு தங்கத்தால் ஆன மாம்பழங்களை தானமாக வழங்க வேண்டும்" என்று கூறினர். மன்னரும் அதற்கு ஒப்புக்கொண்டு பொற்கொல்லர்களை அழைத்து தங்க மாம்பழங்களைச் செய்ய உத்தரவிட்டார்.

இந்தச் செய்தி அரசவை விகடகவி தெனாலிராமனுக்கு எட்டியது. புரோகிதர்களின் பேராசையை அடக்க அவன் ஒரு திட்டம் தீட்டினான். புரோகிதர்களை அணுகி, "என் தாயாரும் இறக்கும் தருவாயில் ஒரு விருப்பம் கொண்டிருந்தார். மன்னரின் தானம் முடிந்ததும் நீங்கள் என் வீட்டிற்கும் வந்து எனக்கும் உதவி செய்ய வேண்டும்" என்று வேண்டினான். தங்க மாம்பழம் கிடைக்கப் போகும் ஆசையில் அவர்களும் ஒப்புக்கொண்டனர்.

மன்னரிடம் தங்க மாம்பழங்களை தானமாகப் பெற்றுக்கொண்டு புரோகிதர்கள் தெனாலிராமன் வீட்டிற்குச் சென்றனர். அங்கே தெனாலிராமன் ஒரு பழுக்கக் காய்ச்சிய இரும்புச் சூட்டுக்கோலை கையில் வைத்திருந்தான்!

புரோகிதர்கள் பயந்து நடுங்கி, "ராமா! இது என்ன?" என்று கேட்டனர். தெனாலிராமன் அடக்கத்துடன் கூறினான்: "ஐயா, என் தாயாருக்கு மூட்டு வலி இருந்தது. இறக்கும் முன் சூடு போட்டுக் கொள்ள ஆசைப்பட்டார். ஆனால் அதற்குள் இறந்துவிட்டார். அவரது ஆத்மா சாந்தியடைய உங்கள் கைகளில் இந்த பழுக்கக் காய்ச்சிய இரும்பால் சூடு போட வேண்டும்!"

புரோகிதர்கள் அலறி அடித்துக் கொண்டு மன்னரிடம் ஓடி முறையிட்டனர். மன்னர் தெனாலிராமனை அழைத்து சினத்துடன் விசாரித்தார். தெனாலிராமன் புன்னகைத்து, "மன்னா, தாயின் ஆசைக்காக தங்க மாம்பழம் தானம் கொடுக்கலாம் என்றால், என் தாயின் ஆசைக்காக சூடு போடுவதில் என்ன தவறு? தங்க மாம்பழம் எப்படி இறந்தவரின் பசியைத் தீர்க்கும்?" என்று கேட்டான்.

மன்னர் உண்மை உணர்ந்து புரோகிதர்களின் பேராசையைக் கண்டித்தார். தெனாலிராமனின் சமயோசித புத்தியைப் பாராட்டினார்.""",
            contentEnglish = """King Krishnadevaraya's mother fell ill and expressed a dying wish to taste a ripe mango. Before the royal attendants could return with the fruit, she breathed her last. The King was filled with deep remorse.

Opportunistic palace priests took advantage of the grief-stricken king, proclaiming: "Your Majesty, to grant your mother's soul eternal peace, you must distribute solid gold mangoes to the priests." The King ordered golden mangoes to be crafted.

Hearing of this exploitation, witty court jester Tenali Raman hatched a plan. He invited the priests to his home after the royal donation ceremony, hinting at further offerings in his late mother's memory.

After receiving gold mangoes from the King, the greedy priests hurried to Raman's house. To their horror, they found Raman standing beside glowing red-hot branding irons!

"What is this madness, Raman?" they gasped. Raman said earnestly, "My mother suffered from severe joint aches and longed for thermal cauterization on her arms before she passed. To pacify her departed spirit, I must apply these hot irons to your arms!"

The terrified priests fled back to the King. When interrogated, Tenali Raman bowed respectfully: "Your Majesty, if giving gold mangoes to priests can satisfy your mother's earthly appetite, surely branding priests will relieve my mother's bodily aches in the heavens!"

The King burst out laughing, realized how his grief had been manipulated, and praised Tenali Raman's quick wit.""",
            moralTamil = "மூடநம்பிக்கையையும் பிறரின் பேராசையையும் அறிவுக்கூர்மையால் மட்டுமே தகர்க்க முடியும்.",
            moralEnglish = "Superstition and greedy trickery are best unmasked with sharp reason and wit.",
            storyteller = "தாத்தா (Grandfather)",
            readingTimeMinutes = 4,
            illustrationRes = R.drawable.img_village_hero,
            isFavorite = false,
            isFeatured = false,
            isPopular = true,
            isPublished = true
        ),

        // 7. அக்பர் பீர்பால்: காற்றில் நின்ற விளக்கு
        Story(
            id = "story_akbar_birbal_lamp",
            titleTamil = "அக்பரும் பீர்பாலும்: காற்றில் நின்ற விளக்கு",
            titleEnglish = "Akbar and Birbal: The Warmth of the Distant Light",
            category = StoryCategory.AKBAR_BIRBAL,
            contentTamil = """குளிர்காலம் உச்சத்தில் இருந்த ஒரு மாலை நேரம். பேரரசர் அக்பரும் அவரது முதன்மை ஆலோசகர் பீர்பாலும் யமுனை நதிக்கரையில் உலாவிக் கொண்டிருந்தனர். தண்ணீர் பனிக்கட்டி போல் சில்லிட்டிருந்தது.

அக்பர் விளையாட்டாக, "பீர்பால், ஒரு மனிதன் பணத்திற்காக இந்த கொட்டும் பனி நீரில் இரவு முழுவதும் நின்று கொண்டிருப்பானா?" என்று கேட்டார். பீர்பால், "மன்னா, ஏழ்மையில் வாடும் ஒருவனுக்குத் தகுந்த பரிசு தருவதாக வாக்களித்தால் எதையும் செய்வான்" என்றார்.

அக்பர் நகரில் அறிவித்தார்: "யமுனை ஆற்று நீரில் விடிய விடிய கழுத்தளவு தண்ணீரில் நிற்பவருக்கு ஆயிரம் பொற்காசுகள் பரிசாக வழங்கப்படும்."

தீனு என்ற ஏழை சலவைத் தொழிலாளி தன் குடும்பத்தின் வறுமையைப் போக்க முன்வந்தான். நடுங்கும் குளிரில் இரவு முழுவதும் ஆற்று நீரில் நின்றான். காலையில் அரண்மனைக்குச் சென்று பரிசை வேண்டினான்.

அக்பர் வியப்புடன், "முழு இரவும் இவ்வளவு குளிரில் எப்படி தாக்குப் பிடித்தாய்?" என்று கேட்டார். தீனு உண்மையோடு கூறினான்: "மன்னா, அரண்மனை மாடத்தில் எரிந்து கொண்டிருந்த ஒரு சிறிய நெய் விளக்கின் ஒளியைப் பார்த்துக் கொண்டே இரவைக் கழித்தேன்."

இதைக் கேட்ட அக்பர், "அப்படியானால் அரண்மனை விளக்கின் வெப்பத்தைப் பெற்றுத்தான் நீ உயிர் பிழைத்திருக்கிறாய்! இது ஏமாற்று வேலை. உனக்குப் பரிசு கிடையாது!" என்று விரட்டிவிட்டார். தீனு அழுதுகொண்டே பீர்பாலிடம் சென்றான்.

மறுநாள் பீர்பால் அரசவைக்கு வரவில்லை. அக்பரே பீர்பாலின் வீட்டிற்குச் சென்றார். அங்கே பீர்பால் தரையில் நெருப்பு மூட்டி, அதிலிருந்து பத்து அடி உயரத்தில் ஒரு பாத்திரத்தில் அரிசியும் தண்ணீரும் வைத்து காத்திருந்தார்!

அக்பர் வியந்து, "பீர்பால், என்ன இது பைத்தியக்காரத்தனம்? பத்து அடி உயரத்தில் இருக்கும் பாத்திரத்திற்கு இந்த நெருப்பின் வெப்பம் எப்படி எட்டும்? சோறு எப்படி வேகும்?" என்றார்.

பீர்பால் அமைதியாகக் கூறினார்: "மன்னா, மைல் தூரத்தில் உள்ள அரண்மனை விளக்கின் வெப்பம் ஆற்று நீரில் இருந்த தீனுவின் உடலைச் சூடேற்ற முடியும் என்றால், பத்து அடி உயரத்தில் இருக்கும் என் சோறும் வெந்துவிடும்!"

அக்பர் தன் தவற்றை உணர்ந்து வெட்கித் தலைகுனிந்தார். உடனடியாக தீனுவை வரவழைத்து இரண்டாயிரம் பொற்காசுகளைப் பரிசளித்து கௌரவித்தார்.""",
            contentEnglish = """On a freezing winter evening, Emperor Akbar and his wise advisor Birbal walked along the frosty banks of the Yamuna River.

Akbar mused: "Birbal, would a man stand in this freezing water all night merely for money?" Birbal replied: "Your Majesty, a destitute man will endure great trials for his starving family if offered adequate reward."

Akbar announced a prize of one thousand gold coins to anyone who would stand neck-deep in the river overnight. Deenu, a poor washerman, took up the challenge and stood shivering in the biting cold through dawn.

When Deenu requested his reward, Akbar asked: "How could you bear such freezing cold without succumbing?" Deenu answered honestly: "Your Majesty, I kept my eyes fixed on the distant lantern burning atop your palace terrace, which gave me psychological endurance."

Akbar proclaimed: "You survived by absorbing warmth from my palace lamp! You have cheated. No reward shall be given!" Heartbroken, Deenu sought Birbal's help.

The following day, Birbal did not attend court. Akbar visited his home and found Birbal sitting by a tiny fire on the ground, with a cooking pot suspended ten feet high in a tree!

Akbar exclaimed: "Birbal, are you out of your mind? How can a fire reach a pot ten feet away to cook rice?"

Birbal smiled respectfully: "Your Majesty, if the gentle glow of a palace lamp miles away can warm a man immersed in a freezing river, surely this pot will cook quickly!"

Akbar immediately recognized his injustice, summoned Deenu, and rewarded him two thousand gold coins with royal honor.""",
            moralTamil = "அதிகாரத்தால் அநீதி இழைக்கக் கூடாது. வறியவர்களின் உழைப்பையும் நேர்மையையும் மதிக்க வேண்டும்.",
            moralEnglish = "Power must never blind justice. The labor and endurance of the humble must be honored.",
            storyteller = "தாத்தா (Grandfather)",
            readingTimeMinutes = 4,
            illustrationRes = R.drawable.img_village_hero,
            isFavorite = false,
            isFeatured = false,
            isPopular = false,
            isPublished = true
        ),

        // 8. தமிழ் நாட்டுப்புறக் கதை: காக்கைக்கும் குருவிக்கும் நட்பு
        Story(
            id = "story_folk_crow_sparrow",
            titleTamil = "பாட்டியின் வடை சுட்ட கதையின் புதிய முடிவு",
            titleEnglish = "The Grandmother's Vada - A Wise Tale of Friendship",
            category = StoryCategory.NAATTUPURA_KATHAIGAL,
            contentTamil = """பழங்காலத்தில் நாம் கேட்ட கதையில், காக்கை ஒன்று பாட்டியிடம் வடை திருடிப் போனதாகவும், நரி ஏமாற்றிப் பறித்ததாகவும் கேட்டிருப்போம். ஆனால் நமது தாத்தா சொன்ன நாட்டுப்புறக் கதையில் ஒரு அழகான திருப்பம் இருக்கிறது!

ஒரு பசுமையான கிராமத்தில் கமலம் பாட்டி ஒரு பெரிய ஆலமரத்தடியில் அமர்ந்து சுடச்சுட மொறுமொறுவென மெதுவடை சுட்டுக் கொண்டிருந்தார். வடையின் நறுமணம் காற்றில் பரவியது.

மரக்கிளையில் அமர்ந்திருந்த ஒரு புத்திசாலி காக்கை, பாட்டியை ஏமாற்றி வடையைத் திருட நினைக்கவில்லை. அது பறந்து சென்று தோட்டத்தில் பழுத்துக் குலுங்கிய சில நாவல் பழங்களையும் காட்டு நெல்லிக்கனிகளையும் தன் அலகில் கொத்தி எடுத்து வந்தது.

பாட்டியின் முன் வைத்து, "கா... கா..." என்று மரியாதையோடு கரைந்தது. பாட்டி வியப்புடன் பார்த்தார். காக்கை பழங்களை பாட்டியிடம் தந்துவிட்டு, வடையைப் பார்த்தது!

பாட்டி சிரித்துக் கொண்டே, "அடடே! காக்கைக்குள்ளும் எவ்வளவு நேர்மை! பண்டமாற்று முறையை எவ்வளவு அழகாகச் செய்கிறது!" என்று பாராட்டி, ஒரு பெரிய சுவையான சூடான வடையை வாழை இலையில் வைத்து காக்கைக்குக் கொடுத்தார்.

காக்கை வடையை எடுத்துக் கொண்டு தன் மரத்திற்குச் சென்றது. அங்கே ஒரு தந்திர நரி வந்து, "காக்கையாரே, உங்கள் குரல் மிகவும் இனிமை, ஒரு பாட்டு பாடுங்களேன்" என்று வழக்கம் போல் ஏமாற்றப் பார்த்தது.

காக்கை தன் கால்களால் வடையை மரக்கிளையில் கெட்டியாகப் பிடித்துக் கொண்டு, "நரியாரே! நான் பழைய காலத்து முட்டாள் காக்கை இல்லை. நேர்மையாக உழைத்துப் பாட்டியிடம் வாங்கிய வடை இது. உன் தந்திரம் என்னிடம் பலிக்காது!" என்று பாடியது.

நரி ஏமாந்து வாலைச் சுருட்டிக்கொண்டு ஓடியது. காக்கை அந்த வடையைத் தன் நண்பர்களான சிட்டுக் குருவிகளுடனும் பகிர்ந்து உண்டு மகிழ்ந்தது.""",
            contentEnglish = """Generations have heard the traditional folklore of the crow, the grandmother making vadas, and the sly fox. But in our village, Grandpa tells a refreshing, heartwarming twist!

In a breezy village, Paati Kamalam was frying aromatic, crispy lentil vadas beneath the spreading banyan tree. The irresistible aroma drifted across the fields.

Perched above was an intelligent crow. Instead of stealing, the crow flew to a secluded orchard, harvested fresh wild gooseberries and sweet blackberries, and laid them neatly on Paati's tray, cawing politely.

Paati chuckled in admiration: "Look at this honorable bird understanding the ancient art of fair trade!" She gladly handed the crow a delicious warm vada served on a banana leaf.

As the crow rested on a branch, the legendary cunning fox trotted beneath: "O melodious crow, sing for me so I may hear your golden voice!"

Wisely clamping the vada securely beneath his talons, the crow called down: "Clever fox, I am not a gullible bird of old. This vada was earned through honest trade; your flattery cannot deceive me!"

Defeated, the fox slunk away into the bushes, while the crow shared the warm feast with neighboring sparrows.""",
            moralTamil = "நேர்மையான உழைப்பால் கிடைத்த உணவே இனிமையானது. பேராசையும் தந்திரமும் ஒருபோதும் நிலைக்காது.",
            moralEnglish = "Honest exchange and mindful vigilance protect what is rightfully earned.",
            storyteller = "பாட்டி (Grandmother)",
            readingTimeMinutes = 3,
            illustrationRes = R.drawable.img_crow_pot,
            isFavorite = true,
            isFeatured = false,
            isPopular = true,
            isPublished = true
        ),

        // 9. தாத்தாவின் பனைமரமும் சிட்டுக் குருவிகளும்
        Story(
            id = "story_palmyra_sparrow",
            titleTamil = "தாத்தாவின் பனைமரமும் சிட்டுக் குருவிகளும்",
            titleEnglish = "Grandpa's Palmyra Palm and the Weavers",
            category = StoryCategory.THAATHA_KATHAIGAL,
            contentTamil = """மாரிமுத்து தாத்தாவின் வயல் வரப்பில் கம்பீரமாக நின்றது ஒரு நூற்றாண்டு பழமையான பனைமரம். தமிழ்நாட்டின் மாநில மரமான பனைமரம், மண்ணின் ஈரப்பதத்தைக் காக்கும் காவல் தெய்வம் என்பது தாத்தாவின் அசைக்க முடியாத நம்பிக்கை.

அந்தப் பனைமரத்தின் அடர்ந்த ஓலைகளில் நூற்றுக்கணக்கான தூக்கணாங்குருவிகள் (weaver birds) தங்களின் அழகிய கூடுகளைத் தலைகீழாகக் கட்டி வாழ்ந்து வந்தன. காற்று பலமாக வீசும்போது கூட அந்தக் கூடுகள் அசைந்தாடுமே தவிர கிழே விழாது. இரவில் மின்மினிப் பூச்சிகளைப் பிடித்து கூட்டில் வைத்து இயற்கை விளக்கு ஏற்றுவது அவற்றின் விந்தை!

ஒரு நாள் ஊரில் ஒரு செல்வந்தர் தாத்தாவிடம் வந்தார். "பெரியவரே! உங்கள் பனைமரத்தை வெட்டி விற்றுவிடுங்கள். நல்ல விலை தருகிறேன். நிலமும் காலியாகும்" என்றார்.

தாத்தா மெதுவாக எழுந்து நின்றார். "தம்பி! இந்தப் பனைமரம் வெறும் மரமல்ல. இது நிலத்தடி நீரைச் சேமித்து என் கிணற்றைக் காப்பாற்றுகிறது. இதன் நுங்கு ஊருக்குக் குளிர்ச்சி, இதன் ஓலை ஏழைகளின் கூரைக்கு நிழல், இதன் பதநீர் உடலுக்கு அமிர்தம். எல்லாவற்றிற்கும் மேலாக, என் கண்ணெதிரே இந்த மரத்தில் வாழும் நூற்றுக்கணக்கான குருவிக் குடும்பங்களின் தாய் வீடு இது. பணத்திற்காக என் வீட்டு விருந்தாளிகளை நான் வீதியில் வீச மாட்டேன்!" என்று உறுதியாக மறுத்தார்.

சில மாதங்களில் கடுமையான புயல் அடித்தது. பல மரங்கள் வேரோடு சாய்ந்தன. ஆனால் ஆழமான வேர்களைக் கொண்ட தாத்தாவின் பனைமரம் புயலிலும் அசைவின்றி நின்றது. குருவிக் குஞ்சுகளும் பாதுகாப்பாக இருந்தன.

அடுத்த தலைமுறைக்கு இயற்கையை எப்படிப் பாதுகாக்க வேண்டும் என்பதை தாத்தாவின் அந்த உறுதியான முடிவு ஊருக்கே உணர்த்தியது.""",
            contentEnglish = """On the edge of Grandpa Marimuthu's paddy field stood a towering, century-old Palmyra palm tree. To Grandpa, the Palmyra was the sentinel of Tamil soil and underground water conservation.

High amidst its fan-shaped fronds hung dozens of intricately woven nests crafted by ingenious weaver birds. Even in fierce gales, the artistic nests swayed gracefully without falling.

One day, a wealthy timber merchant offered a hefty sum to fell the tree. Grandpa Marimuthu gently yet firmly refused: "This palmyra is not timber for sale. It safeguards the groundwater in my well, offers sweet nungu fruit in summer, provides thatch for village roofs, and most sacredly, serves as an ancestral sanctuary for countless bird families. No amount of money can replace life and nature."

When a cyclonic tempest struck the region months later, flimsy trees toppled, yet the deeply rooted Palmyra stood unmoved, shielding the birds and the village boundary. The entire village hailed Grandpa's wisdom.""",
            moralTamil = "மரங்களும் பறவைகளும் நம் பூமியின் உண்மையான செல்வங்கள். இயற்கையைக் காப்பது நம் கடமை.",
            moralEnglish = "Preserving nature and wildlife protects our future far beyond material wealth.",
            storyteller = "தாத்தா (Grandfather)",
            readingTimeMinutes = 3,
            illustrationRes = R.drawable.img_village_hero,
            isFavorite = false,
            isFeatured = false,
            isPopular = false,
            isPublished = true
        ),

        // 10. புராணக் கதை: நெல்லிக்கனியும் அவ்வையாரும்
        Story(
            id = "story_avvaiyar_gooseberry",
            titleTamil = "நெல்லிக்கனியும் அதியமான் அவ்வையாரும்",
            titleEnglish = "Avvaiyar and King Athiyaman's Rare Gift",
            category = StoryCategory.PURANA_KATHAIGAL,
            contentTamil = """சங்க காலத்தில் தகடூர் பகுதியை ஆண்டு வந்த அதியமான் என்ற குறுநில மன்னன், தமிழையும் தமிழ் புலவர்களையும் தன் கண்ணைப் போலப் போற்றி வந்தான். முதுபெரும் தமிழ்ப் புலவர் அவ்வையார் மீது அவன் மிகுந்த மதிப்பும் பாசமும் கொண்டிருந்தான்.

ஒரு சமயம் அதியமானின் வீரர்கள் காட்டில் உள்ள செங்குத்தான மலையுச்சியில் இருந்து ஒரு அபூர்வமான கருநெல்லிக் கனியைக் கொண்டு வந்தனர். அந்த நெல்லிக்கனியை உண்பவர்கள் நீண்ட காலம் நோயின்றி, சாகா வரம் பெற்று வாழ்வார்கள் என்பது ஐதீகம்.

மன்னனின் அமைச்சர்கள் கூறினர்: "மன்னா! நீங்கள் இந்தக் கனியை உண்ண வேண்டும். நீங்கள் நீண்ட காலம் வாழ்ந்தால்தான் இந்த நாடு செழிப்பாக இருக்கும்!"

அதியமான் சிறிது நேரம் ஆழ்ந்து சிந்தித்தான். அவனது நினைவில் தமிழ் மூதாட்டி அவ்வையார் வந்தார்.

அரண்மனைக்கு வந்த அவ்வையாரை அதியமான் அன்புடன் வரவேற்றான். உபசரித்து, அந்த அரிய நெல்லிக்கனியை ஒரு தங்கத் தட்டில் வைத்து அவ்வையாரிடம் உண்ணக் கொடுத்தான்.

அவ்வையார் அதை ஒரு சாதாரண நெல்லிக்கனி என்று நினைத்து ருசித்து உண்டார். பின்னர் அதியமான் அதன் பெருமையையும், உண்டவர்கள் நீண்ட ஆயுள் பெறுவார்கள் என்ற உண்மையையும் கூறினான்.

அவ்வையார் திடுக்கிட்டார்: "அதியமானே! இவ்வளவு அரிய கனியை எனக்குக் கொடுத்துவிட்டாயே! நீயல்லவா இதை உண்டிருக்க வேண்டும்? நீ வாழ்ந்தால் நாடு வாழும், மக்கள் வாழ்வார்கள்!" என்று நெகிழ்ந்து கேட்டார்.

அதியமான் பணிவோடு தலைவணங்கினான்: "தாயே! நான் ஒரு மன்னன் மட்டுமே. நான் இறந்தால் மற்றொரு மன்னன் ஆட்சிக்கு வந்துவிடுவான். ஆனால் நீங்கள் நீண்ட காலம் வாழ்ந்தால் மட்டுமே இந்த உலகத்திற்குத் தூய தமிழ் அமுதமும் நல்வழியும் கிடைக்கும். எனக்காக அல்ல, தமிழ் மொழிக்காகவே இக்கனியை உங்களுக்குத் தந்தேன்!"

மன்னனின் தன்னலமற்ற தமிழ்ப் பற்றையும் பெருந்தன்மையையும் கண்டு அவ்வையாரின் கண்கள் பனித்தன. அதியமானை வாழ்த்தி அமரத்துவம் வாய்ந்த பாடல்களைப் பாடினார்.""",
            contentEnglish = """During the Sangam era, the righteous King Athiyaman ruled Thagadoor with deep devotion to Tamil literature. He held the venerable sage-poetess Avvaiyar in highest esteem.

Hunters brought the King an extraordinarily rare wild black gooseberry (Nellikani) harvested from an inaccessible mountain precipice. Legend held that whoever ate this celestial fruit would attain longevity and immunity against disease.

Ministers urged: "O King, consume this sacred fruit so that your benevolent reign shall protect our kingdom for centuries!"

Athiyaman pondered deeply and thought of Avvaiyar. When the elderly poetess arrived at the royal court, Athiyaman presented the fruit on a golden plate, requesting her to partake in it.

Thinking it a customary orchard fruit, Avvaiyar ate it with delight. Only then did the King reveal its miraculous nature.

Avvaiyar was astonished: "Noble Athiyaman! Why did you bestow this life-giving gift upon an aged wanderer instead of preserving your own royal life?"

Athiyaman bowed with folded palms: "Revered Mother, if I perish, another king will govern this throne. But if your life is preserved, immortal Tamil wisdom and moral poetry will enlighten humanity for millenia to come. My gift was dedicated to our eternal mother tongue!"

Deeply moved by his selfless magnanimity, Avvaiyar blessed the monarch with immortal verses that echo across history.""",
            moralTamil = "தன்னலமற்ற தியாகமும், அறிவுக்கும் கல்விக்கும் செலுத்தும் மரியாதையுமே ஒரு மனிதனை வரலாற்றில் வாழ வைக்கும்.",
            moralEnglish = "Selfless sacrifice and elevating wisdom above personal ambition creates true immortality.",
            storyteller = "தாத்தா (Grandfather)",
            readingTimeMinutes = 4,
            illustrationRes = R.drawable.img_village_hero,
            isFavorite = true,
            isFeatured = true,
            isPopular = false,
            isPublished = true
        )
    )
}
