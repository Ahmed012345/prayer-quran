package com.zaker.android.sapeh.app.main.activitymain.expandingcards;

import com.zaker.android.sapeh.R;
import com.zaker.android.sapeh.app.main.activitymain.expandingcards.pojo.CardData;
import com.zaker.android.sapeh.app.main.activitymain.expandingcards.pojo.Comment;
import com.zaker.android.sapeh.app.main.expandinglibrary.ECCardData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExampleDataset {

    private List<ECCardData> dataset;

    public ExampleDataset() {
        dataset = new ArrayList<>(35);

        CardData item1 = new CardData();
        item1.setMainBackgroundResource(R.drawable.big1);
        item1.setHeadBackgroundResource(R.drawable.small1);
        item1.setListItems(prepareCommentsArray1());
        dataset.add(item1);

        CardData item2 = new CardData();
        item2.setMainBackgroundResource(R.drawable.big2);
        item2.setHeadBackgroundResource(R.drawable.small2);
        item2.setListItems(prepareCommentsArray2());
        dataset.add(item2);

        CardData item3 = new CardData();
        item3.setMainBackgroundResource(R.drawable.big3);
        item3.setHeadBackgroundResource(R.drawable.small3);
        item3.setListItems(prepareCommentsArray3());
        dataset.add(item3);

        CardData item4 = new CardData();
        item4.setMainBackgroundResource(R.drawable.big4);
        item4.setHeadBackgroundResource(R.drawable.small4);
        item4.setListItems(prepareCommentsArray4());
        dataset.add(item4);

        CardData item5 = new CardData();
        item5.setMainBackgroundResource(R.drawable.big21);
        item5.setHeadBackgroundResource(R.drawable.small21);
        item5.setListItems(prepareCommentsArray21());
        dataset.add(item5);

        CardData item6 = new CardData();
        item6.setMainBackgroundResource(R.drawable.big6);
        item6.setHeadBackgroundResource(R.drawable.small6);
        item6.setListItems(prepareCommentsArray6());
        dataset.add(item6);

        CardData item7 = new CardData();
        item7.setMainBackgroundResource(R.drawable.big7);
        item7.setHeadBackgroundResource(R.drawable.small7);
        item7.setListItems(prepareCommentsArray7());
        dataset.add(item7);

        CardData item8 = new CardData();
        item8.setMainBackgroundResource(R.drawable.big8);
        item8.setHeadBackgroundResource(R.drawable.small8);
        item8.setListItems(prepareCommentsArray8());
        dataset.add(item8);

        CardData item9 = new CardData();
        item9.setMainBackgroundResource(R.drawable.big9);
        item9.setHeadBackgroundResource(R.drawable.small9);
        item9.setListItems(prepareCommentsArray9());
        dataset.add(item9);

        CardData item10 = new CardData();
        item10.setMainBackgroundResource(R.drawable.big10);
        item10.setHeadBackgroundResource(R.drawable.small10);
        item10.setListItems(prepareCommentsArray10());
        dataset.add(item10);

        CardData item11 = new CardData();
        item11.setMainBackgroundResource(R.drawable.big11);
        item11.setHeadBackgroundResource(R.drawable.small11);
        item11.setListItems(prepareCommentsArray11());
        dataset.add(item11);

        CardData item12 = new CardData();
        item12.setMainBackgroundResource(R.drawable.big12);
        item12.setHeadBackgroundResource(R.drawable.small12);
        item12.setListItems(prepareCommentsArray12());
        dataset.add(item12);

        CardData item13 = new CardData();
        item13.setMainBackgroundResource(R.drawable.big13);
        item13.setHeadBackgroundResource(R.drawable.small13);
        item13.setListItems(prepareCommentsArray13());
        dataset.add(item13);

        CardData item14 = new CardData();
        item14.setMainBackgroundResource(R.drawable.big14);
        item14.setHeadBackgroundResource(R.drawable.small14);
        item14.setListItems(prepareCommentsArray14());
        dataset.add(item14);

        CardData item15 = new CardData();
        item15.setMainBackgroundResource(R.drawable.big15);
        item15.setHeadBackgroundResource(R.drawable.small15);
        item15.setListItems(prepareCommentsArray15());
        dataset.add(item15);

        CardData item16 = new CardData();
        item16.setMainBackgroundResource(R.drawable.big16);
        item16.setHeadBackgroundResource(R.drawable.small16);
        item16.setListItems(prepareCommentsArray16());
        dataset.add(item16);

        CardData item17 = new CardData();
        item17.setMainBackgroundResource(R.drawable.big17);
        item17.setHeadBackgroundResource(R.drawable.small17);
        item17.setListItems(prepareCommentsArray17());
        dataset.add(item17);

        CardData item18 = new CardData();
        item18.setMainBackgroundResource(R.drawable.big18);
        item18.setHeadBackgroundResource(R.drawable.small18);
        item18.setListItems(prepareCommentsArray18());
        dataset.add(item18);

        CardData item19 = new CardData();
        item19.setMainBackgroundResource(R.drawable.big19);
        item19.setHeadBackgroundResource(R.drawable.small19);
        item19.setListItems(prepareCommentsArray19());
        dataset.add(item19);

        CardData item20 = new CardData();
        item20.setMainBackgroundResource(R.drawable.big20);
        item20.setHeadBackgroundResource(R.drawable.small20);
        item20.setListItems(prepareCommentsArray20());
        dataset.add(item20);

        CardData item21 = new CardData();
        item21.setMainBackgroundResource(R.drawable.big5);
        item21.setHeadBackgroundResource(R.drawable.small5);
        item21.setListItems(prepareCommentsArray5());
        dataset.add(item21);

        CardData item22 = new CardData();
        item22.setMainBackgroundResource(R.drawable.big22);
        item22.setHeadBackgroundResource(R.drawable.small22);
        item22.setListItems(prepareCommentsArray22());
        dataset.add(item22);

        CardData item23 = new CardData();
        item23.setMainBackgroundResource(R.drawable.big23);
        item23.setHeadBackgroundResource(R.drawable.small23);
        item23.setListItems(prepareCommentsArray23());
        dataset.add(item23);

        CardData item24 = new CardData();
        item24.setMainBackgroundResource(R.drawable.big24);
        item24.setHeadBackgroundResource(R.drawable.small24);
        item24.setListItems(prepareCommentsArray24());
        dataset.add(item24);

        CardData item25 = new CardData();
        item25.setMainBackgroundResource(R.drawable.big25);
        item25.setHeadBackgroundResource(R.drawable.small25);
        item25.setListItems(prepareCommentsArray25());
        dataset.add(item25);

        CardData item26 = new CardData();
        item26.setMainBackgroundResource(R.drawable.big26);
        item26.setHeadBackgroundResource(R.drawable.small26);
        item26.setListItems(prepareCommentsArray26());
        dataset.add(item26);

        CardData item27 = new CardData();
        item27.setMainBackgroundResource(R.drawable.big27);
        item27.setHeadBackgroundResource(R.drawable.small27);
        item27.setListItems(prepareCommentsArray27());
        dataset.add(item27);

        CardData item28 = new CardData();
        item28.setMainBackgroundResource(R.drawable.big28);
        item28.setHeadBackgroundResource(R.drawable.small28);
        item28.setListItems(prepareCommentsArray28());
        dataset.add(item28);

        CardData item29 = new CardData();
        item29.setMainBackgroundResource(R.drawable.big29);
        item29.setHeadBackgroundResource(R.drawable.small29);
        item29.setListItems(prepareCommentsArray29());
        dataset.add(item29);

        CardData item30 = new CardData();
        item30.setMainBackgroundResource(R.drawable.big30);
        item30.setHeadBackgroundResource(R.drawable.small30);
        item30.setListItems(prepareCommentsArray30());
        dataset.add(item30);

        CardData item31 = new CardData();
        item31.setMainBackgroundResource(R.drawable.big31);
        item31.setHeadBackgroundResource(R.drawable.small31);
        item31.setListItems(prepareCommentsArray31());
        dataset.add(item31);

        CardData item32 = new CardData();
        item32.setMainBackgroundResource(R.drawable.big32);
        item32.setHeadBackgroundResource(R.drawable.small32);
        item32.setListItems(prepareCommentsArray32());
        dataset.add(item32);

        CardData item33 = new CardData();
        item33.setMainBackgroundResource(R.drawable.big33);
        item33.setHeadBackgroundResource(R.drawable.small33);
        item33.setListItems(prepareCommentsArray33());
        dataset.add(item33);

        CardData item34 = new CardData();
        item34.setMainBackgroundResource(R.drawable.big34);
        item34.setHeadBackgroundResource(R.drawable.small34);
        item34.setListItems(prepareCommentsArray34());
        dataset.add(item34);

        CardData item35 = new CardData();
        item35.setMainBackgroundResource(R.drawable.big35);
        item35.setHeadBackgroundResource(R.drawable.small35);
        item35.setListItems(prepareCommentsArray35());
        dataset.add(item35);

    }

    public List<ECCardData> getDataset() {
        return dataset;
    }

    private List<Comment> prepareCommentsArray1() {
        List<Comment> comments = new ArrayList<>(Collections.singletonList(
                new Comment(R.string.sourah9, R.string.aya33_1, R.string.card_aya1, R.drawable.card_bottom1)));
        return comments;
    }
    private List<Comment> prepareCommentsArray2() {
        List<Comment> comments = new ArrayList<>(Collections.singletonList(
                new Comment(R.string.sourah6, R.string.aya114_2, R.string.card_aya2, R.drawable.card_bottom2)));
        return comments;
    }
    private List<Comment> prepareCommentsArray3() {
        List<Comment> comments = new ArrayList<>(Collections.singletonList(
                new Comment(R.string.sourah24, R.string.aya41_3, R.string.card_aya3, R.drawable.card_bottom3)));
        return comments;
    }
    private List<Comment> prepareCommentsArray4() {
        List<Comment> comments = new ArrayList<>(Collections.singletonList(
                new Comment(R.string.sourah2, R.string.aya28_4, R.string.card_aya4, R.drawable.card_bottom4)));
        return comments;
    }
    private List<Comment> prepareCommentsArray5() {
        List<Comment> comments = new ArrayList<>(Collections.singletonList(
                new Comment(R.string.sourah2, R.string.aya163_5, R.string.card_aya5, R.drawable.card_bottom5)));
        return comments;
    }
    private List<Comment> prepareCommentsArray6() {
        List<Comment> comments = new ArrayList<>(Collections.singletonList(
                new Comment(R.string.sourah6, R.string.aya71_6, R.string.card_aya6, R.drawable.card_bottom6)));
        return comments;
    }
    private List<Comment> prepareCommentsArray7() {
        List<Comment> comments = new ArrayList<>(Collections.singletonList(
                new Comment(R.string.sourah6, R.string.aya95_7, R.string.card_aya7, R.drawable.card_bottom7)));
        return comments;
    }
    private List<Comment> prepareCommentsArray8() {
        List<Comment> comments = new ArrayList<>(Collections.singletonList(
                new Comment(R.string.sourah10, R.string.aya1_8, R.string.card_aya8, R.drawable.card_bottom8)));
        return comments;
    }
    private List<Comment> prepareCommentsArray9() {
        List<Comment> comments = new ArrayList<>(Collections.singletonList(
                new Comment(R.string.sourah11, R.string.aya6_9, R.string.card_aya9, R.drawable.card_bottom9)));
        return comments;
    }
    private List<Comment> prepareCommentsArray10() {
        List<Comment> comments = new ArrayList<>(Collections.singletonList(
                new Comment(R.string.sourah13, R.string.aya12_10, R.string.card_aya10, R.drawable.card_bottom10)));
        return comments;
    }
    private List<Comment> prepareCommentsArray11() {
        List<Comment> comments = new ArrayList<>(Collections.singletonList(
                new Comment(R.string.sourah16, R.string.aya10_11, R.string.card_aya11, R.drawable.card_bottom11)));
        return comments;
    }
    private List<Comment> prepareCommentsArray12() {
        List<Comment> comments = new ArrayList<>(Collections.singletonList(
                new Comment(R.string.sourah57, R.string.aya1_12, R.string.card_aya12, R.drawable.card_bottom12)));
        return comments;
    }
    private List<Comment> prepareCommentsArray13() {
        List<Comment> comments = new ArrayList<>(Collections.singletonList(
                new Comment(R.string.surah7, R.string.aya52_13, R.string.card_aya13, R.drawable.card_bottom13)));
        return comments;
    }
    private List<Comment> prepareCommentsArray14() {
        List<Comment> comments = new ArrayList<>(Collections.singletonList(
                new Comment(R.string.sourah13, R.string.aya1_14, R.string.card_aya14, R.drawable.card_bottom14)));
        return comments;
    }
    private List<Comment> prepareCommentsArray15() {
        List<Comment> comments = new ArrayList<>(Collections.singletonList(
                new Comment(R.string.sourah14, R.string.aya32_15, R.string.card_aya15, R.drawable.card_bottom15)));
        return comments;
    }
    private List<Comment> prepareCommentsArray16() {
        List<Comment> comments = new ArrayList<>(Collections.singletonList(
                new Comment(R.string.sourah35, R.string.aya9_16, R.string.card_aya16, R.drawable.card_bottom16)));
        return comments;
    }
    private List<Comment> prepareCommentsArray17() {
        List<Comment> comments = new ArrayList<>(Collections.singletonList(
                new Comment(R.string.sourah39, R.string.aya1_17, R.string.card_aya17, R.drawable.card_bottom17)));
        return comments;
    }
    private List<Comment> prepareCommentsArray18() {
        List<Comment> comments = new ArrayList<>(Collections.singletonList(
                new Comment(R.string.sourah45, R.string.aya1_18, R.string.card_aya18, R.drawable.card_bottom18)));
        return comments;
    }
    private List<Comment> prepareCommentsArray19() {
        List<Comment> comments = new ArrayList<>(Collections.singletonList(
                new Comment(R.string.sourah25, R.string.aya53_19, R.string.card_aya19, R.drawable.card_bottom19)));
        return comments;
    }
    private List<Comment> prepareCommentsArray20() {
        List<Comment> comments = new ArrayList<>(Collections.singletonList(
                new Comment(R.string.sourah35, R.string.aya39_20, R.string.card_aya20, R.drawable.card_bottom20)));
        return comments;
    }
    private List<Comment> prepareCommentsArray21() {
        List<Comment> comments = new ArrayList<>(Collections.singletonList(
                new Comment(R.string.sourah22, R.string.aya60_21, R.string.card_aya21, R.drawable.card_bottom21)));
        return comments;
    }
    private List<Comment> prepareCommentsArray22() {
        List<Comment> comments = new ArrayList<>(Collections.singletonList(
                new Comment(R.string.sourah6, R.string.aya1_22, R.string.card_aya22, R.drawable.card_bottom22)));
        return comments;
    }
    private List<Comment> prepareCommentsArray23() {
        List<Comment> comments = new ArrayList<>(Collections.singletonList(
                new Comment(R.string.sourah6, R.string.aya59_23, R.string.card_aya23, R.drawable.card_bottom23)));
        return comments;
    }
    private List<Comment> prepareCommentsArray24() {
        List<Comment> comments = new ArrayList<>(Collections.singletonList(
                new Comment(R.string.sourah6, R.string.aya164_24, R.string.card_aya24, R.drawable.card_bottom24)));
        return comments;
    }
    private List<Comment> prepareCommentsArray25() {
        List<Comment> comments = new ArrayList<>(Collections.singletonList(
                new Comment(R.string.surah7, R.string.aya187_25, R.string.card_aya25, R.drawable.card_bottom25)));
        return comments;
    }
    private List<Comment> prepareCommentsArray26() {
        List<Comment> comments = new ArrayList<>(Collections.singletonList(
                new Comment(R.string.sourah10, R.string.aya66_26, R.string.card_aya26, R.drawable.card_bottom26)));
        return comments;
    }
    private List<Comment> prepareCommentsArray27() {
        List<Comment> comments = new ArrayList<>(Collections.singletonList(
                new Comment(R.string.sourah23, R.string.aya78_27, R.string.card_aya27, R.drawable.card_bottom27)));
        return comments;
    }
    private List<Comment> prepareCommentsArray28() {
        List<Comment> comments = new ArrayList<>(Collections.singletonList(
                new Comment(R.string.sourah25, R.string.aya43_28, R.string.card_aya28, R.drawable.card_bottom28)));
        return comments;
    }
    private List<Comment> prepareCommentsArray29() {
        List<Comment> comments = new ArrayList<>(Collections.singletonList(
                new Comment(R.string.sourah25, R.string.aya58_29, R.string.card_aya29, R.drawable.card_bottom29)));
        return comments;
    }
    private List<Comment> prepareCommentsArray30() {
        List<Comment> comments = new ArrayList<>(Collections.singletonList(
                new Comment(R.string.sourah40, R.string.aya64_30, R.string.card_aya30, R.drawable.card_bottom30)));
        return comments;
    }
    private List<Comment> prepareCommentsArray31() {
        List<Comment> comments = new ArrayList<>(Collections.singletonList(
                new Comment(R.string.sourah16, R.string.aya64_31, R.string.card_aya31, R.drawable.card_bottom31)));
        return comments;
    }
    private List<Comment> prepareCommentsArray32() {
        List<Comment> comments = new ArrayList<>(Collections.singletonList(
                new Comment(R.string.sourah67, R.string.aya1_32, R.string.card_aya12, R.drawable.card_bottom32)));
        return comments;
    }
    private List<Comment> prepareCommentsArray33() {
        List<Comment> comments = new ArrayList<>(Collections.singletonList(
                new Comment(R.string.sourah71, R.string.aya5_33, R.string.card_aya33, R.drawable.card_bottom33)));
        return comments;
    }
    private List<Comment> prepareCommentsArray34() {
        List<Comment> comments = new ArrayList<>(Collections.singletonList(
                new Comment(R.string.sourah88, R.string.aya17_34, R.string.card_aya34, R.drawable.card_bottom34)));
        return comments;
    }
    private List<Comment> prepareCommentsArray35() {
        List<Comment> comments = new ArrayList<>(Collections.singletonList(
                new Comment(R.string.sourah30, R.string.arom_k1, R.string.card_aya35, R.drawable.card_bottom35)));
        return comments;
    }
}
