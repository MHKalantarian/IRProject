package com.hadisaboohi.irproject.Data;


import android.content.Context;

import com.hadisaboohi.irproject.Helper.Normalizer;
import com.hadisaboohi.irproject.Helper.Stemmer;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Document.class, Word.class}, version = 1, exportSchema = false)
@TypeConverters(Converter.class)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public synchronized static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = buildDatabase(context);
        }
        return INSTANCE;
    }

    private static AppDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class,
                "irproject-db")
                .allowMainThreadQueries()
                .build();
    }

    private static ArrayList<Document> populateData() {
        return new ArrayList<Document>() {{
            add(new Document("doc1", "جاوا (زبان برنامه\u200Cنویسی)", "از ویکی\u200Cپدیا، دانشنامهٔ آزاد\n" +
                    "جاوا (به انگلیسی: Java) یک زبان برنامه\u200Cنویسیِ شیءگرا است. نخستین بار توسط جیمز گاسلینگ در شرکت سان مایکروسیستمز ایجاد گردید. در سال ۱۹۹۵ به عنوان بخشی از سکوی جاوا منتشر شد. زبان جاوا شبیه به ++C است اما مدل شیءگرایی آسان\u200Cتری دارد و از قابلیت\u200Cهای سطح پایین کمتری پشتیبانی می\u200Cکند. ایده شیء گرایی جاوا از زبان اسمال\u200Cتاک گرفته شده است. یکی از قابلیت\u200Cهای بنیادین جاوا این است که مدیریت حافظه را بطور خودکار انجام می\u200Cدهد. ضریب اطمینان عملکرد برنامه\u200Cهای نوشته\u200Cشده به این زبان نسبت به زبانهای نسل اول C بالا است. برنامه\u200Cهای جاوا به صورت بایت کد (کامپایل) می\u200Cشوند و توسط JVM (ماشین مجازی جاوا) به کدهای ماشین تبدیل و اجرا می\u200Cشوند. در صورت وجود JVM مانند سایر زبانهای مبتنی بر آن که وابسته به سیستم\u200Cعامل خاصی نیستند برنامه\u200Cهای نوشته شده به جاوا بر روی هر نوع سیستم عامل و هرگونه وسیلهٔ الکترونیکی قابل اجرا می\u200Cباشند. شعار جاوا «یکبار بنویس و هر جایی اجرا کن» (Write once, Run anywhere) است که به همین ویژگی اشاره دارد.[۱۰]"));
            add(new Document("doc2", "سی پلاس\u200Cپلاس", "از ویکی\u200Cپدیا، دانشنامهٔ آزاد\n" +
                    "++C (بخوانید سی پلاس\u200Cپلاس) یک زبان برنامه\u200Cنویسی رایانه\u200Cای همه\u200Cمنظوره، شیءگرا، سطح میانی و چندرگه (که از برنامه\u200Cنویسی رویه\u200Cای، تجرید داده\u200Cها و برنامه\u200Cنویسی شیءگرا پشتیبانی می\u200Cکند)، عمومی و با قابلیت\u200Cهای سطح بالا و سطح پایین می\u200Cباشد. این زبان دارای قابلیت\u200Cهای انواع داده ایستا، نوشتار آزاد، چندمدلی، معمولاً زبان ترجمه شده با پشتیبانی از برنامه\u200Cنویسی ساخت\u200Cیافته، برنامه\u200Cنویسی شیءگرا، برنامه\u200Cنویسی جنریک است. از آنجا که در سی++ اشیاء را می\u200Cتوان ابتدا به ساکن از کلاس\u200Cهایی ایجاد کرد که به هیچگونه سلسله مراتب رده\u200Cها و وراثت مقید نیستند، لذا سی++ از برنامه\u200Cسازی شیء بنیاد (object-based programming) نیز پشتیبانی می\u200Cکند.[۱] ++C به همراه جد خود C از پرطرفدارترین زبان\u200Cهای برنامه\u200Cنویسی تجاری هستند.\n" +
                    "\n" +
                    "++C یک زبان سطح میانی در نظر گرفته می\u200Cشود؛ این زبان دارای قابلیت زبان\u200Cهای سطح بالا و پایین به\u200Cصورت هم\u200Cزمان است.\n" +
                    "\n" +
                    "++C توسط بی\u200Cیارنه استراس\u200Cتروپ ریاضیدان دانمارکی در سال ۱۹۷۹ در آزمایشگاه\u200Cهای بل (Bell Labs)، برای بهبود زبان سی و بر مبنای آن ساخته شد و آن را «C با کلاس» (C With Classes) نام\u200Cگذاری نمود. در سال ۱۹۸۳ به ++C تغییر نام داد. توسعه با اضافه نمودن کلاس\u200Cها و ویژگی\u200Cهای دیگری مانند توابع مجازی، سربارگزاری عملگرها، وراثت چندگانه، قالب توابع، و پردازش استثناء انجام شد. این زبان برنامه\u200Cنویسی در سال ۱۹۹۸ تحت نام ISO/IEC ۱۴۸۸۲:۱۹۹۸ استاندارد شد. نسخهٔ فعلی استاندارد این زبان ISO/IEC ۱۴۸۸۲:۲۰۱۴ است.[۲][۳]"));
            add(new Document("doc3", "سی شارپ", "از ویکی\u200Cپدیا، دانشنامهٔ آزاد\n" +
                    "سی شارپ (به انگلیسی: C#)، زبانی شیءگرا و سطح بالا از خانوادهٔ زبان\u200Cهای چارچوب دات\u200Cنت شرکت مایکروسافت است.\n" +
                    "\n" +
                    "زبان سی شارپ، یک زبان برنامه\u200Cنویسی چند الگویی و منظم شده مدل\u200Cهای تابعی، امری، عمومی، شیءگرا و جز گرا و در بستر دات نت می\u200Cباشد. این زبان توسط مایکروسافت و جزئی از دات نت به وجود آمد و بعداً استانداردهای ECMA و ISO را نیز دربر گرفت. سی شارپ یکی از ۴۴ زبان برنامه\u200Cنویسی است که توسط زمان اجرای زبان مشترک از چارچوب دات\u200Cنت پشتیبانی می\u200Cشوند و در همه جا به وسیله مایکروسافت ویژوال استودیو شناخته می\u200Cشود.\n" +
                    "\n" +
                    "زبان سی شارپ با قدرت و در عین حال سطح بالایی خود توانسته توجه بسیاری از برنامه نویسان را به خود جلب کند.\n" +
                    "\n" +
                    "این زبان برپایه سادگی، مدرن بودن، همه منظوره و شیءگرا بودن ساخته شد. آندرس هجلزبرگ، طراح زبان برنامه\u200Cنویسی دلفی، سرپرستی تیم طراحان زبان سی شارپ را بر عهده داشت. این زبان دارای دستوری شیءگرا مشابه ++C است و به شدت از زبان\u200Cهای جاوا و دلفینیازمندمدرک تأثیر پذیرفته\u200Cاست. در ابتدا نام این زبان COOL بود که مخفف C like Object Oriented Language بود، هر چند در ژوئیه ۲۰۰۰، زمانی که مایکروسافت پروژه را عمومی اعلام کرد، اسم آن به سی شارپ تغییر پیدا کرد.\n" +
                    "\n" +
                    "آخرین نسخه آن نسخه ۷٫۰ است که در حال توسعه است.[۱]"));
            add(new Document("doc4", "آبجکتیو-سی", "از ویکی\u200Cپدیا، دانشنامهٔ آزاد\n" +
                    "آبجکتیو-سی (به انگلیسی: Objective-C) یک زبان شی\u200Cگرا است که با اضافه کردن مفاهیم ارسال پیام از زبان اسمال\u200Cتاک به زبان سی ایجاد شده. در حال حاضر استفادهٔ اصلی آن در محیط\u200Cهای Mac OS X و iPhone OS است.\n" +
                    "\n" +
                    "برنامه\u200Cهایی از این زبان که از کتابخانه\u200Cهای خاص این محیط\u200Cها استفاده نکرده باشند در همهٔ محیط\u200Cهایی که کامپایلر جی\u200Cسی\u200Cسی (GCC) استفاده می\u200Cشود، قابل استفاده\u200Cاند."));
            add(new Document("doc5", "پایتون (زبان برنامه\u200Cنویسی)", "از ویکی\u200Cپدیا، دانشنامهٔ آزاد\n" +
                    "پایتون (به انگلیسی: Python) یک زبان برنامه\u200Cنویسی همه منظوره،[۳] سطح بالا،[۳] شیءگرا و مفسر است که توسط خودو فان روسوم (به هلندی: Guido van Rossum) در سال ۱۹۹۱ در کشور هلند طراحی شد.\n" +
                    "\n" +
                    "فلسفهٔ ایجاد آن تأکید بر دو هدف اصلی خوانایی بالای برنامه\u200Cهای نوشته شده[۴] و کوتاهی و بازدهی نسبی بالای آن است.[۵] کلمات کلیدی و اصلی این زبان به صورت حداقلی تهیه شده\u200Cاند و در مقابل کتابخانه\u200Cهایی که در اختیار کاربر است بسیار وسیع هستند.\n" +
                    "\n" +
                    "بر خلاف برخی زبان\u200Cهای برنامه\u200Cنویسی رایج دیگر که بلاک\u200Cهای کد در آکولاد تعریف می\u200Cشوند (به\u200Cویژه زبان\u200Cهایی که از گرامر زبان سی پیروی می\u200Cکنند) در زبان پایتون از نویسه فاصله و جلو بردن متن برنامه برای مشخص کردن بلاک\u200Cهای کد استفاده می\u200Cشود. به این معنی که تعدادی یکسان از نویسه فاصله در ابتدای سطرهای هر بلاک قرار می\u200Cگیرند، و این تعداد در بلاک\u200Cهای کد درونی\u200Cتر افزایش می\u200Cیابد. بدین ترتیب بلاک\u200Cهای کد به صورت خودکار ظاهری مرتب دارند.\n" +
                    "\n" +
                    "پایتون مدل\u200Cهای مختلف برنامه\u200Cنویسی (از جمله شیء گرا و برنامه\u200Cنویسی دستوری و تابع محور) را پشتیبانی می\u200Cکند و برای مشخص کردن نوع متغییرها از یک سامانهٔ پویا استفاده می\u200Cکند.\n" +
                    "\n" +
                    "این زبان از زبان\u200Cهای برنامه\u200Cنویسی مفسر بوده و به صورت کامل یک زبان شیءگرا است که در ویژگی\u200Cها با زبانهای تفسیری پرل، روبی، اسکیم، اسمال\u200Cتاک و تی\u200Cسی\u200Cال مشابهت دارد و از مدیریت خودکار حافظه استفاده می\u200Cکند.[۶][۷][۸]\n" +
                    "\n" +
                    "پایتون پروژه\u200Cای آزاد و متن\u200Cباز توسعه\u200Cیافته\u200Cاست و توسط بنیاد نرم\u200Cافزار پایتون مدیریت می\u200Cگردد.[۹]\n" +
                    "\n"));
        }};
    }

    private static void extractTokensFromDocuments(AppDatabase database) {
        List<Document> documents = database.documentDAO().getAllDocuments();
        Set<Word> tokens = new LinkedHashSet<>();
        for (Document document : documents) {
            String text = Normalizer.i().run(document.toString());
            for (String token : text.split(" ")) {
                tokens.add(new Word(Stemmer.i().stem(token)));
            }
        }
        ArrayList<Word> words = new ArrayList<>();
        words.addAll(tokens);
        database.wordDAO().insertWords(words);
    }

    private static void createPositionalIndex(AppDatabase database) {
        List<Document> documents = database.documentDAO().getAllDocuments();
        List<Word> words = database.wordDAO().getAllWords();
        for (Word word : words) {
            int count = 0;
            ArrayList<PositionIndex> positionIndices = new ArrayList<>();
            for (Document document : documents) {
                if (document.toString().contains(word.getBody())) {
                    ArrayList<Integer> indices = new ArrayList<>();
                    for (int i = -1; (i = document.toString().indexOf(word.getBody(), i + 1)) != -1; i++) {
                        indices.add(i);
                        count++;
                    }
                    positionIndices.add(new PositionIndex(document.getId(), indices));
                }
                word.setPositionIndices(positionIndices);
                word.setCount(count);
                database.wordDAO().updateWords(word);
            }
        }
    }

    public abstract DocumentDAO documentDAO();

    public abstract WordDAO wordDAO();
}