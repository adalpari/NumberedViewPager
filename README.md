# NumberedViewPager

A simple ViewPager with a number indicator to know the _current / total_ items on it.

![demo gif](https://raw.githubusercontent.com/adalpari/NumberedViewPager/master/media/demo.gif)

# How to use

## 1.- Add the library into gradle

[![](https://jitpack.io/v/adalpari/NumberedViewPager.svg)](https://jitpack.io/#adalpari/NumberedViewPager)

```
allprojects {
  repositories {
  ...
  maven { url 'https://jitpack.io' }
  }
}
```

```
dependencies {
  implementation 'com.github.adalpari:NumberedViewPager:1.1'
}
```

## 2.- Add the NumberedViewPagerHandler into your layout

```xml
<adalpari.github.com.numberedviewpager.NumberedViewPagerHandler
        android:id="@+id/numbered_view_page_handler"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        custom:nc_color="#494949"
        custom:nc_text_color="#ffffff"/>
```

_custom:nc_color_ is the background color of the counter

_custom:nc_text_color_ is the text color of the counter

Don't forget to add the custom scheem in the top paent view _xmlns:custom="http://schemas.android.com/apk/res-auto"_

## 3.- Get the NumberedViewPagerHandler in your class and add the PagerAdapter

```java
NumberedViewPagerHandler numberedViewPagerHandler = findViewById(R.id.numbered_view_page_handler);
numberedViewPagerHandler.setAdapter(new CustomPagerAdapter(MY_VARIABLES));
```

## 4.- Add onDestoy call

In order to avoid memory leaks, call _numberedViewPagerHandler.onDestroy()_ when your activity is destroyed.

```java
@Override
protected void onDestroy() {
    numberedViewPagerHandler.onDestroy();
    super.onDestroy();
}
```

## Important
You can get the embedded _ViewPager_ from the _NumberedViewPagerHandler_. But, if you want to subscribe to _OnPageChangeListener_, __don't do it directly__, use _NumberedViewPagerHandler.setOnPageChangeListener(...)_ instead.

```java
ViewPager internalViewPager = numberedViewPagerHandler.getViewPager();
        
...

numberedViewPagerHandler.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        // your code
    }

    @Override
    public void onPageSelected(int position) {
        // your code
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        // your code
    }
});
```
