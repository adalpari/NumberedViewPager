# NumberedViewPager

A simple ViewPager with an indicator to know the _current / total_ items on it.

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
  implementation 'com.github.adalpari:NumberedViewPager:1.0'
}
```

## 2.- Add the NumberedViewPagerHandler into your layout

```xml
<adalpari.github.com.numberedviewpager.NumberedViewPagerHandler
        android:id="@+id/numbered_view_page_handler"
        android:layout_width="match_parent"
        android:layout_height="200dp"
        android:layout_marginTop="50dp"
        custom:nc_color="#494949"
        custom:nc_text_color="#ffffff"/>
```

_custom:nc_color_ is the background color of the counter

_custom:nc_text_color_ is the text color of the counter

Dont forget to add the custom scheem in the top paent view _xmlns:custom="http://schemas.android.com/apk/res-auto"_

## 3.- Get the NumberedViewPagerHandler in your class and add the PagerAdapter

```java
NumberedViewPagerHandler numberedViewPagerHandler = findViewById(R.id.numbered_view_page_handler);
numberedViewPagerHandler.setAdapter(new CustomPagerAdapter(MY_VARIABLES));
```

