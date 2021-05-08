package com.zaker.android.sapeh.app.main.expandinglibrary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import androidx.viewpager.widget.PagerAdapter;
import com.zaker.android.sapeh.R;
import org.jetbrains.annotations.NotNull;


/**
 * Adapter must be implemented to provide your layouts and data(that implements {@link ECCardData})
 * to cards in {@link ECPagerView}.
 */
public abstract class ECPagerViewAdapter extends PagerAdapter {

    private ECPagerCard activeCard;
    private List<ECCardData> dataset;
    private LayoutInflater inflaterService;

    public ECPagerViewAdapter(Context applicationContext, List<ECCardData> dataset) {
        this.inflaterService = (LayoutInflater) applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.dataset = dataset;
    }

    @NotNull
    @Override
    public Object instantiateItem(@NotNull ViewGroup container, int position) {
        final ECPager pager = (ECPager) container;
        @SuppressLint("InflateParams")
        final ECPagerCard pagerCard = (ECPagerCard) inflaterService.inflate(R.layout.ec_pager_card, null);
        final ECPagerView pagerContainer = (ECPagerView) pager.getParent();

        ECPagerCardContentList ecPagerCardContentList = pagerCard.getEcPagerCardContentList();
        ECPagerCardHead headView = ecPagerCardContentList.getHeadView();

        headView.setHeight(pagerContainer.getCardHeight());

        Integer drawableRes = dataset.get(position).getHeadBackgroundResource();
        if (drawableRes != null) {
            headView.setHeadImageBitmap(BitmapFactory.decodeResource(pagerContainer.getResources(), drawableRes, new BitmapFactoryOptions()));
        }

        instantiateCard(inflaterService, headView, ecPagerCardContentList, dataset.get(position));

        pager.addView(pagerCard, pagerContainer.getCardWidth(), pagerContainer.getCardHeight());
        return pagerCard;
    }


    public abstract void instantiateCard(LayoutInflater inflaterService, ViewGroup head, ListView list, ECCardData data);

    @Override
    public void destroyItem(@NotNull ViewGroup container, int position, @NotNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public void setPrimaryItem(@NotNull ViewGroup container, int position, @NotNull Object object) {
        super.setPrimaryItem(container, position, object);
        activeCard = (ECPagerCard) object;
    }

    public ECPagerCard getActiveCard() {
        return activeCard;
    }

    @Override
    public int getCount() {
        return dataset.size();
    }

    @Override
    public boolean isViewFromObject(@NotNull View view, @NotNull Object object) {
        return (view == object);
    }

    public List<ECCardData> getDataset() {
        return dataset;
    }
}
