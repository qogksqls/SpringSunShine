<template>
  <!--상담사만 봄 상담일지 볼 탭-->
  <div class="overflow-auto  text-center">
    <b-table
      hover
      id="my-table2"
      :items="items"
      :per-page="perPage"
      :current-page="currentPage"
      small
      :fields="fields"
      @row-clicked="counReview"
    >
    </b-table>
    <b-pagination
      v-model="currentPage"
      :total-rows="rows"
      :per-page="perPage"
      aria-controls="my-table2"
      align="center"
    ></b-pagination>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      perPage: 5,
      currentPage: 1,
      fields: [
        {
          key: "NO",
          sortable: true,
        },
        {
          key: "date",
          label: "날짜",
          soratable: true,
        },
        {
          key: "time",
          label: "시간",
          soratable: true,
        },
        {
          key: "content",
          label: "상담일지",
          soratable: false,
        },
      ],
      items: [
        { NO: 1, date: 20220812, time: "17:00", content: "어쩌구저쩌구" },
        { NO: 2, date: 20220812, time: "17:00", content: "어쩌구저쩌구" },
        { NO: 3, date: 20220812, time: "17:00", content: "어쩌구저쩌구" },
        { NO: 4, date: 20220812, time: "17:00", content: "어쩌구저쩌구" },
        { NO: 5, date: 20220812, time: "17:00", content: "어쩌구저쩌구" },
        { NO: 6, date: 20220812, time: "17:00", content: "어쩌구저쩌구" },
        { NO: 7, date: 20220812, time: "17:00", content: "어쩌구저쩌구" },
        { NO: 8, date: 20220812, time: "17:00", content: "어쩌구저쩌구" },
        { NO: 9, date: 20220812, time: "17:00", content: "어쩌구저쩌구" },
        { NO: 10, date: 20220812, time: "17:00", content: "어쩌구저쩌구" },
      ],
    };
  },
  computed: {
    rows() {
      return this.items.length;
    },
  },
  created() {
    console.log("특정아동 상담내역");
    axios({
      url: `https://i7a606.q.ssafy.io/service-api/consult/parent/${
        this.$store.state.accounts.userid
      }/${this.$route.params.childId}/1/5`,
      method: "get",
    })
      .then((res) => {
        console.log(res.data);
        // this.items = res.data
        // for (let i = 0; i < this.items.length; i++) {
        //   this.items[i]["No"] = i + 1
        //   // const date = this.items[i]["reservTime"].slice(0, 10)
        //   // const time = this.items[i]["reservTime"].slice(11, 16)
        //   // this.items[i]["date"] = date
        //   // this.items[i]["time"] = time
        // }
        // console.log(this.items)
      })
      .catch((err) => {
        console.log(err.response);
      })
      .catch((err) => {
        console.log(err.response);
      });
  },
  methods: {
    counReview(item) {
      this.$router.push({ name: "counReview" });
    },
  },
};
</script>
<style scoped>
#my-table2 {
  cursor: pointer;
}
</style>
