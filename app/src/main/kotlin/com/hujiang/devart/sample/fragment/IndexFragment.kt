package com.hujiang.devart.sample.fragment

import android.content.Intent
import android.os.Bundle
import android.preference.Preference
import android.view.Menu
import com.hujiang.devart.base.BasePreferenceFragment
import com.hujiang.devart.base.common.FragmentStarter
import com.hujiang.devart.base.inner.UIInstance
import com.hujiang.devart.component.mutax.MutaxReceiver
import com.hujiang.devart.component.mutax.OnMutaxMessage
import com.hujiang.devart.sample.*
import com.hujiang.devart.sample.service.DemoService

/**
 * Created by rarnu on 3/25/16.
 */
class IndexFragment : BasePreferenceFragment(), Preference.OnPreferenceClickListener, OnMutaxMessage {


    private var _mutax: MutaxReceiver? = null

    private var _p1Arg1: Preference? = null
    private var _p1Arg2: Preference? = null
    private var _p1Arg3: Preference? = null

    private var _p2Adapter: Preference? = null
    private var _p2Dialog: Preference? = null
    private var _p2Popup: Preference? = null
    private var _p2Mutax: Preference? = null

    private var _p3Slide: Preference? = null
    private var _p3Tab: Preference? = null
    private var _p3Float: Preference? = null

    private var _p4Device: Preference? = null
    private var _p4Download: Preference? = null
    private var _p4File: Preference? = null
    private var _p4Json: Preference? = null
    private var _p4Http: Preference? = null
    private var _p4Image: Preference? = null
    private var _p4Network: Preference? = null
    private var _p4Notification: Preference? = null

    override fun getBarTitle(): Int = R.string.app_name

    override fun getBarTitleWithPath(): Int = R.string.app_name

    override fun getCustomTitle(): String? = null

    override fun initComponents() {

        _mutax = MutaxReceiver(DemoService.DEMO_SERVICE_ACTION, null, null)

        _p1Arg1 = findPreference(getString(R.string.id_item_1_1))
        _p1Arg2 = findPreference(getString(R.string.id_item_1_2))
        _p1Arg3 = findPreference(getString(R.string.id_item_1_3))

        _p2Adapter = findPreference(getString(R.string.id_item_2_1))
        _p2Dialog = findPreference(getString(R.string.id_item_2_2))
        _p2Popup = findPreference(getString(R.string.id_item_2_3))
        _p2Mutax = findPreference(getString(R.string.id_item_2_4))

        _p3Slide = findPreference(getString(R.string.id_item_3_5))
        _p3Tab = findPreference(getString(R.string.id_item_3_7))
        _p3Float = findPreference(getString(R.string.id_item_3_6))

        _p4Device = findPreference(getString(R.string.id_item_4_1))
        _p4Download = findPreference(getString(R.string.id_item_4_2))
        _p4File = findPreference(getString(R.string.id_item_4_3))
        _p4Json = findPreference(getString(R.string.id_item_4_3_1))
        _p4Http = findPreference(getString(R.string.id_item_4_4))
        _p4Image = findPreference(getString(R.string.id_item_4_5))
        _p4Network = findPreference(getString(R.string.id_item_4_6))
        _p4Notification = findPreference(getString(R.string.id_item_4_7))

    }

    override fun initEvents() {

        _mutax?.onReceive = this

        _p1Arg1?.onPreferenceClickListener = this
        _p1Arg2?.onPreferenceClickListener = this
        _p1Arg3?.onPreferenceClickListener = this

        _p2Adapter?.onPreferenceClickListener = this
        _p2Dialog?.onPreferenceClickListener = this
        _p2Popup?.onPreferenceClickListener = this
        _p2Mutax?.onPreferenceClickListener = this

        _p3Slide?.onPreferenceClickListener = this
        _p3Tab?.onPreferenceClickListener = this
        _p3Float?.onPreferenceClickListener = this

        _p4Device?.onPreferenceClickListener = this
        _p4Download?.onPreferenceClickListener = this
        _p4File?.onPreferenceClickListener = this
        _p4Json?.onPreferenceClickListener = this
        _p4Http?.onPreferenceClickListener = this
        _p4Image?.onPreferenceClickListener = this
        _p4Network?.onPreferenceClickListener = this
        _p4Notification?.onPreferenceClickListener = this

    }

    override fun initLogic() {

    }

    override fun getFragmentLayoutResId(): Int = R.xml.main

    override fun getMainActivityName(): String? = MainActivity::class.java.name

    override fun initMenu(menu: Menu?) {

    }

    override fun onGetNewArguments(bn: Bundle?) {
    }

    override fun getFragmentState(): Bundle? = null

    override fun onPreferenceClick(preference: Preference?): Boolean {
        val key = preference!!.key
        when (key) {
            getString(R.string.id_item_1_1),
            getString(R.string.id_item_1_2),
            getString(R.string.id_item_1_3) -> {
                UIInstance.currentFragment = 1
                val bn = Bundle()
                bn.putString("key", preference.key)
                if (!Fragments.argFragment.isAdded) {
                    FragmentStarter.showContent(activity, ArgumentActivity::class.java, Fragments.argFragment)
                }
                Fragments.argFragment.setNewArguments(bn)
            }
            getString(R.string.id_item_2_1) -> {
                UIInstance.currentFragment = 2
                FragmentStarter.showContent(activity, AdapterActivity::class.java, Fragments.adapterFragment)
            }
            getString(R.string.id_item_2_2) -> startActivity(Intent(activity, DialogActivity::class.java))
            getString(R.string.id_item_2_3) -> startActivity(Intent(activity, PopupActivity::class.java))
            getString(R.string.id_item_2_4) -> {
                val inService = Intent(activity, DemoService::class.java)
                inService.putExtra("command", "service")
                inService.putExtra("id", 101)
                inService.putExtra("title", R.string.service_name)
                inService.putExtra("desc", R.string.service_finished)
                inService.putExtra("procId", 201)
                inService.putExtra("procTitle", R.string.service_name)
                inService.putExtra("procDesc", R.string.service_detail)
                activity.startService(inService)
            }
            getString(R.string.id_item_3_5) -> startActivity(Intent(activity, SlideActivity::class.java))
            getString(R.string.id_item_3_6) -> {
                UIInstance.currentFragment = 16
                FragmentStarter.showContent(activity, FloatWindowActivity::class.java, Fragments.floatFragment)
            }
            getString(R.string.id_item_3_7) -> {
                UIInstance.currentFragment = 17
                FragmentStarter.showContent(activity, TabActivity::class.java, Fragments.tabFragment)
            }
            getString(R.string.id_item_4_1) -> {
                UIInstance.currentFragment = 7
                FragmentStarter.showContent(activity, DeviceActivity::class.java, Fragments.deviceFragment)
            }
            getString(R.string.id_item_4_2) -> {
                UIInstance.currentFragment = 8
                FragmentStarter.showContent(activity, DownloadActivity::class.java, Fragments.downloadFragment)
            }
            getString(R.string.id_item_4_3) -> {
                UIInstance.currentFragment = 9
                FragmentStarter.showContent(activity, FileActivity::class.java, Fragments.fileFragment)
            }
            getString(R.string.id_item_4_3_1) -> {
                UIInstance.currentFragment = 21
                FragmentStarter.showContent(activity, JsonActivity::class.java, Fragments.jsonFragment)
            }
            getString(R.string.id_item_4_4) -> {
                UIInstance.currentFragment = 10
                FragmentStarter.showContent(activity, HttpActivity::class.java, Fragments.httpFragment)
            }
            getString(R.string.id_item_4_5) -> {
                UIInstance.currentFragment = 11
                FragmentStarter.showContent(activity, ImageActivity::class.java, Fragments.imageFragment)
            }
            getString(R.string.id_item_4_6) -> {
                UIInstance.currentFragment = 12
                FragmentStarter.showContent(activity, NetworkActivity::class.java, Fragments.networkFragment)
            }
            getString(R.string.id_item_4_7) -> {
                UIInstance.currentFragment = 13
                FragmentStarter.showContent(activity, NotificationActivity::class.java, Fragments.notificationFragment)
            }
        }
        return true
    }

    override fun onResume() {
        super.onResume()
        _mutax?.register(activity)
    }

    override fun onPause() {
        _mutax?.unregister(activity)
        super.onPause()
    }

    override fun onMutaxStateChange(operating: Boolean) {
        if (!operating) {
            val inCleanBackupService = Intent(activity, DemoService::class.java)
            activity.stopService(inCleanBackupService)
        }
        _p2Mutax?.isEnabled = !operating
    }

    override fun onMutaxProgress(name: String, position: Int, total: Int) { }

    override fun onMutaxMessage(operating: Boolean) { }
}