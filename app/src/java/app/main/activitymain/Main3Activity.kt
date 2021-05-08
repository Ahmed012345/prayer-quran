package com.zaker.android.sapeh.app.main.activitymain

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewTreeObserver
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import butterknife.BindView
import butterknife.ButterKnife
import com.akexorcist.localizationactivity.ui.LocalizationActivity
import com.facebook.rebound.Spring
import com.facebook.rebound.SpringConfig
import com.facebook.rebound.SpringListener
import com.facebook.rebound.SpringSystem
import com.mursaat.extendedtextview.AnimatedGradientTextView
import com.realpacific.clickshrinkeffect.applyClickShrink
import com.zaker.android.sapeh.R
import com.zaker.android.sapeh.app.main.Bungee
import com.zaker.android.sapeh.app.main.Rumble
import com.zaker.android.sapeh.app.main.bubble.BubbleLayout
import com.zaker.android.sapeh.app.main.foltingtoast.FloatingToast
import com.zaker.android.sapeh.app.main.guillotine.animation.GuillotineAnimation
import com.zaker.android.sapeh.app.main.guillotine.interfaces.GuillotineListener
import com.zaker.android.sapeh.app.main.steppertouch.OnStepCallback
import com.zaker.android.sapeh.app.main.steppertouch.StepperTouch


class Main3Activity : LocalizationActivity(), View.OnTouchListener, SpringListener {

    private val RIPPLE_DURATION: Long = 250

    private val TENSION = 500.0
    private val DAMPER = 10.0 //friction


    private lateinit var mImageToAnimate: ImageView
    private lateinit var mSpringSystem: SpringSystem
    private lateinit var mSpring: Spring


    var context: Context = this

    private companion object Params {

        const val STIFFNESS = SpringForce.STIFFNESS_MEDIUM

        const val DAMPING_RATIO = SpringForce.DAMPING_RATIO_HIGH_BOUNCY
    }


    lateinit var xAnimation: SpringAnimation

    lateinit var yAnimation: SpringAnimation

    lateinit var movingView: ImageView

    @BindView(R.id.toolbar) internal lateinit var toolbar: Toolbar

    @BindView(R.id.root3) internal lateinit var root: FrameLayout

    @BindView(R.id.content_hamburger3) internal lateinit var contentHamburger: View

    private lateinit var mGuillotineAnimation: GuillotineAnimation

    internal var isOpened = true

    private var doubleBackToExitPressedOnce = false

    internal lateinit var backb: MediaPlayer
    internal lateinit var openk: MediaPlayer
    internal lateinit var closek: MediaPlayer
    internal lateinit var sapih: MediaPlayer

    internal lateinit var three3: BubbleLayout
    internal lateinit var three: BubbleLayout

    internal lateinit var menu3:AnimatedGradientTextView
    internal lateinit var editText:EditText

    internal lateinit var kal:BubbleLayout
    internal lateinit var kal1:BubbleLayout
    internal lateinit var kal2:BubbleLayout
    internal lateinit var kal3:BubbleLayout
    internal lateinit var kal4:BubbleLayout
    internal lateinit var kal5:BubbleLayout
    internal lateinit var kal6:BubbleLayout
    internal lateinit var kal7:BubbleLayout
    internal lateinit var kal8:BubbleLayout
    internal lateinit var kal9:BubbleLayout


    @SuppressLint("InflateParams", "ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Rumble.init(applicationContext)
        setContentView(R.layout.activity_main3)
        ButterKnife.bind(this)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)


        setSupportActionBar(toolbar)
        supportActionBar!!.title = null

        val guillotineMenu = LayoutInflater.from(this).inflate(R.layout.guillotine3, null)
        root.addView(guillotineMenu)

        three = findViewById(R.id.three)
        three.setOnClickListener{
            mGuillotineAnimation.close()
            FloatingToast.makeToast(
                this@Main3Activity,
                resources.getString(R.string.activity),
                FloatingToast.LENGTH_TOO_LONG
            )
                .setGravity(FloatingToast.GRAVITY_MID_BOTTOM)
                .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
                .setTextSizeInDp(resources.getDimensionPixelSize(R.dimen._5mdp).toFloat())
                .setTextTypeface(Typeface.createFromAsset(assets, "fonts/toto.ttf"))
                .setBackgroundBlur(true)
                .setFloatDistance(FloatingToast.DISTANCE_LONG.toFloat())
                .setTextColor(Color.parseColor("#dc8744"))
                .setShadowLayer(5f, 1f, 1f, Color.parseColor("#000000"))
                .show()    //Show toast at the specified fixed position
        }
        three3 = findViewById(R.id.three3)
        three3.setOnClickListener {
            mGuillotineAnimation.close()
            FloatingToast.makeToast(
                this@Main3Activity,
                resources.getString(R.string.activity),
                FloatingToast.LENGTH_TOO_LONG
            )
                .setGravity(FloatingToast.GRAVITY_BOTTOM)
                .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
                .setTextSizeInDp(resources.getDimensionPixelSize(R.dimen._5mdp).toFloat())
                .setTextTypeface(Typeface.createFromAsset(assets, "fonts/toto.ttf"))
                .setBackgroundBlur(true)
                .setFloatDistance(FloatingToast.DISTANCE_LONG.toFloat())
                .setTextColor(Color.parseColor("#dc8744"))
                .setShadowLayer(5f, 1f, 1f, Color.parseColor("#000000"))
                .show()    //Show toast at the specified fixed position
        }

        openk = MediaPlayer.create(this, R.raw.openk)
        closek = MediaPlayer.create(this, R.raw.closek)
        mGuillotineAnimation = GuillotineAnimation.GuillotineBuilder(
            guillotineMenu,
            guillotineMenu.findViewById(R.id.guillotine_hamburger3), contentHamburger
        )
            .setActionBarViewForAnimation(toolbar)
            .setStartDelay(RIPPLE_DURATION)
            .setClosedOnStart(true)
            .setGuillotineListener(object : GuillotineListener {
                override fun onGuillotineOpened() {
                    openk.start()
                    isOpened = true
                }

                override fun onGuillotineClosed() {
                    closek.start()
                    isOpened = false
                }
            })
            .build()


        mImageToAnimate = findViewById(R.id.imageView2)
        mImageToAnimate.setOnTouchListener(this)

        mSpringSystem = SpringSystem.create()

        mSpring = mSpringSystem.createSpring()
        mSpring.addListener(this)
        val config = SpringConfig(TENSION, DAMPER)
        mSpring.springConfig = config



        sapih = MediaPlayer.create(this, R.raw.sipha)
        editText = findViewById(R.id.editText)


        val bottemStepperTouch = findViewById<StepperTouch>(R.id.bottomStepperTouch)
        bottemStepperTouch.stepper.setMin(0)
        bottemStepperTouch.stepper.setMax(19000)
        bottemStepperTouch.enableSideTap(true)
        bottemStepperTouch.stepper.addStepCallback(object : OnStepCallback {
            override fun onStep(value: Int, positive: Boolean) {
                Rumble.once(50)
                FloatingToast.makeToast(
                    this@Main3Activity,
                    editText.text.toString(),
                    FloatingToast.LENGTH_TOO_LONG
                )
                    .setGravity(FloatingToast.GRAVITY_CENTER)
                    .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
                    .setTextSizeInDp(resources.getDimensionPixelSize(R.dimen._5mdp).toFloat())
                    .setTextTypeface(Typeface.createFromAsset(assets, "fonts/toto.ttf"))
                    .setBackgroundBlur(true)
                    .setFloatDistance(FloatingToast.DISTANCE_LONG.toFloat())
                    .setTextColor(Color.parseColor("#dc8744"))
                    .setShadowLayer(5f, 1f, 1f, Color.parseColor("#000000"))
                    .show()    //Show toast at the specified fixed position
                sapih.start()
            }
        })



        movingView = findViewById(R.id.imageView3)
        movingView.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {

            override fun onGlobalLayout() {
                xAnimation = createSpringAnimation(
                    movingView, SpringAnimation.X, movingView.x,
                    STIFFNESS,
                    DAMPING_RATIO
                )
                yAnimation = createSpringAnimation(
                    movingView, SpringAnimation.Y, movingView.y,
                    STIFFNESS,
                    DAMPING_RATIO
                )
                movingView.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })
        var dX = 0f
        var dY = 0f
        movingView.setOnTouchListener { view, event ->
            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    // capture the difference between view's top left corner and touch point
                    dX = view.x - event.rawX
                    dY = view.y - event.rawY

                    // cancel animations so we can grab the view during previous animation
                    xAnimation.cancel()
                    yAnimation.cancel()
                }
                MotionEvent.ACTION_MOVE -> {
                    //  a different approach would be to change the view's LayoutParams.
                    movingView.animate()
                        .x(event.rawX + dX)
                        .y(event.rawY + dY)
                        .setDuration(0)
                        .start()
                }
                MotionEvent.ACTION_UP -> {
                    xAnimation.start()
                    yAnimation.start()
                }
            }
            true
        }
        backb = MediaPlayer.create(this, R.raw.backb)

        kal = findViewById(R.id.kal)
        kal1 = findViewById(R.id.kal1)
        kal2 = findViewById(R.id.kal2)
        kal3 = findViewById(R.id.kal3)
        kal4 = findViewById(R.id.kal4)
        kal5 = findViewById(R.id.kal5)
        kal6 = findViewById(R.id.kal6)
        kal7 = findViewById(R.id.kal7)
        kal8 = findViewById(R.id.kal8)
        kal9 = findViewById(R.id.kal9)

        kal.applyClickShrink()
        kal1.applyClickShrink()
        kal2.applyClickShrink()
        kal3.applyClickShrink()
        kal4.applyClickShrink()
        kal5.applyClickShrink()
        kal6.applyClickShrink()
        kal7.applyClickShrink()
        kal8.applyClickShrink()
        kal9.applyClickShrink()

        menu3 = findViewById(R.id.menu3)
        menu3.applyClickShrink()

        if (context.getString(R.string.lang) == "ar") {

            menu3.typeface = FontManger.yad
            editText.typeface = FontManger.yad
        }
        if (context.getString(R.string.lang) == "zh") {

            menu3.typeface = FontManger.asian
            editText.typeface = FontManger.asian
        }
        if (context.getString(R.string.lang) == "cs") {

            menu3.typeface = FontManger.english
            editText.typeface = FontManger.english
        }
        if (context.getString(R.string.lang) == "nl") {

            menu3.typeface = FontManger.english
            editText.typeface = FontManger.english
        }
        if (context.getString(R.string.lang) == "en") {

            menu3.typeface = FontManger.english
            editText.typeface = FontManger.english
        }
        if (context.getString(R.string.lang) == "fr") {

            menu3.typeface = FontManger.english
            editText.typeface = FontManger.english
        }
        if (context.getString(R.string.lang) == "de") {

            menu3.typeface = FontManger.english
            editText.typeface = FontManger.english
        }
        if (context.getString(R.string.lang) == "in") {

            menu3.typeface = FontManger.english
            editText.typeface = FontManger.english
        }
        if (context.getString(R.string.lang) == "it") {

            menu3.typeface = FontManger.english
            editText.typeface = FontManger.english
        }
        if (context.getString(R.string.lang) == "ja") {

            menu3.typeface = FontManger.asian
            editText.typeface = FontManger.asian
        }
        if (context.getString(R.string.lang) == "ko") {

            menu3.typeface = FontManger.asian
            editText.typeface = FontManger.asian
        }
        if (context.getString(R.string.lang) == "fa") {

            menu3.typeface = FontManger.yad
            editText.typeface = FontManger.yad
        }
        if (context.getString(R.string.lang) == "pl") {

            menu3.typeface = FontManger.english
            editText.typeface = FontManger.english
        }
        if (context.getString(R.string.lang) == "pt") {

            menu3.typeface = FontManger.english
            editText.typeface = FontManger.english
        }
        if (context.getString(R.string.lang) == "ro") {

            menu3.typeface = FontManger.english
            editText.typeface = FontManger.english
        }
        if (context.getString(R.string.lang) == "ru") {

            menu3.typeface = FontManger.russi
            editText.typeface = FontManger.russi
        }
        if (context.getString(R.string.lang) == "es") {

            menu3.typeface = FontManger.english
            editText.typeface = FontManger.english
        }
        if (context.getString(R.string.lang) == "th") {

            menu3.typeface = FontManger.english
            editText.typeface = FontManger.english
        }
        if (context.getString(R.string.lang) == "tr") {

            menu3.typeface = FontManger.english
            editText.typeface = FontManger.english
        }
        if (context.getString(R.string.lang) == "ms") {

            menu3.typeface = FontManger.english
            editText.typeface = FontManger.english
        }
        if (context.getString(R.string.lang) == "hi") {

            menu3.typeface = FontManger.english
            editText.typeface = FontManger.english
        }
        if (context.getString(R.string.lang) == "bn") {

            menu3.typeface = FontManger.english
            editText.typeface = FontManger.english
        }
        if (context.getString(R.string.lang) == "ur") {

            menu3.typeface = FontManger.yad
            editText.typeface = FontManger.yad
        }
        if (context.getString(R.string.lang) == "sv") {

            menu3.typeface = FontManger.english
            editText.typeface = FontManger.english
        }
        if (context.getString(R.string.lang) == "sq") {

            menu3.typeface = FontManger.english
            editText.typeface = FontManger.english
        }
        if (context.getString(R.string.lang) == "az") {

            menu3.typeface = FontManger.english
            editText.typeface = FontManger.english
        }
        if (context.getString(R.string.lang) == "bs") {

            menu3.typeface = FontManger.english
            editText.typeface = FontManger.english
        }
        if (context.getString(R.string.lang) == "bg") {

            menu3.typeface = FontManger.russi
            editText.typeface = FontManger.russi
        }
        if (context.getString(R.string.lang) == "ha") {

            menu3.typeface = FontManger.english
            editText.typeface = FontManger.english
        }
        if (context.getString(R.string.lang) == "no") {

            menu3.typeface = FontManger.english
            editText.typeface = FontManger.english
        }
        if (context.getString(R.string.lang) == "so") {

            menu3.typeface = FontManger.english
            editText.typeface = FontManger.english
        }
        if (context.getString(R.string.lang) == "sw") {

            menu3.typeface = FontManger.english
            editText.typeface = FontManger.english
        }
    }

    private fun createSpringAnimation(
        View: View,
        property: DynamicAnimation.ViewProperty?,
        finalPosition: Float,
        stiffness: Float,
        dampingRATIO: Float
    ): SpringAnimation {
        val animation = SpringAnimation(View, property)
        val spring = SpringForce(finalPosition)
        spring.stiffness = stiffness
        spring.dampingRatio = dampingRATIO
        animation.spring = spring
        return animation
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View, event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                mSpring.endValue = 1.0
                return true
            }
            MotionEvent.ACTION_UP -> {
                mSpring.endValue = 0.0
                return true
            }
        }
        return false
    }

    override fun onSpringUpdate(spring: Spring) {
        val value = spring.currentValue.toFloat()
        val scale = 1f - value * 0.5f
        mImageToAnimate.scaleX = scale
        mImageToAnimate.scaleY = scale
    }


    override fun onSpringAtRest(spring: Spring) = Unit
    override fun onSpringActivate(spring: Spring) = Unit
    override fun onSpringEndStateChange(spring: Spring) = Unit

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            backb.start()
            return
        }


        this.doubleBackToExitPressedOnce = true
        FloatingToast.makeToast(
            this@Main3Activity,
            resources.getString(R.string.finalback),
            FloatingToast.LENGTH_TOO_LONG
        )
            .setGravity(FloatingToast.GRAVITY_CENTER)
            .setFadeOutDuration(FloatingToast.FADE_DURATION_TOO_LONG)
            .setTextSizeInDp(resources.getDimensionPixelSize(R.dimen._5mdp).toFloat())
            .setBackgroundBlur(true)
            .setFloatDistance(FloatingToast.DISTANCE_LONG.toFloat())
            .setTextTypeface(Typeface.createFromAsset(assets, "fonts/toto.ttf"))
            .setTextColor(Color.parseColor("#ffffff"))
            .setShadowLayer(5f, 1f, 1f, Color.parseColor("#000000"))
            .show()    //Show toast at the specified fixed position
        Handler().postDelayed({
            doubleBackToExitPressedOnce = false
            Bungee.shrink(context)

        }, 2000)
    }



}

