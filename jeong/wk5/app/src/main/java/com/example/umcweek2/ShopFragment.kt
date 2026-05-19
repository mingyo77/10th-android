package com.example.umcweek2

import android.os.Bundle
import android.widget.TextView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.umcweek2.databinding.FragmentShopBinding
import com.google.android.material.tabs.TabLayout

class ShopFragment : Fragment() {

    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTabs()

        ViewCompat.setOnApplyWindowInsetsListener(binding.shopRoot) { v, insets ->
            val topInset = insets.getInsets(WindowInsetsCompat.Type.systemBars()).top
            v.setPadding(
                v.paddingLeft,
                topInset,
                v.paddingRight,
                v.paddingBottom
            )
            insets
        }
    }

    private fun setupTabs() {
        val tabTitles = listOf("전체", "Top & T-shirts", "sale")
        val tabWidthsDp = listOf(78, 153, 77)

        tabTitles.forEachIndexed { index, title ->
            val tab = binding.tabLayoutShop.newTab()
            val tabView = LayoutInflater.from(requireContext())
                .inflate(R.layout.item_shop_tab, binding.tabLayoutShop, false) as TextView
            tabView.text = title
            tabView.layoutParams = ViewGroup.LayoutParams(
                dpToPx(tabWidthsDp[index]),
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            tab.customView = tabView
            binding.tabLayoutShop.addTab(tab)
        }
        updateTabTextStyles(binding.tabLayoutShop.getTabAt(0), isSelected = true)

        if (childFragmentManager.findFragmentById(R.id.shopTabContainer) == null) {
            replaceTabFragment(ShopAllFragment())
        }

        binding.tabLayoutShop.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                updateTabTextStyles(tab, isSelected = true)
                when (tab.position) {
                    0 -> replaceTabFragment(ShopAllFragment())
                    1 -> replaceTabFragment(ShopPlaceholderFragment.newInstance("Top & T-shirts"))
                    2 -> replaceTabFragment(ShopPlaceholderFragment.newInstance("sale"))
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                updateTabTextStyles(tab, isSelected = false)
            }
            override fun onTabReselected(tab: TabLayout.Tab) = Unit
        })
    }

    private fun updateTabTextStyles(tab: TabLayout.Tab?, isSelected: Boolean) {
        val textView = tab?.customView as? TextView ?: return
        textView.setTextColor(
            resources.getColor(
                if (isSelected) R.color.black else R.color.gray_500,
                null
            )
        )
    }

    private fun dpToPx(dp: Int): Int {
        return (dp * resources.displayMetrics.density).toInt()
    }

    private fun replaceTabFragment(fragment: Fragment) {
        childFragmentManager.beginTransaction()
            .replace(R.id.shopTabContainer, fragment)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}