<template>
  <div class="overflow-auto text-center">
    <b-table
      hover
      id="my-table"
      :items="items"
      :per-page="perPage"
      :current-page="currentPage"
      small
      :fields="fields"
    ></b-table>
    <b-pagination
      v-model="currentPage"
      :total-rows="rows"
      :per-page="perPage"
      aria-controls="my-table"
      align="center"
    ></b-pagination>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      items: [],
      perPage: 5,
      currentPage: 1,
      fields: [
        {
          key: "No",
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
          key: "theraName",
          label: "상담사",
          soratable: true,
        },
        // {
        //   key: "parent",
        //   label: "보호자",
        //   soratable: true,
        // },
        {
          key: "childName",
          label: "아이",
          soratable: true,
        },
      ],
      // reserves: [
      //   {
      //     NO: 1,
      //     date: "20220812 ",
      //     time: "17:00",
      //     ther: "어",
      //     parent: "부모",
      //     child: "아이",
      //   },
      // ],
    };
  },
  computed: {
    rows() {
      return this.items.length;
    },
  },
  created() {
    console.log('특정아동 예약날짜')
    axios({
      url: `https://i7a606.q.ssafy.io/service-api/reserv-parent/${this.$store.state.accounts.userid}/${this.$route.params.childId}`,
      method: 'get'
    })
      .then(res => {
        console.log(res.data)
        this.items = res.data
        for (let i = 0; i < this.items.length; i++) {
          this.items[i]["No"] = i + 1
          const date = this.items[i]["reservTime"].slice(0, 10)
          const time = `${Number(this.items[i]["reservTime"].slice(11, 13)) + 9} 시`
          this.items[i]["date"] = date
          this.items[i]["time"] = time
        }
        console.log(this.items)
      })
      .catch(err => {
        console.log(err.response)
      })
  }
};
</script>
<style scoped>
#my-table {
  cursor: pointer;
}
</style>
