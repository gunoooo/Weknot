package com.example.weknot_android.view.fragment

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.example.weknot_android.R
import com.example.weknot_android.base.BaseFragment
import com.example.weknot_android.databinding.FeedFragmentBinding
import com.example.weknot_android.model.entity.feed.Feed
import com.example.weknot_android.widget.recyclerview.adapter.FeedAdapter

class FeedFragment : BaseFragment<FeedFragmentBinding>() {
    private lateinit var animAddShow : Animation
    private lateinit var animAddHide : Animation
    private var feedAdapter: FeedAdapter? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()

        var feeds: ArrayList<Feed> = ArrayList()
        feeds.add(Feed("aa","https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/67403926_132863061286633_2428159034944126976_n.jpg?_nc_cat=111&_nc_eui2=AeEBBhNE_x8QbW2VUZ44ux6LJpQKHjEs3OFl2jHv6NuUeB8E7WlH-TFTP0r69Y136W7aIWS_xMNg4RJD4_tdOZBBXgFWECi85CYsr5JLAZTh_Q&_nc_oc=AQnq-O1fFeIUpXbLH0zOByqesQqQ4e1cmcKsxsWKWodyBvlyoGBuTt4Fh2VuEwjjCsA&_nc_ht=scontent-icn1-1.xx&oh=05ceb3efda3ccc6ce6186bd734e12d61&oe=5E156726","박건우",312321,"aa","https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/69202764_473249329945552_962513982193664000_n.jpg?_nc_cat=110&_nc_eui2=AeH7a1b9giy40MxTdssYQk4MseQOim8RLW_fqffye6br8dUX2ZNUC6_AgbKZko07hhn1_xEMG2Uk2dFsRkcRDTKz4xCfkjuncUFRdqBlRzXsLw&_nc_oc=AQmU1Q4Y5UF1mkbl6IvjqPeoEGwM95ij3HdoGZB8yisg74T5rgJbPx8rzAPqZ3xEjo0&_nc_ht=scontent-icn1-1.xx&oh=3a7b0279cef60956ecd0a1a5b7d88f08&oe=5DCAABEA","aa",1,false))
        feeds.add(Feed("aa","https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/67403926_132863061286633_2428159034944126976_n.jpg?_nc_cat=111&_nc_eui2=AeEBBhNE_x8QbW2VUZ44ux6LJpQKHjEs3OFl2jHv6NuUeB8E7WlH-TFTP0r69Y136W7aIWS_xMNg4RJD4_tdOZBBXgFWECi85CYsr5JLAZTh_Q&_nc_oc=AQnq-O1fFeIUpXbLH0zOByqesQqQ4e1cmcKsxsWKWodyBvlyoGBuTt4Fh2VuEwjjCsA&_nc_ht=scontent-icn1-1.xx&oh=05ceb3efda3ccc6ce6186bd734e12d61&oe=5E156726","김인직",321321,"aa","https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/69604273_768139570269014_1217837345420607488_n.jpg?_nc_cat=103&_nc_eui2=AeGVEtVYLuHGo3X5T3MWiWva_hNYkI_V77pGbp2NywTrYrQh54AVRwacR7ZEjNPvS7SvM9o67N2WjdGXAVF5cQ50QIpDBJli59ZSZCF4zNGnpQ&_nc_oc=AQljah0RnMBp5MdFO_ijj-x1AbZpoiIKUvXB-5AlRyb7qGwyosyx_WX0dRjPeHs139s&_nc_ht=scontent-icn1-1.xx&oh=a9afd97099220498cc4623c836e186ff&oe=5E0C1DC1","aa",100,true))
        feeds.add(Feed("aa","https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/67403926_132863061286633_2428159034944126976_n.jpg?_nc_cat=111&_nc_eui2=AeEBBhNE_x8QbW2VUZ44ux6LJpQKHjEs3OFl2jHv6NuUeB8E7WlH-TFTP0r69Y136W7aIWS_xMNg4RJD4_tdOZBBXgFWECi85CYsr5JLAZTh_Q&_nc_oc=AQnq-O1fFeIUpXbLH0zOByqesQqQ4e1cmcKsxsWKWodyBvlyoGBuTt4Fh2VuEwjjCsA&_nc_ht=scontent-icn1-1.xx&oh=05ceb3efda3ccc6ce6186bd734e12d61&oe=5E156726","최석준",321321,"aa","https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/69280261_768139600269011_2894823346371821568_n.jpg?_nc_cat=105&_nc_eui2=AeHsLD9y-cMc-FCCCSCoQJS3xnbO3pRwQWe9H5F-cPryvJO-HEO4JUwn71DWj4kI5FvDlrK-1ILYnyTqo-aB8PmHRmLfrS54laMMMPbxBhvxog&_nc_oc=AQls240rAKJlsYHvGBTLPq6aeoEHWJnAOOdRuf773-O2-NnWQ3U9_G87b2h01QpYisc&_nc_ht=scontent-icn1-1.xx&oh=6038c08808999299603ebb4499d6dc6f&oe=5DFD86D4","aa",1321,false))
        feeds.add(Feed("aa","https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/67403926_132863061286633_2428159034944126976_n.jpg?_nc_cat=111&_nc_eui2=AeEBBhNE_x8QbW2VUZ44ux6LJpQKHjEs3OFl2jHv6NuUeB8E7WlH-TFTP0r69Y136W7aIWS_xMNg4RJD4_tdOZBBXgFWECi85CYsr5JLAZTh_Q&_nc_oc=AQnq-O1fFeIUpXbLH0zOByqesQqQ4e1cmcKsxsWKWodyBvlyoGBuTt4Fh2VuEwjjCsA&_nc_ht=scontent-icn1-1.xx&oh=05ceb3efda3ccc6ce6186bd734e12d61&oe=5E156726","이유승",321321,"aa","https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/69431091_768139633602341_1174538603288592384_n.jpg?_nc_cat=111&_nc_eui2=AeHWbz-rtzsZ-Au1aAuC-UrbSkDplHS_fIxNMkaHVBX7jcTRYaheqpS3yvIpJi2Oxrko_dux2uSYOhijaiye6USFq8ZD39tbWZquWfJxq72X2w&_nc_oc=AQmnz9pWjH6FKlyfKBs3Q42fVhChhJ8u1XlWhYe0Lpp8zXKx6qu-WZB8h39c5nHTTlg&_nc_ht=scontent-icn1-1.xx&oh=eb323d7d64da1923ad77ccc59e907b1a&oe=5E018EB1","aa",1323,false))
        feeds.add(Feed("aa","https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/67403926_132863061286633_2428159034944126976_n.jpg?_nc_cat=111&_nc_eui2=AeEBBhNE_x8QbW2VUZ44ux6LJpQKHjEs3OFl2jHv6NuUeB8E7WlH-TFTP0r69Y136W7aIWS_xMNg4RJD4_tdOZBBXgFWECi85CYsr5JLAZTh_Q&_nc_oc=AQnq-O1fFeIUpXbLH0zOByqesQqQ4e1cmcKsxsWKWodyBvlyoGBuTt4Fh2VuEwjjCsA&_nc_ht=scontent-icn1-1.xx&oh=05ceb3efda3ccc6ce6186bd734e12d61&oe=5E156726","홀리쉣",321321,"aa","https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/69208890_768139663602338_4461828768314425344_n.jpg?_nc_cat=111&_nc_eui2=AeHQrMfF1y2qESwzwmYLgq6d9eCeT2_ZxUjcwWSs_UJcImjAP8-ORduMiRi-CcL1WQmpmlt9rTKzrY8sTtL2FKcKb57Ez1lWZcW9DXhK1wlEeg&_nc_oc=AQl2aMKyOZfoMQ4BxMF2HRe1DXKYaXYchH0fMrH7HCZpEFr6G0o2gIqbmFxh8gWfsBg&_nc_ht=scontent-icn1-1.xx&oh=3c9cdaaf0790d428e40ae9881a3d1b1a&oe=5DD38AE3","aa",132,true))
        feeds.add(Feed("aa","https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/67403926_132863061286633_2428159034944126976_n.jpg?_nc_cat=111&_nc_eui2=AeEBBhNE_x8QbW2VUZ44ux6LJpQKHjEs3OFl2jHv6NuUeB8E7WlH-TFTP0r69Y136W7aIWS_xMNg4RJD4_tdOZBBXgFWECi85CYsr5JLAZTh_Q&_nc_oc=AQnq-O1fFeIUpXbLH0zOByqesQqQ4e1cmcKsxsWKWodyBvlyoGBuTt4Fh2VuEwjjCsA&_nc_ht=scontent-icn1-1.xx&oh=05ceb3efda3ccc6ce6186bd734e12d61&oe=5E156726","왓더뻑",321321,"aa","https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/69822182_768139703602334_1331934470193807360_n.jpg?_nc_cat=106&_nc_eui2=AeGdCYQDKY0Vj9FR0Z2RUx6kgDzfKckGUajSiqm87dNm9WSgLpk6S0-X_oT5ELsNqG2V-0DBJKa7Jp4cc576a_Es2q7Kc3CYYQQIbDE-ZfvKzQ&_nc_oc=AQlVGj2wANh2-Tgjl3wvG7-Az7OvSqWdnS4aFDCwFMzGohq1-HPBdW-SgO_8fbsdCSE&_nc_ht=scontent-icn1-1.xx&oh=b55bf464cd41ae93bedf04d75e2ef7de&oe=5E00E538","aa",133,false))
        feeds.add(Feed("aa","https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/67403926_132863061286633_2428159034944126976_n.jpg?_nc_cat=111&_nc_eui2=AeEBBhNE_x8QbW2VUZ44ux6LJpQKHjEs3OFl2jHv6NuUeB8E7WlH-TFTP0r69Y136W7aIWS_xMNg4RJD4_tdOZBBXgFWECi85CYsr5JLAZTh_Q&_nc_oc=AQnq-O1fFeIUpXbLH0zOByqesQqQ4e1cmcKsxsWKWodyBvlyoGBuTt4Fh2VuEwjjCsA&_nc_ht=scontent-icn1-1.xx&oh=05ceb3efda3ccc6ce6186bd734e12d61&oe=5E156726","안늉",321321,"aa","https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/69295326_768139733602331_7321993085817192448_n.jpg?_nc_cat=105&_nc_eui2=AeHpSS0isTW45my0VSgg49d4I1SYdmaN522pEStC5KMFbrVA9WUsHYFnmNlDE7CbCkg4y9pf46MLfhpQcoGBnyhcso0FdbCdUomefHb2t3zy3w&_nc_oc=AQmB-JzoUxG7adQsA3Dj8iDpTmOrdevS3FuZ3sfty_QH2mHdpPxy7zAlcaO5yisG-vw&_nc_ht=scontent-icn1-1.xx&oh=a9fa7ae1622fbc38a3866a10cb5f1694&oe=5DCDC5AD","aa",111,false))
        feeds.add(Feed("aa","https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/67403926_132863061286633_2428159034944126976_n.jpg?_nc_cat=111&_nc_eui2=AeEBBhNE_x8QbW2VUZ44ux6LJpQKHjEs3OFl2jHv6NuUeB8E7WlH-TFTP0r69Y136W7aIWS_xMNg4RJD4_tdOZBBXgFWECi85CYsr5JLAZTh_Q&_nc_oc=AQnq-O1fFeIUpXbLH0zOByqesQqQ4e1cmcKsxsWKWodyBvlyoGBuTt4Fh2VuEwjjCsA&_nc_ht=scontent-icn1-1.xx&oh=05ceb3efda3ccc6ce6186bd734e12d61&oe=5E156726","하하하",321321,"aa","https://scontent-icn1-1.xx.fbcdn.net/v/t1.0-9/69227072_768139776935660_8770370415827615744_n.jpg?_nc_cat=103&_nc_eui2=AeGb86R02_nyfSADrMTIsbRNhZcnu2xyqPDfdV3g90NexSTCAqz0MhqikXvdUDTorIpT2jM3fDUDe2RNzPtDnOfGJC1mpgOD5z6NbUJCtAfNtA&_nc_oc=AQkSUJMfkB_Y1Q8AoEOzKD_dZZCV-FIb_WoHFJXKAQe7xoVq0li2S3xGZWbR9bsQa80&_nc_ht=scontent-icn1-1.xx&oh=0549a8f231389c303ca1ef18fe08f101&oe=5E0DCFEC","aa",122,true))

        feedAdapter = FeedAdapter(context!!, feeds)
        setRecyclerView()

        clickEvent()
        scrollEvent()
    }

    private fun initData() {
        animAddShow = AnimationUtils.loadAnimation(context, R.anim.animation_add_show)
        animAddHide = AnimationUtils.loadAnimation(context, R.anim.animation_add_hide)
    }

    private fun clickEvent() {
        binding.writeBtn.setOnClickListener {  }
    }

    private fun scrollEvent() {
        binding.feedRecyclerview.addOnScrollListener(object : OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (dy > 20) {
                    if (isOpenWriteBtn) {
                        binding.writeBtn.startAnimation(animAddHide)
                        appBarBinding.toolbar.startAnimation(animToolbarHide)
                        binding.writeBtn.visibility = View.INVISIBLE
                        appBarBinding.toolbar.visibility = View.INVISIBLE
                        isOpenWriteBtn = false
                    }
                }
                else if (dy < -20) {
                    if (!isOpenWriteBtn) {
                        binding.writeBtn.startAnimation(animAddShow)
                        appBarBinding.toolbar.startAnimation(animToolbarShow)
                        binding.writeBtn.visibility = View.VISIBLE
                        appBarBinding.toolbar.visibility = View.VISIBLE
                        isOpenWriteBtn = true
                    }
                }
            }
        })
    }

    private fun setRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(context)
        binding.feedRecyclerview.adapter = feedAdapter
        binding.feedRecyclerview.layoutManager = linearLayoutManager
    }

    override fun layoutId(): Int {
        return R.layout.feed_fragment
    }
}